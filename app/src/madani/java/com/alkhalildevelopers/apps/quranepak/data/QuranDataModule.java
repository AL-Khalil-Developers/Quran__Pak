package com.alkhalildevelopers.apps.quranepak.data;

import com.alkhalildevelopers.data.source.PageProvider;
import com.alkhalildevelopers.data.source.QuranDataSource;
import com.alkhalildevelopers.apps.quranepak.util.QuranSettings;
import dagger.Module;
import dagger.Provides;
import java.util.Map;

@Module
public class QuranDataModule {

  @Provides
  static PageProvider provideQuranPageProvider(Map<String, PageProvider> providers,
                                               QuranSettings quranSettings) {
    final String key = quranSettings.getPageType();
    final String fallbackType = "madani";
    if (key == null) {
      quranSettings.setPageType(fallbackType);
    }
    return providers.get(key == null ? fallbackType : key);
  }

  @Provides
  static QuranDataSource provideQuranDataSource(PageProvider pageProvider) {
    return pageProvider.getDataSource();
  }
}
