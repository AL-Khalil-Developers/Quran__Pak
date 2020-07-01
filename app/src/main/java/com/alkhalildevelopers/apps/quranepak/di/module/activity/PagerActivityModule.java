package com.alkhalildevelopers.apps.quranepak.di.module.activity;

import android.content.Context;

import com.alkhalildevelopers.data.core.QuranInfo;
import com.alkhalildevelopers.apps.quranepak.R;
import com.alkhalildevelopers.apps.quranepak.di.ActivityScope;
import com.alkhalildevelopers.apps.quranepak.ui.PagerActivity;
import com.alkhalildevelopers.apps.quranepak.ui.helpers.AyahSelectedListener;
import com.alkhalildevelopers.apps.quranepak.util.QuranScreenInfo;
import com.alkhalildevelopers.apps.quranepak.util.QuranUtils;
import com.alkhalildevelopers.apps.quranepak.util.TranslationUtil;

import dagger.Module;
import dagger.Provides;

@Module
public class PagerActivityModule {
  private final PagerActivity pagerActivity;

  public PagerActivityModule(PagerActivity pagerActivity) {
    this.pagerActivity = pagerActivity;
  }

  @Provides
  AyahSelectedListener provideAyahSelectedListener() {
    return this.pagerActivity;
  }

  @Provides
  @ActivityScope
  String provideImageWidth(QuranScreenInfo screenInfo) {
    return QuranUtils.isDualPages(pagerActivity, screenInfo) ?
        screenInfo.getTabletWidthParam() : screenInfo.getWidthParam();
  }

  @Provides
  @ActivityScope
  TranslationUtil provideTranslationUtil(Context context, QuranInfo quranInfo) {
    return new TranslationUtil(
        context.getResources().getColor(R.color.translation_translator_color),
        quranInfo);
  }
}
