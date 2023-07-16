package kr.loner.personalstudy.dagger

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.Reusable
import javax.inject.Scope
import javax.inject.Singleton


@Module
abstract class DaggerModule {
    @Provides
    @Reusable
    fun provideMockInteger() = 1

    // Makes Dagger provide SharedPreferencesStorage when a Storage type is requested
    @Binds
    abstract fun provideTestObject(fakeInt: Int):DaggerTestObject
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class TestScope { }