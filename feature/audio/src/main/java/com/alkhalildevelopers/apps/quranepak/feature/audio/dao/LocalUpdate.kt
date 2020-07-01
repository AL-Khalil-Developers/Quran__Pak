package com.alkhalildevelopers.apps.quranepak.feature.audio.dao

import com.alkhalildevelopers.apps.quranepak.common.audio.QariItem

data class LocalUpdate(val qari: QariItem,
                       val files: List<String> = emptyList(),
                       val needsDatabaseUpgrade: Boolean = false)
