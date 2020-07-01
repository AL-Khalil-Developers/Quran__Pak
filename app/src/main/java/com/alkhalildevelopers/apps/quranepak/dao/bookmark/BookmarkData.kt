package com.alkhalildevelopers.apps.quranepak.dao.bookmark

import com.alkhalildevelopers.apps.quranepak.dao.RecentPage
import com.alkhalildevelopers.apps.quranepak.dao.Tag
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BookmarkData(val tags: List<Tag> = emptyList(),
                        val bookmarks: List<Bookmark> = emptyList(),
                        val recentPages: List<RecentPage> = emptyList())
