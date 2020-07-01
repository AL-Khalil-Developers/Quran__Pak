package com.alkhalildevelopers.apps.quranepak.ui.util;

import android.view.MotionEvent;

import com.alkhalildevelopers.apps.quranepak.ui.helpers.AyahSelectedListener;

public interface PageController {
  boolean handleTouchEvent(MotionEvent event, AyahSelectedListener.EventType eventType, int page);
  void handleRetryClicked();
  void onScrollChanged(int x, int y, int oldx, int oldy);
}
