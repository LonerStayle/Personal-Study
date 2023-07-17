package kr.loner.personalstudy.dagger

import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.Subcomponent
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap
import kr.loner.personalstudy.ui.activity.DetailActivity
import kr.loner.personalstudy.ui.activity.MainActivity
import javax.inject.Singleton

/**
 *
 * 1.AndroidInjectionModule 추가 (ApplicationComponent의 modules)
 * 2.ActivityBindingModule 추가 (ApplicationComponent의 modules)
 * 3.AndroidInjector<Activity 또는 Fragment> 상속 (SubComponent interface에 상속)
 * 4.AndroidInjector.Factory<Activity 또는 Fragment) 상속 (SubComponent.Factory interface에 상속)
 * 5.Application 단위 클래스에서 HasAndroidInjector를 상속하고 androidInjector() 메서드를 구현하기(DispatchingAndroidInjector를 반환)
 * 6.각 Activity / Fragment에서 AndroidInjection.inject()로 의존성 주입을 완료
 */


/**1번,2번*/
@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,    // 조건 1번,
        ActivityBindingModule::class, // 조건 2번, AppComponent의 하위 Component 연결위한 Module
        CoffeeModule::class        // Coffee 의존성 인스턴스 생성 위한 Module
    ]
)
interface AppComponent {
    fun inject(myApp: MyApp)    // Application을 구현한 BaseApplication 클래스
}


/**3번*/
@Subcomponent(modules = [CoffeeModule::class])
interface MainComponent : AndroidInjector<MainActivity> {
    @Subcomponent.Factory
    interface Factory : AndroidInjector.Factory<MainActivity> {}
}

@Subcomponent(modules = [CoffeeModule::class])
interface DetailComponent : AndroidInjector<DetailActivity> {
    @Subcomponent.Factory
    interface Factory : AndroidInjector.Factory<MainActivity> {}
}

/**4번*/
@Module(
    subcomponents = [
        MainComponent::class,
        DetailComponent::class
    ]
)
abstract class ActivityBindingModule {    // @Binds 메서드 사용하기 때문에 abstract(추상) 설정

    @Binds
    @IntoMap                // MultiBinding을 위한 annotation
    @ClassKey(MainActivity::class)    // MultiBinding Map의 Key Type
    abstract fun bindMainInjectorFactory(factory: MainComponent.Factory): AndroidInjector.Factory<*>

    @Binds
    @IntoMap
    @ClassKey(DetailActivity::class)
    abstract fun bindDetailInjectorFactory(factory: DetailComponent.Factory): AndroidInjector.Factory<*>


    // 위의 Map은 이것 처럼 된다. Key : Class<*>, Value : AndroidInjecotr.Factory<*>
//    Map<Class<*>, AndroidInjector.Factory<*>>

}


