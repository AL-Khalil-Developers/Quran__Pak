package com.alkhalildevelopers.apps.quranepak.util

import android.content.Context
import android.content.res.Resources
import java.util.Locale

object LocaleUtil {
  fun getLocale(context: Context): Locale {
    val isArabic = QuranSettings.getInstance(context.applicationContext).isArabicNames
    return when {
      isArabic -> Locale("ar")
      else -> Resources.getSystem().configuration.locale
    }
  }
}
