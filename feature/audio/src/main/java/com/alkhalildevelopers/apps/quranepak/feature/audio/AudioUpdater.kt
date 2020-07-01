package com.alkhalildevelopers.apps.quranepak.feature.audio

import com.alkhalildevelopers.apps.quranepak.common.audio.QariItem
import com.alkhalildevelopers.apps.quranepak.feature.audio.api.AudioSetUpdate
import com.alkhalildevelopers.apps.quranepak.feature.audio.dao.LocalUpdate
import com.alkhalildevelopers.apps.quranepak.feature.audio.util.AudioFileChecker
import com.alkhalildevelopers.apps.quranepak.feature.audio.util.HashLogger

object AudioUpdater {
  fun computeUpdates(updates: List<AudioSetUpdate>,
                     qaris: List<QariItem>,
                     audioFileChecker: AudioFileChecker,
                     databaseChecker: VersionableDatabaseChecker,
                     hashLogger: HashLogger
  ): List<LocalUpdate> {
    return updates.map { it to findCorrespondingQari(it, qaris) }
        .filter { (_, qari) -> qari != null && audioFileChecker.isQariOnFilesystem(qari) }
        .map { (audioSetUpdate, qariItem) ->
          makeLocalUpdate(audioFileChecker, audioSetUpdate, databaseChecker, hashLogger, qariItem!!)
        }
        .filter { it.files.isNotEmpty() || it.needsDatabaseUpgrade }
  }

  private fun findCorrespondingQari(
    audioSetUpdate: AudioSetUpdate,
    qaris: List<QariItem>
  ): QariItem? {
    return qaris.firstOrNull { it.url == audioSetUpdate.path }
  }

  private fun makeLocalUpdate(audioFileChecker: AudioFileChecker,
                              audioSetUpdate: AudioSetUpdate,
                              databaseChecker: VersionableDatabaseChecker,
                              hashLogger: HashLogger,
                              qari: QariItem): LocalUpdate {
    val existingFiles =
      audioSetUpdate.files.filter { audioFileChecker.doesFileExistForQari(qari, it.filename) }
    val filesToUpdate = existingFiles
          .filter { !audioFileChecker.doesHashMatchForQariFile(qari, it.filename, it.md5sum) }
          .map { it.filename }

    if (existingFiles.size > filesToUpdate.size) {
      hashLogger.logEvent(existingFiles.size - filesToUpdate.size)
    }

    val needsDatabaseUpgrade = if (audioSetUpdate.databaseVersion == null) {
      false
    } else {
      val databasePath = audioFileChecker.getDatabasePathForQari(qari)
      // gapless qaris should always have a database path
      databasePath != null && audioFileChecker.doesDatabaseExist(databasePath) &&
          (databaseChecker.getVersionForDatabase(databasePath) != audioSetUpdate.databaseVersion)
    }
    return LocalUpdate(qari, filesToUpdate, needsDatabaseUpgrade)
  }
}
