package kr.loner.personalstudy.coroutine

import android.widget.Button

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*
import kotlinx.coroutines.flow.MutableSharedFlow
import org.w3c.dom.Text
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
    CoroutineScope(Dispatchers.Main).launch{
        for (i in 1..10){
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


suspend fun channelTest(){
    val channel:Channel<Int> = Channel()
    for(i in 1..5) channel.send(i)



    channel.consume {
        println(receive())
         }


}

@ExperimentalCoroutinesApi
fun CoroutineScope.channelProduceTest(): ReceiveChannel<Int> = produce {
    repeat(5){ send(it)}
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

fun pipeLineIterator(){
    var cur = getNumberIteratorFrom(2)
    for (i in 1..10) {
        val prime = cur.next()
        println(prime)
        cur = getFilteredNumberIterator(cur, prime)
    }
    println("Done")
}