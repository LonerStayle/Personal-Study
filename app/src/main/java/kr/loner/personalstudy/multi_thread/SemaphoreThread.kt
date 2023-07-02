package kr.loner.personalstudy.multi_thread

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


class SemaphoreThread(private val semaphore: java.util.concurrent.Semaphore) : Thread() {
    override fun run() {
        super.run()
        semaphore.acquire()
        try {
            Log.d("checkk", "${currentThread().name} is executing the critical section.")
            sleep(1000)
        } finally {
            semaphore.release()
        }
    }
}


@Composable
fun SemaphoreTestBtn() {
    val semaphore = java.util.concurrent.Semaphore(1)
    Button(modifier = Modifier.padding(top = 8.dp), onClick = {
        val thread01 = SemaphoreThread(semaphore)
        val thread02 = SemaphoreThread(semaphore)
        thread01.start()
        thread02.start()
        thread01.join()
        thread02.join()
    }) {
        Text(text = "세마포어")
    }
}