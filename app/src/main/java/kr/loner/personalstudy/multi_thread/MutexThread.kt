package kr.loner.personalstudy.multi_thread

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.util.concurrent.locks.ReentrantLock

class MutexThread(private val mutex:ReentrantLock):Thread() {
    override fun run() {
        super.run()
        mutex.lock()
        try{
            Log.d("checkk","${currentThread().name} is executing the critical section.")
            sleep(1000)
        }finally {
           mutex.unlock()
        }
    }
}


@Composable
fun MutexTestBtn(){
    val mutex = remember { ReentrantLock() }
    Button(modifier = Modifier.padding(top = 8.dp), onClick = {
        val thread01 = MutexThread(mutex)
        val thread02 = MutexThread(mutex)
        thread01.start()
        thread02.start()
        thread01.join()
        thread02.join()
    }){
        Text(text = "뮤텍스")
    }
}