package com.alkhalildevelopers.apps.quranepak.presenter.translation

import com.alkhalildevelopers.data.core.QuranInfo
import com.alkhalildevelopers.apps.quranepak.R
import com.alkhalildevelopers.apps.quranepak.common.LocalTranslation
import com.alkhalildevelopers.apps.quranepak.common.QuranAyahInfo
import com.alkhalildevelopers.data.model.SuraAyah
import com.alkhalildevelopers.apps.quranepak.database.TranslationsDBAdapter
import com.alkhalildevelopers.apps.quranepak.di.QuranPageScope
import com.alkhalildevelopers.apps.quranepak.model.translation.TranslationModel
import com.alkhalildevelopers.apps.quranepak.ui.PagerActivity
import com.alkhalildevelopers.apps.quranepak.util.QuranSettings
import com.alkhalildevelopers.apps.quranepak.util.ShareUtil
import com.alkhalildevelopers.apps.quranepak.util.TranslationUtil
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

@QuranPageScope
internal class TranslationPresenter @Inject internal constructor(translationModel: TranslationModel,
                     private val quranSettings: QuranSettings,
                     translationsAdapter: TranslationsDBAdapter,
                     translationUtil: TranslationUtil,
                     private val shareUtil: ShareUtil,
                     private val quranInfo: QuranInfo,
                     private val pages: Array<Int?>) :
    BaseTranslationPresenter<TranslationPresenter.TranslationScreen>(
        translationModel, translationsAdapter, translationUtil, quranInfo) {

  fun refresh() {
    disposable?.dispose()

    disposable = Observable.fromArray(*pages)
        .flatMap { page ->
          getVerses(quranSettings.wantArabicInTranslationView(),
              getTranslations(quranSettings), quranInfo.getVerseRangeForPage(page))
              .toObservable()
        }
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeWith(object : DisposableObserver<ResultHolder>() {
          override fun onNext(result: ResultHolder) {
            if (translationScreen != null && result.ayahInformation.isNotEmpty()) {
              translationScreen!!.setVerses(
                  getPage(result.ayahInformation), result.translations,
                  result.ayahInformation)
              translationScreen!!.updateScrollPosition()
            }
          }

          override fun onError(e: Throwable) {}

          override fun onComplete() {}
        })
  }

  fun onTranslationAction(activity: PagerActivity,
                          ayah: QuranAyahInfo,
                          translationNames: Array<LocalTranslation>,
                          actionId: Int) {
    when (actionId) {
      R.id.cab_share_ayah_link -> {
        val bounds = SuraAyah(ayah.sura, ayah.ayah)
        activity.shareAyahLink(bounds, bounds)
      }
      R.id.cab_share_ayah_text, R.id.cab_copy_ayah -> {
        val shareText = shareUtil.getShareText(activity, ayah, translationNames)
        if (actionId == R.id.cab_share_ayah_text) {
          shareUtil.shareViaIntent(activity, shareText, R.string.share_ayah_text)
        } else {
          shareUtil.copyToClipboard(activity, shareText)
        }
      }
      R.id.cab_play_from_here -> {
        val start = SuraAyah(ayah.sura, ayah.ayah)
        activity.playFromAyah(start,
            null, getPage(listOf(ayah)), 0, 0, false)
        activity.toggleActionBarVisibility(true);
      }
    }
  }

  private fun getPage(result: List<QuranAyahInfo>): Int {
    val firstPage = pages.first()
    return if (pages.size == 1 && firstPage != null) {
      firstPage
    } else {
      quranInfo.getPageFromSuraAyah(result[0].sura, result[0].ayah)
    }
  }

  interface TranslationScreen {
    fun setVerses(page: Int,
                  translations: Array<LocalTranslation>,
                  verses: List<@JvmSuppressWildcards QuranAyahInfo>)
    fun updateScrollPosition()
  }
}
