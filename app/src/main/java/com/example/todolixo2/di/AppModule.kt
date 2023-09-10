package com.example.todolixo2.di

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.room.Room
import androidx.savedstate.SavedStateRegistryOwner
import com.example.todolixo2.data.TaskDao
import com.example.todolixo2.data.TaskDatabase

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideTaskDatabase(
        app: Application,
        callcack: TaskDatabase.CallbackX
    ): TaskDatabase {
        return Room.databaseBuilder(app, TaskDatabase::class.java, "task_database")
            .fallbackToDestructiveMigration()
            .addCallback(callcack)
            .build()
    }

    @Provides
    fun provideTaskDao(db: TaskDatabase): TaskDao {
        return db.taskDao()
    }

    @ApplicationScope
    @Provides
    @Singleton
    fun provideApplicationScope(): CoroutineScope {
        return CoroutineScope(SupervisorJob())
    }

}

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class ApplicationScope




@Module
@InstallIn(FragmentComponent::class)
object FragmentModule {
    @Provides
    fun provideSavedStateRegistryOwner(fragment: Fragment): SavedStateRegistryOwner {
        return fragment
    }
    @Provides
    fun provideDefaultArgs(): Bundle? {
        return null
    }

}