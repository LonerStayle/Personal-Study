package kr.loner.personalstudy.coroutine

import android.widget.Button

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flow
import org.w3c.dom.Text
import java.lang.NullPointerException
import kotlin.coroutines.Continuation
import kotlin.coroutines.ContinuationInterceptor
import kotlin.coroutines.CoroutineContext

/**Delay 내부 코드 보기용*/
suspend fun delayTest() {
    delay(1000)
}

/**Scope 내부 코드 보기용*/
private fun globalScope() = runBlocking {
    //CoroutineScope 를 상속 받고 EmptyCoroutineContext 를 기본적으로 가진다.
    GlobalScope
    //ContextScope 를 생성 한다. 생성 할때 파라미터로 CoroutineContext.get 에서 Job이 있을 경우 context, 없을 경우 context + Job() 으로 context를 넘긴다.
    val job = CoroutineScope(Dispatchers.IO)
    job.cancel()
    CoroutineScope(Dispatchers.Main).launch {
        for (i in 1..10) {
            println(i.toString())
        }
    }
    println("여기 로그 찍히니?")
    channelProduceTest().consumeEach { println(it) }
    GlobalScope.launch() { }
    GlobalScope.async { }
    MutableSharedFlow<String>()

//    kotlinx.coroutines.scheduling.CoroutineScheduler
    withContext(this.coroutineContext) {}

}


class CEH(override val key: CoroutineContext.Key<*>) : CoroutineExceptionHandler {
    override fun handleException(context: CoroutineContext, exception: Throwable) {

    }

}


class CI(override val key: CoroutineContext.Key<*>) : ContinuationInterceptor {

    override fun <T> interceptContinuation(continuation: Continuation<T>): Continuation<T> {
        TODO("Not yet implemented")
    }

    override fun releaseInterceptedContinuation(continuation: Continuation<*>) {
        super.releaseInterceptedContinuation(continuation)
    }
}


suspend fun asyncTest(): Int =
    coroutineScope {
        val deferredOne: Deferred<Int> = async { 1 }
        val deferredTwo = async { 2 }
        deferredOne.await()
        deferredTwo.await()
    }

suspend fun asyncTestList(): Int =
    coroutineScope {
        val deferredOne: Deferred<Int> = async { 1 }
        val deferredTwo = async { 2 }
        val testList = listOf(deferredOne, deferredTwo)
        testList.awaitAll().sum()
    }


suspend fun fetchLatestNews(): String {
    return withContext(Dispatchers.IO) {
        "asd"
    }
}


suspend fun channelTest() {
    val channel: Channel<Int> = Channel()
    for (i in 1..5) channel.send(i)
    channel.consume { println(receive()) }
}

@ExperimentalCoroutinesApi
fun CoroutineScope.channelProduceTest(): ReceiveChannel<Int> = produce {
    repeat(5) { send(it) }
}


fun getNumberIteratorFrom(start: Int) = iterator {
    var x = start
    while (true) yield(x++) // infinite stream of integers from start
}

fun getFilteredNumberIterator(numbers: Iterator<Int>, prime: Int) = iterator {
    for (x in numbers) {
        if (x % prime != 0) yield(x)
    }
}

fun pipeLineIterator() {
    var cur = getNumberIteratorFrom(2)
    for (i in 1..10) {
        val prime = cur.next()
        println(prime)
        cur = getFilteredNumberIterator(cur, prime)
    }
    println("Done")
}

/** 채널 프로듀스 만들기*/
fun mainTest01() = runBlocking<Unit> {
    val squares = produceSquares(5)
    squares.consumeEach { println(it) }
    println("Done")
}

fun CoroutineScope.produceSquares(max: Int): ReceiveChannel<Int> = produce {
    for (x in 1..max) send(x * x)
}

/**파이프 라인
 * [정수 생성 프로듀서] -> [두배수 변환 프로듀서] -> 출력
 * */
fun mainTest02() = runBlocking {
    produceDouble(produceNumbers(5)).consumeEach { println(it) }
    println("Done")
}

fun CoroutineScope.produceNumbers(max: Int): ReceiveChannel<Int> = produce {
    for (x in 1..max) send(x)
}

fun CoroutineScope.produceDouble(numbers: ReceiveChannel<Int>): ReceiveChannel<Int> = produce {
    numbers.consumeEach { send(it * 2) }
}


/**
 * 팬 아웃
 * 송신:1채널 -> 수신:N 코루틴
 * 아래 예제는 for loop 를 사용했지만
 *  consumeEach 확장 함수를 이용했다면 각 프로세서 코루틴들은 다른 프로세서 코루틴의 정상 종료 혹은 비정상 종료에 대해서 취소 이벤트를 전파 받고 모두 종료될 것임
 * */
fun mainTest03() = runBlocking {
    val producer = produceNumbers()
    repeat(5) { launchProcessor(it, producer) }
    delay(950)
    producer.cancel()
}

fun CoroutineScope.produceNumbers() = produce {
    var x = 1
    while (true) {
        send(x++)
        delay(100)
    }
}

fun CoroutineScope.launchProcessor(id: Int, channel: ReceiveChannel<Int>) = launch {
    for (msg in channel) println("Processor #$id received $msg")
}

/***
 * 팬 인
 * 송신:N 코루틴 -> 수신:1채널
 *
 */

fun mainTest04() = runBlocking {
    val channel = Channel<String>()
    launch { sendString(channel, "Foo", 200) }
    launch { sendString(channel, "Bar", 500) }
    repeat(6) {
        println(channel.receive())
    }
    coroutineContext.cancelChildren()
}

suspend fun sendString(channel: SendChannel<String>, text: String, time: Long) {
    while (true) {
        delay(time)
        channel.send(text)
    }
}


/***
 * 버퍼가 있는 채널
 * 출력:4에서 끝남
 */
fun mainTest05() = runBlocking {
    val channel = Channel<Int>(4)

    val sender = launch {
        repeat(10) {
            print("Try to send $it : ")
            channel.send(it)
            print("Done\n")
        }
    }

    delay(1000)
    sender.cancel()
}


/***
 * 채널은 공정하다.
 * player 가 ping 일때 delay 를 지워도 ping이 먼저 수신 된다.
 * 출력:
 * ping Ball(hits=1)
 * pong Ball(hits=2)
 * ping Ball(hits=3)
 * pong Ball(hits=4)
 */
data class Ball(var hits: Int)

fun mainTest06() = runBlocking {
    val table = Channel<Ball>()
    launch { player("ping", table) }
    launch { player("pong", table) }
    table.send(Ball(0))
    delay(1000)
    coroutineContext.cancelChildren()
}

suspend fun player(name: String, table: Channel<Ball>) {
    for (ball in table) {
        ball.hits++
        println("$name $ball")
        delay(300)
        table.send(ball)
    }
}

fun main() = runBlocking {
    async {
        throw NullPointerException("asd")
    }
}
