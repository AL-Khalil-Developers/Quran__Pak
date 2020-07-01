package com.alkhalildevelopers.apps.quranepak.model.translation;

import android.content.Context;

import com.alkhalildevelopers.apps.quranepak.common.QuranText;
import com.alkhalildevelopers.apps.quranepak.data.QuranDataProvider;
import com.alkhalildevelopers.data.model.VerseRange;
import com.alkhalildevelopers.apps.quranepak.database.DatabaseHandler;
import com.alkhalildevelopers.apps.quranepak.di.ActivityScope;
import com.alkhalildevelopers.apps.quranepak.util.QuranFileUtils;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

@ActivityScope
public class TranslationModel {
  private final Context appContext;
  private final QuranFileUtils quranFileUtils;

  @Inject
  TranslationModel(Context appContext, QuranFileUtils quranFileUtils) {
    this.appContext = appContext;
    this.quranFileUtils = quranFileUtils;
  }

  public Single<List<QuranText>> getArabicFromDatabase(VerseRange verses) {
    return getVersesFromDatabase(verses,
        QuranDataProvider.QURAN_ARABIC_DATABASE, DatabaseHandler.TextType.ARABIC);
  }

  public Single<List<QuranText>> getTranslationFromDatabase(VerseRange verses, String db) {
    return getVersesFromDatabase(verses, db, DatabaseHandler.TextType.TRANSLATION);
  }

  private Single<List<QuranText>> getVersesFromDatabase(VerseRange verses,
                                                        String database,
                                                        @DatabaseHandler.TextType int type) {
    return Single.fromCallable(() -> {
      DatabaseHandler databaseHandler =
          DatabaseHandler.getDatabaseHandler(appContext, database, quranFileUtils);
      return databaseHandler.getVerses(verses, type);
    });
  }
}
