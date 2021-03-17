package com.example.todolist.dagger

import dagger.Module
import dagger.Provides
import org.jetbrains.annotations.NotNull
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import javax.inject.Singleton

@Module
class ExecutorServiceModule {

    @Provides
    @NotNull
    @Singleton
    fun provideExecutorService(): ExecutorService {
        return Executors.newSingleThreadExecutor()
    }

}