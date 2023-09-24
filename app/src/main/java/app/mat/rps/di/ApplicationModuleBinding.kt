package app.mat.rps.di

import app.mat.rps.common.util.controller.Controller
import app.mat.rps.common.util.controller.ControllerImpl
import app.mat.rps.common.util.engine.Engine
import app.mat.rps.common.util.engine.EngineImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(
    SingletonComponent::class
)
interface ApplicationModuleBinding {
    @Binds
    fun provideEngine(
        engine: EngineImpl
    ): Engine

    @Binds
    fun provideController(
        controller: ControllerImpl
    ): Controller
}
