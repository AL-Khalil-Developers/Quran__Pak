package com.alkhalildevelopers.apps.quranepak.pageselect

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.crashlytics.android.answers.Answers
import com.crashlytics.android.answers.CustomEvent
import com.alkhalildevelopers.apps.quranepak.QuranApplication
import com.alkhalildevelopers.apps.quranepak.QuranDataActivity
import com.alkhalildevelopers.apps.quranepak.R
import com.alkhalildevelopers.apps.quranepak.ui.helpers.QuranDisplayHelper
import com.alkhalildevelopers.apps.quranepak.util.QuranSettings
import javax.inject.Inject

class PageSelectActivity : AppCompatActivity() {
  @Inject lateinit var presenter : PageSelectPresenter
  @Inject lateinit var quranSettings: QuranSettings

  private lateinit var adapter : PageSelectAdapter
  private lateinit var viewPager: ViewPager

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    (application as QuranApplication).applicationComponent.inject(this)

    Answers.getInstance().logCustom(CustomEvent("pageSelectionActivityVisited"))

    setContentView(R.layout.page_select)

    val display = windowManager.defaultDisplay
    val width = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
      QuranDisplayHelper.getWidthKitKat(display)
    else display.width

    adapter = PageSelectAdapter(LayoutInflater.from(this), width) {
      onPageTypeSelected(it)
    }

    viewPager = findViewById(R.id.pager)
    viewPager.adapter = adapter

    // let the next and previous pages be slightly visible
    val pageMargin = resources.getDimensionPixelSize(R.dimen.page_margin)
    val pagerPadding = pageMargin * 2
    viewPager.setPadding(pagerPadding, 0, pagerPadding, 0)
    viewPager.clipToPadding = false
    viewPager.pageMargin = pageMargin
  }

  override fun onResume() {
    super.onResume()
    presenter.bind(this)
  }

  override fun onPause() {
    presenter.unbind(this)
    super.onPause()
  }

  override fun onDestroy() {
    adapter.cleanUp()
    super.onDestroy()
  }

  fun onUpdatedData(data: List<PageTypeItem>) {
    adapter.replaceItems(data, viewPager)
  }

  private fun onPageTypeSelected(type: String) {
    val pageType = quranSettings.pageType
    if (pageType != type) {
      quranSettings.removeDidDownloadPages()
      quranSettings.pageType = type
      Answers.getInstance().logCustom(
          CustomEvent("pageTypeChanged").putCustomAttribute("pageType", type))
      val intent = Intent(this, QuranDataActivity::class.java).apply {
        addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
      }
      startActivity(intent)
    }
    finish()
  }
}