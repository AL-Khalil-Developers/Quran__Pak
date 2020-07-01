package com.alkhalildevelopers.apps.quranepak.core.worker.di

import com.alkhalildevelopers.apps.quranepak.feature.audio.api.AudioUpdateService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit.Builder
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
object AudioUpdateModule {

  @Provides
  fun provideAudioUpdateService(): AudioUpdateService {
    val retrofit = Builder()
        .baseUrl("https://android.alkhalildevelopers.com/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
    return retrofit.create(AudioUpdateService::class.java)
  }
}
