package com.alkhalildevelopers.apps.quranepak.feature.audio

interface VersionableDatabaseChecker {
  fun getVersionForDatabase(path: String): Int
}
