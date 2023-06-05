package com.example.flashcardsoftwareappjetpackcomposestyle.data.di

import com.example.flashcardsoftwareappjetpackcomposestyle.data.repository.FlashcardRepositoryImpl
import com.example.flashcardsoftwareappjetpackcomposestyle.domain.repository.FlashcardRepository
import com.example.flashcardsoftwareappjetpackcomposestyle.domain.use_case.FlashcardUseCasesFactory
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class FlashcardBindings {

    @Binds
    @Singleton
    abstract fun bindFlashcardRepository (
        flashcardRepository: FlashcardRepositoryImpl
    ) : FlashcardRepository
}