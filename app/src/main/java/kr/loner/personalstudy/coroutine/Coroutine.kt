package kr.loner.personalstudy.coroutine

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun coroutineTest(dispatcher: CoroutineDispatcher) = CoroutineScope(dispatcher).async {
    launch {
        delay(1000)
        println("World")
    }
    println("Hello")

    runBlocking {  }
}


