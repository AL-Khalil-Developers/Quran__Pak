package com.alkhalildevelopers.apps.quranepak.ui.translation;

import com.alkhalildevelopers.apps.quranepak.common.LocalTranslation;
import com.alkhalildevelopers.apps.quranepak.common.QuranAyahInfo;

public interface OnTranslationActionListener {
  void onTranslationAction(QuranAyahInfo ayah, LocalTranslation[] translations, int actionId);
}
