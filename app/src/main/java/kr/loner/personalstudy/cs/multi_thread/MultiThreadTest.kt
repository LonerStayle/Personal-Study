package kr.loner.personalstudy.cs.multi_thread

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


var counter = 0
fun concurrencyTest() {
    repeat(100) {
        CoroutineScope(Dispatchers.Default).launch {
            repeat(1000) {
                counter++
            }
        }
    }

}

fun main() {
    concurrencyTest()
    println(counter)
}