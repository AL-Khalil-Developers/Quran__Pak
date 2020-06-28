package com.quran.labs.androidquran.util

import com.google.common.truth.Truth.assertThat
import com.quran.data.core.QuranInfo
import com.quran.data.model.SuraAyah
import com.quran.data.pageinfo.common.MadaniDataSource
import com.quran.data.source.PageProvider
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when` as whenever

class AudioUtilsTest {

  @Test
  fun testGetLastAyahWithNewSurahOnNextPageForMadani() {
    val pageProviderMock = Mockito.mock(PageProvider::class.java)
    whenever(pageProviderMock.getDataSource())
        .thenReturn(MadaniDataSource())
    val quranInfo = QuranInfo(MadaniDataSource())
    val audioUtils = AudioUtils(quranInfo, Mockito.mock(QuranFileUtils::class.java))
    val lastAyah = audioUtils.getLastAyahToPlay(SuraAyah(109, 1), 603, 1, false)
    Assert.assertNotNull(lastAyah)
    Assert.assertEquals(5, lastAyah!!.ayah.toLong())
    Assert.assertEquals(111, lastAyah.sura.toLong())
  }

  @Test
  fun testSuraTawbaDoesNotNeedBasmallah() {
    val quranInfo = QuranInfo(MadaniDataSource())
    val audioUtils = AudioUtils(quranInfo, Mockito.mock(QuranFileUtils::class.java))

    // start after ayah 1 of sura anfal
    val start = SuraAyah(8, 2)
    // finish in sura tawbah, so no basmallah needed here
    val ending = SuraAyah(9, 100)

    // overall don't need a basmallah
    Assert.assertFalse(audioUtils.doesRequireBasmallah(start, ending))
  }

  @Test
  fun testNeedBasmallahAcrossRange() {
    val quranInfo = QuranInfo(MadaniDataSource())
    val audioUtils = AudioUtils(quranInfo, Mockito.mock(QuranFileUtils::class.java))
    val start = SuraAyah(8, 1)
    val ending = SuraAyah(10, 2)
    // should need a basmallah due to 10:1
    Assert.assertTrue(audioUtils.doesRequireBasmallah(start, ending))
  }

  @Test
  fun testLastAyahForFirstAyahWithPageDownload() {
    val audioUtils = AudioUtils(QuranInfo(MadaniDataSource()),
        Mockito.mock(QuranFileUtils::class.java))
    val start = SuraAyah(56, 51)
    val end = audioUtils.getLastAyahToPlay(start, 536, 1, false)
    assertThat(end).isEqualTo(SuraAyah(56, 76))
  }
}
