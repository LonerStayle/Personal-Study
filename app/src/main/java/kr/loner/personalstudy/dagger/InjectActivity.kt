package kr.loner.personalstudy.dagger

import android.os.Bundle
import androidx.activity.ComponentActivity
import dagger.android.AndroidInjection
import javax.inject.Inject

class InjectActivity: ComponentActivity() {

    @Inject
    lateinit var testObject: DaggerTestObject

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }
}