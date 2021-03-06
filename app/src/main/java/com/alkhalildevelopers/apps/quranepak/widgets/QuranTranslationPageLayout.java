package com.alkhalildevelopers.apps.quranepak.widgets;

import android.content.Context;
import android.graphics.Color;
import androidx.annotation.NonNull;
import android.view.View;

import com.alkhalildevelopers.apps.quranepak.R;
import com.alkhalildevelopers.apps.quranepak.ui.translation.TranslationView;
import com.alkhalildevelopers.apps.quranepak.util.QuranSettings;

public class QuranTranslationPageLayout extends QuranPageLayout {
  private TranslationView translationView;

  public QuranTranslationPageLayout(Context context) {
    super(context);
    isFullWidth = true;
  }

  @Override
  protected View generateContentView(Context context, boolean isLandscape) {
    translationView = new TranslationView(context);
    return translationView;
  }

  @Override
  public void updateView(@NonNull QuranSettings quranSettings) {
    super.updateView(quranSettings);
    translationView.refresh(quranSettings);
  }

  @Override
  protected void updateBackground(boolean nightMode, QuranSettings quranSettings) {
    if (nightMode) {
      setBackgroundResource(R.color.translation_background_color_night);
    } else {
      setBackgroundColor(Color.WHITE);
    }
  }

  @Override
  protected boolean shouldWrapWithScrollView() {
    return false;
  }

  public TranslationView getTranslationView() {
    return translationView;
  }
}
