package com.example.pinapp.di.modules

import com.example.pinapp.repository.PinRepositoryImpl
import com.example.pinapp.usecases.PinUseCase
import dagger.Module
import dagger.Provides
import io.realm.kotlin.Realm

@Module
internal class PinModule {

    @Provides
    internal fun providePinRepository(
        realmDatabase: Realm
    ): PinRepositoryImpl =
        PinRepositoryImpl(
            realmDatabase = realmDatabase
        )

    @Provides
    internal fun providePinUseCase(
        pinRepository: PinRepositoryImpl
    ): PinUseCase =
        PinUseCase(
            pinRepository
        )
}