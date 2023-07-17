package kr.loner.personalstudy.dagger

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import dagger.multibindings.StringKey

@Module
abstract class CoffeeModule {
    // @Binds 사용하려면 먼저 파라미터 Type의 @Provide 또는 @Inject 설정이 필요합니다

    @Binds
    @IntoMap
    @StringKey("ethiopia")
    abstract fun provideEthiopiaBean(ethiopiaBean: EthiopiaBean) : CoffeeBean

    @Binds
    @IntoMap
    @StringKey("guatemala")
    abstract fun provideGuatemalaBean(guatemalaBean: GuatemalaBean) : CoffeeBean
}

interface CoffeeBean
class GuatemalaBean:CoffeeBean
class EthiopiaBean:CoffeeBean