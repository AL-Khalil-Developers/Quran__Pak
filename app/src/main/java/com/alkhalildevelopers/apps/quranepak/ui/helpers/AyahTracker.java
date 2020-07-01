package com.alkhalildevelopers.apps.quranepak.ui.helpers;

import com.alkhalildevelopers.apps.quranepak.widgets.AyahToolBar;

import java.util.Set;

public interface AyahTracker {
  void highlightAyah(int sura, int ayah, HighlightType type, boolean scrollToAyah);
  void highlightAyat(int page, Set<String> ayahKeys, HighlightType type);
  void unHighlightAyah(int sura, int ayah, HighlightType type);
  void unHighlightAyahs(HighlightType type);
  AyahToolBar.AyahToolBarPosition getToolBarPosition(int sura, int ayah,
      int toolBarWidth, int toolBarHeight);
}
