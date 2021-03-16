package com.example.todolist.dagger

import android.content.Context
import dagger.Module
import dagger.Provides
import org.jetbrains.annotations.NotNull
import javax.inject.Singleton

@Module
class AppContextModule(@NotNull val appContext: Context) {

    @Provides
    @NotNull
    @Singleton
    fun provideAppContext(): Context {
        return appContext
    }

}