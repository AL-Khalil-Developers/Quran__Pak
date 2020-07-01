package com.alkhalildevelopers.apps.quranepak.data;

import android.os.Build;

import com.alkhalildevelopers.apps.quranepak.database.DatabaseHandler;
import com.alkhalildevelopers.apps.quranepak.ui.util.TypefaceManager;

public class QuranFileConstants {
  // server urls
  public static final int FONT_TYPE = TypefaceManager.TYPE_NOOR_HAYAH;

  // arabic database
  public static final String ARABIC_DATABASE =
      Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR1 ?
          "alkhalildevelopers.ar_naskh.db" : "alkhalildevelopers.ar.db";
  public static final String ARABIC_SHARE_TABLE = DatabaseHandler.ARABIC_TEXT_TABLE;

  public static final boolean ARABIC_SHARE_TEXT_HAS_BASMALLAH = false;
  public static final boolean ARE_PAGES_BUNDLED = false;
}
