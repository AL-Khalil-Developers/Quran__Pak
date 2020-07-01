package com.alkhalildevelopers.apps.quranepak.extension

import com.alkhalildevelopers.data.model.SuraAyah

fun SuraAyah.requiresBasmallah(): Boolean {
  return ayah == 1 && sura != 1 && sura != 9
}
