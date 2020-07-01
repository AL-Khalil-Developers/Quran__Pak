package com.alkhalildevelopers.apps.quranepak.di.component.fragment;

import com.alkhalildevelopers.apps.quranepak.di.QuranPageScope;
import com.alkhalildevelopers.apps.quranepak.di.module.fragment.QuranPageModule;
import com.alkhalildevelopers.apps.quranepak.ui.fragment.QuranPageFragment;
import com.alkhalildevelopers.apps.quranepak.ui.fragment.TabletFragment;
import com.alkhalildevelopers.apps.quranepak.ui.fragment.TranslationFragment;

import dagger.Subcomponent;

@QuranPageScope
@Subcomponent(modules = QuranPageModule.class)
public interface QuranPageComponent {
  void inject(QuranPageFragment quranPageFragment);
  void inject(TabletFragment tabletFragment);
  void inject(TranslationFragment translationFragment);

  @Subcomponent.Builder interface Builder {
    Builder withQuranPageModule(QuranPageModule quranPageModule);
    QuranPageComponent build();
  }
}
