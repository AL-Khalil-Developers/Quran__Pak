package com.alkhalildevelopers.apps.quranepak.feature.audio.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AudioFileUpdate(val filename: String, @Json(name = "md5") val md5sum: String)
