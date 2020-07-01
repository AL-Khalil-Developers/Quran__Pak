package com.alkhalildevelopers.apps.quranepak.common

import com.alkhalildevelopers.data.model.SuraAyah

data class TranslationMetadata(val sura: Int,
                               val ayah: Int,
                               val text: CharSequence,
                               val link: SuraAyah? = null)
