package com.alkhalildevelopers.apps.quranepak.di.component.activity;

import com.alkhalildevelopers.apps.quranepak.di.component.fragment.QuranPageComponent;
import com.alkhalildevelopers.apps.quranepak.di.ActivityScope;
import com.alkhalildevelopers.apps.quranepak.di.module.activity.PagerActivityModule;
import com.alkhalildevelopers.apps.quranepak.ui.PagerActivity;
import com.alkhalildevelopers.apps.quranepak.ui.fragment.AyahTranslationFragment;

import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = PagerActivityModule.class)
public interface PagerActivityComponent {
  // subcomponents
  QuranPageComponent.Builder quranPageComponentBuilder();

  void inject(PagerActivity pagerActivity);
  void inject(AyahTranslationFragment ayahTranslationFragment);

  @Subcomponent.Builder interface Builder {
    Builder withPagerActivityModule(PagerActivityModule pagerModule);
    PagerActivityComponent build();
  }
}
