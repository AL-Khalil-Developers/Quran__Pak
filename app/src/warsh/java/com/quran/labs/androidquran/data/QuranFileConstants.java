package com.alkhalildevelopers.apps.quranepak.data;

import com.alkhalildevelopers.apps.quranepak.database.DatabaseHandler;
import com.alkhalildevelopers.apps.quranepak.ui.util.TypefaceManager;

public class QuranFileConstants {
  // server urls
  public static final int FONT_TYPE = TypefaceManager.TYPE_UTHMANI_HAFS;

  // arabic database
  public static final String ARABIC_DATABASE = "alkhalildevelopers.ar.db";
  public static final String ARABIC_SHARE_TABLE = DatabaseHandler.ARABIC_TEXT_TABLE;
  public static final boolean ARABIC_SHARE_TEXT_HAS_BASMALLAH = true;

  // data
  public static final boolean ARE_PAGES_BUNDLED = true;
}
