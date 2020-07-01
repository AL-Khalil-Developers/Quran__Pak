package com.alkhalildevelopers.apps.quranepak.di.module.fragment;

import dagger.Module;
import dagger.Provides;

@Module
public class QuranPageModule {
  private final Integer[] pages;

  public QuranPageModule(Integer... pages) {
    this.pages = pages;
  }

  @Provides
  Integer[] providePages() {
    return this.pages;
  }
}
