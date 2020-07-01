package com.alkhalildevelopers.apps.quranepak.ui.helpers;

import android.graphics.drawable.BitmapDrawable;

import com.alkhalildevelopers.apps.quranepak.common.Response;

public interface PageDownloadListener {
  void onLoadImageResponse(BitmapDrawable drawable, Response response);
}
