package com.alkhalildevelopers.apps.quranepak.dao.bookmark

import com.alkhalildevelopers.apps.quranepak.dao.Tag
import com.alkhalildevelopers.apps.quranepak.ui.helpers.QuranRow

data class BookmarkResult(val rows: List<QuranRow>, val tagMap: Map<Long, Tag>)
