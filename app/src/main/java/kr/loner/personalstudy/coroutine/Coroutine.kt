package kr.loner.personalstudy.coroutine

import android.os.Handler
import android.os.Looper
import android.os.Message
import android.provider.Settings.Global
import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.*
import kr.loner.personalstudy.handler_looper.MockHandler
import kotlin.coroutines.Continuation
import kotlin.coroutines.ContinuationInterceptor
import kotlin.coroutines.CoroutineContext


@Composable
fun CoroutineTestBtn(){
    Button(modifier = Modifier.padding(top = 8.dp), onClick = {


    }) {
        Text(text = "메인 핸들러 테스트")
    }
}

/**Delay 내부 코드 보기용*/
suspend fun delayTest(){
    delay(1000)
}

/**Scope 내부 코드 보기용*/
private fun globalScope() = runBlocking{
    //CoroutineScope 를 상속 받고 EmptyCoroutineContext 를 기본적으로 가진다.
    GlobalScope
    //ContextScope 를 생성 한다. 생성 할때 파라미터로 CoroutineContext.get 에서 Job이 있을 경우 context, 없을 경우 context + Job() 으로 context를 넘긴다.
    CoroutineScope(Dispatchers.IO)


    GlobalScope.launch() {  }
    GlobalScope.async {  }
//    kotlinx.coroutines.scheduling.CoroutineScheduler
    withContext(this.coroutineContext){}

}


class CHE(override val key: CoroutineContext.Key<*>) :CoroutineExceptionHandler{
    override fun handleException(context: CoroutineContext, exception: Throwable) {

    }

}


class CI(override val key: CoroutineContext.Key<*>): ContinuationInterceptor{

    override fun <T> interceptContinuation(continuation: Continuation<T>): Continuation<T> {
        TODO("Not yet implemented")
    }

    override fun releaseInterceptedContinuation(continuation: Continuation<*>) {
        super.releaseInterceptedContinuation(continuation)
    }
}


