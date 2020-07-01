package com.alkhalildevelopers.apps.quranepak.dao

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RecentPage(val page: Int, val timestamp: Long)
