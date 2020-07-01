package com.alkhalildevelopers.apps.quranepak.core.worker.di

import com.alkhalildevelopers.apps.quranepak.core.worker.WorkerKey
import com.alkhalildevelopers.apps.quranepak.core.worker.WorkerTaskFactory
import com.alkhalildevelopers.apps.quranepak.worker.AudioUpdateWorker
import com.alkhalildevelopers.apps.quranepak.worker.MissingPageDownloadWorker
import com.alkhalildevelopers.apps.quranepak.worker.PartialPageCheckingWorker
import com.alkhalildevelopers.apps.quranepak.worker.PartialPageCheckingWorker.Factory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [ AudioUpdateModule::class ])
abstract class WorkerModule {

  @Binds
  @IntoMap
  @WorkerKey(PartialPageCheckingWorker::class)
  abstract fun bindPartialPageCheckingWorkerFactory(
    workerFactory: Factory
  ): WorkerTaskFactory

  @Binds
  @IntoMap
  @WorkerKey(MissingPageDownloadWorker::class)
  abstract fun bindMissingPageDownloadWorkerFactory(
    workerFactory: MissingPageDownloadWorker.Factory
  ): WorkerTaskFactory

  @Binds
  @IntoMap
  @WorkerKey(AudioUpdateWorker::class)
  abstract fun bindAudioUpdateWorkerFactory(
    workerFactory: AudioUpdateWorker.Factory
  ): WorkerTaskFactory
}
