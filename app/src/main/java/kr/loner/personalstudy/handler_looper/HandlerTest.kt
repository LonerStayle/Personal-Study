package kr.loner.personalstudy.handler_looper

import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun HandlerSendMsgBtn() {
    val context = LocalContext.current
    val mainHandler = Handler(Looper.getMainLooper())
    Button(modifier = Modifier.padding(top = 8.dp), onClick = {
        Thread{
            /**prepare() 를 하지 않으면 myLooper가 Null임 */
            Looper.prepare()
            val mockHandler = MockHandler(Looper.myLooper()!!)

            val msg = Message.obtain(mainHandler) {
                Toast.makeText(context, "핸들러 테스트", Toast.LENGTH_SHORT).show()
            }
            mockHandler.sendMessage(msg)
            /**loop 는 이전에 작성한 sendMsg를 다른 핸들러에 보냄*/
            Looper.loop()
        }.start()
    }) {
        Text(text = "메인 핸들러 테스트")
    }

}
@Composable
fun WorkerHandlerSendMsgBtn() {
    Button(modifier = Modifier.padding(top = 8.dp), onClick = {
        Thread{
            /**prepare() 를 하지 않으면 myLooper가 Null임 */
            Looper.prepare()
            val mockHandler = MockHandler(Looper.myLooper()!!)

            val msg = Message.obtain(mockHandler) {
                //UI 관련 일이 아닌 작업
            }
            mockHandler.sendMessage(msg)
            /**loop 는 이전에 작성한 sendMsg를 다른 핸들러에 보냄*/
            Looper.loop()
        }.start()
    }) {
        Text(text = "워커 핸들러 테스트")
    }

}



class MockHandler(looper: Looper):Handler(looper){
    override fun handleMessage(msg: Message) {
        super.handleMessage(msg)
        if(msg.what == 0){
            Log.d("checkk","워커 핸들러로 왔는가?")
        }
    }
}