package com.alkhalildevelopers.apps.quranepak.database

import com.alkhalildevelopers.apps.quranepak.feature.audio.VersionableDatabaseChecker
import javax.inject.Inject

class AudioDatabaseVersionChecker @Inject constructor() : VersionableDatabaseChecker {
  override fun getVersionForDatabase(path: String): Int {
    return SuraTimingDatabaseHandler.getDatabaseHandler(path).version
  }
}
