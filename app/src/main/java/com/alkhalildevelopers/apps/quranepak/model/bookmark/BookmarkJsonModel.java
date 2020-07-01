package com.alkhalildevelopers.apps.quranepak.model.bookmark;

import androidx.annotation.NonNull;

import com.alkhalildevelopers.apps.quranepak.dao.bookmark.BookmarkData;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.IOException;

import javax.inject.Inject;

import okio.BufferedSink;
import okio.BufferedSource;

class BookmarkJsonModel {
  private final JsonAdapter<BookmarkData> jsonAdapter;

  @Inject
  BookmarkJsonModel() {
    Moshi moshi = new Moshi.Builder().build();
    jsonAdapter = moshi.adapter(BookmarkData.class);
  }

  void toJson(BufferedSink sink, BookmarkData bookmarks) throws IOException {
    jsonAdapter.toJson(sink, bookmarks);
  }

  @NonNull
  BookmarkData fromJson(BufferedSource jsonSource) throws IOException {
    return jsonAdapter.fromJson(jsonSource);
  }
}
