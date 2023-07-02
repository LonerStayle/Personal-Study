package kr.loner.personalstudy.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kr.loner.personalstudy.handler_looper.HandlerPostBtn
import kr.loner.personalstudy.handler_looper.HandlerSendMsgBtn
import kr.loner.personalstudy.multi_thread.MutexTestBtn
import kr.loner.personalstudy.multi_thread.SemaphoreTestBtn
import kr.loner.personalstudy.ui.theme.PersonalStudyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PersonalStudyTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        MutexTestBtn()
                        SemaphoreTestBtn()
                        HandlerSendMsgBtn()
                        HandlerPostBtn()
                    }
                }
            }
        }
    }
}