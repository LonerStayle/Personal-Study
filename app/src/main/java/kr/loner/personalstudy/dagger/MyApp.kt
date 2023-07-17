package kr.loner.personalstudy.dagger

import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class MyApp:Application(), HasAndroidInjector {
    // AppComponent에게 DispatchingAndroidInjector 의존성 요청
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>
    override fun onCreate() {
        super.onCreate()
        // AppComponent의 Member-Injection 메서드 inject()
//        DaggerAppComponent.create().inject(this@MyApp)
    }
    override fun androidInjector(): AndroidInjector<Any>  = dispatchingAndroidInjector
}