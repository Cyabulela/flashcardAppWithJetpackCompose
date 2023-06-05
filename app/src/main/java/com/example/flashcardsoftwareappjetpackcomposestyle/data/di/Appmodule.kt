package com.example.flashcardsoftwareappjetpackcomposestyle.data.di

import android.app.Application
import androidx.room.Room
import com.example.flashcardsoftwareappjetpackcomposestyle.data.local.FlashcardDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRoomDatabase(app : Application) : FlashcardDatabase {
        return Room.databaseBuilder(
            app,
            FlashcardDatabase::class.java,
            "flashcards.db"
        ).build()
    }
}