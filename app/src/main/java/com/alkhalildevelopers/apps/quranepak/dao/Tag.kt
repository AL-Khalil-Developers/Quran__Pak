package com.alkhalildevelopers.apps.quranepak.dao

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Tag(val id: Long, val name: String)
