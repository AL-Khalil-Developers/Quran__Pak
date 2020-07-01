package com.alkhalildevelopers.apps.quranepak.presenter.translation;

import com.alkhalildevelopers.data.core.QuranInfo;
import com.alkhalildevelopers.apps.quranepak.common.LocalTranslation;
import com.alkhalildevelopers.apps.quranepak.common.QuranAyahInfo;
import com.alkhalildevelopers.data.model.VerseRange;
import com.alkhalildevelopers.apps.quranepak.database.TranslationsDBAdapter;
import com.alkhalildevelopers.apps.quranepak.model.translation.TranslationModel;
import com.alkhalildevelopers.apps.quranepak.util.QuranSettings;
import com.alkhalildevelopers.apps.quranepak.util.TranslationUtil;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;

public class InlineTranslationPresenter extends
    BaseTranslationPresenter<InlineTranslationPresenter.TranslationScreen> {
  private final QuranSettings quranSettings;

  @Inject
  InlineTranslationPresenter(TranslationModel translationModel,
                             TranslationsDBAdapter dbAdapter,
                             TranslationUtil translationUtil,
                             QuranSettings quranSettings,
                             QuranInfo quranInfo) {
    super(translationModel, dbAdapter, translationUtil, quranInfo);
    this.quranSettings = quranSettings;
  }

  public void refresh(VerseRange verseRange) {
    if (getDisposable() != null) {
      getDisposable().dispose();
    }

    setDisposable(getVerses(false, getTranslations(quranSettings), verseRange)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeWith(new DisposableSingleObserver<ResultHolder>() {
          @Override
          public void onSuccess(ResultHolder result) {
            if (getTranslationScreen() != null) {
              getTranslationScreen()
                  .setVerses(result.getTranslations(), result.getAyahInformation());
            }
          }

          @Override
          public void onError(Throwable e) {
          }
        }));
  }

  public interface TranslationScreen {
    void setVerses(@NonNull LocalTranslation[] translations, @NonNull List<QuranAyahInfo> verses);
  }
}
