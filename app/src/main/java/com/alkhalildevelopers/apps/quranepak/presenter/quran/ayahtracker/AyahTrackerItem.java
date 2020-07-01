package com.alkhalildevelopers.apps.quranepak.presenter.quran.ayahtracker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.alkhalildevelopers.apps.quranepak.dao.bookmark.Bookmark;
import com.alkhalildevelopers.data.model.SuraAyah;
import com.alkhalildevelopers.apps.quranepak.ui.helpers.HighlightType;
import com.alkhalildevelopers.apps.quranepak.widgets.AyahToolBar;
import com.alkhalildevelopers.page.common.data.AyahCoordinates;
import com.alkhalildevelopers.page.common.data.PageCoordinates;

import java.util.List;
import java.util.Set;

public class AyahTrackerItem<T> {
  final int page;
  @NonNull final T ayahView;

  AyahTrackerItem(int page, @NonNull T ayahView) {
    this.page = page;
    this.ayahView = ayahView;
  }

  void onSetPageBounds(PageCoordinates pageCoordinates) {
  }

  void onSetAyahCoordinates(AyahCoordinates ayahCoordinates) {
  }

  void onSetAyahBookmarks(@NonNull List<Bookmark> bookmarks) {
  }

  boolean onHighlightAyah(int page, int sura, int ayah, HighlightType type, boolean scrollToAyah) {
    return false;
  }

  void onHighlightAyat(int page, Set<String> ayahKeys, HighlightType type) {
  }

  void onUnHighlightAyah(int page, int sura, int ayah, HighlightType type) {
  }

  void onUnHighlightAyahType(HighlightType type) {
  }

  @Nullable
  AyahToolBar.AyahToolBarPosition getToolBarPosition(int page, int sura, int ayah,
                                                     int toolBarWidth, int toolBarHeight) {
    return null;
  }

  @Nullable
  SuraAyah getAyahForPosition(int page, float x, float y) {
    return null;
  }
}
