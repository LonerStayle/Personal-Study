package kr.loner.personalstudy.dagger

import android.app.Application
import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        DaggerModule::class
    ]
)
interface ApplicationComponent : AndroidInjector<Application> {
    //프로비전 메서드
    fun makeTestObject(): DaggerTestObject

    //멤버 인젝션 메서드
    fun inject(injectActivity: InjectActivity)

    @Component.Factory
    interface Factory {
        // With @BindsInstance, the Context passed in will be available in the graph
        fun create(@BindsInstance context: Context): ApplicationComponent
    }

}