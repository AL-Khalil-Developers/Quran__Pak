package com.alkhalildevelopers.apps.quranepak.feature.audio.util

import java.io.File

interface HashCalculator {
  fun calculateHash(file: File): String
}
