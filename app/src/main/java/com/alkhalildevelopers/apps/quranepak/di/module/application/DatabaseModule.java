package com.alkhalildevelopers.apps.quranepak.di.module.application;

import android.content.Context;

import com.alkhalildevelopers.data.core.QuranInfo;
import com.alkhalildevelopers.apps.quranepak.database.BookmarksDBAdapter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {

  @Provides
  @Singleton
  static BookmarksDBAdapter provideBookmarkDatabaseAdapter(Context context, QuranInfo quranInfo) {
    return new BookmarksDBAdapter(context, quranInfo.getNumberOfPages());
  }
}
