package com.alkhalildevelopers.apps.quranepak.data;

import com.alkhalildevelopers.data.source.PageProvider;

import com.alkhalildevelopers.data.source.QuranDataSource;
import java.util.Map;

import dagger.Module;
import dagger.Provides;

@Module
public class QuranDataModule {

  @Provides
  static PageProvider provideQuranPageProvider(Map<String, PageProvider> providers) {
    return providers.get("naskh");
  }

  @Provides
  static QuranDataSource provideQuranDataSource(PageProvider pageProvider) {
    return pageProvider.getDataSource();
  }
}
