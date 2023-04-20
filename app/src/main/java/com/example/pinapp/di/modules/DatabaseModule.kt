package com.example.pinapp.di.modules

import com.example.pinapp.model.dao.Pin
import dagger.Module
import dagger.Provides
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import javax.inject.Singleton

@Module
internal class DatabaseModule {

    @Singleton
    @Provides
    internal fun provideRealmDatabase(): Realm {
        val config = RealmConfiguration.Builder(
            schema = setOf(
                Pin::class
            )
        )
            .schemaVersion(0)
            .build()

        return Realm.open(config)
    }

}