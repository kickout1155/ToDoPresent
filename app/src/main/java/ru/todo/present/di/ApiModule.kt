package ru.todo.present.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.todo.present.core.datasource.NetworkDataSource
import ru.todo.present.production.NetworkDataSourceProduction
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun bind(network: NetworkDataSourceProduction): NetworkDataSource

}
