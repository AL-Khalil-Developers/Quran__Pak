package com.alkhalildevelopers.apps.quranepak.dao.audio

import com.alkhalildevelopers.data.model.SuraAyah

data class AudioPlaybackInfo(val currentAyah: SuraAyah,
                             val timesPlayed: Int = 1,
                             val rangePlayedTimes: Int = 1,
                             val shouldPlayBasmallah: Boolean = false)
