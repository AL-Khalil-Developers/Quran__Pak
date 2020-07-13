package com.alkhalildevelopers.quranepak.personal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import com.alkhalildevelopers.apps.quranepak.R;
import com.alkhalildevelopers.apps.quranepak.ui.QuranActivity;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class ChannelPageActivity extends AppCompatActivity {
  SwipeRefreshLayout swipeRefreshLayout;
  WebView webView;
  ViewGroup bannerADContainer;
  AdView adView;
  @SuppressLint("SetJavaScriptEnabled")
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_channel_page);
    MobileAds.initialize(this);

    String AdmobBannerAdUnit = getResources().getString(R.string.AdmobBannerAdUnit);

    if (!AdmobBannerAdUnit.isEmpty()){
      adView = new AdView(ChannelPageActivity.this);
      adView.setAdSize(AdSize.BANNER);
      adView.setAdUnitId(AdmobBannerAdUnit);
      Handler handler = new Handler();
      handler.postDelayed(new Runnable() {
        @Override
        public void run() {
          bannerADContainer = (LinearLayout) findViewById(R.id.bannerAdContainer);
          bannerADContainer.addView(adView);
          adView.loadAd(new AdRequest.Builder().build());
        }
      },5000);
    }
    swipeRefreshLayout = findViewById(R.id.channelSwipeableLayout);
    webView = findViewById(R.id.channelWebView);
    webView.getSettings().setJavaScriptEnabled(true);
    webView.getSettings().setLoadsImagesAutomatically(true);
    webView.loadUrl(getResources().getString(R.string.channelUrl));

    swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
      @Override
      public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        webView.reload();
      }
    });

    webView.setWebViewClient(new WebViewClient(){
      @Override
      public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        swipeRefreshLayout.setRefreshing(true);
      }

      @Override
      public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        swipeRefreshLayout.setRefreshing(false);
      }
    });

    webView.setWebChromeClient(new WebChromeClient(){
      @Override
      public void onProgressChanged(WebView view, int newProgress) {
        super.onProgressChanged(view, newProgress);
        if (newProgress > 50){
          swipeRefreshLayout.setRefreshing(false);
        }
      }
    });

    webView.setOnKeyListener(new View.OnKeyListener() {
      @Override
      public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == MotionEvent.ACTION_UP){
          if(webView.canGoBack()){
            webView.goBack();
          }else {
            finish();
          }
        }
        int action = event.getAction();
        AudioManager audioManager =(AudioManager) getSystemService(Context.AUDIO_SERVICE);
        switch (keyCode) {
          case KeyEvent.KEYCODE_VOLUME_UP:
            if (action == KeyEvent.ACTION_DOWN) {
              audioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC,AudioManager.ADJUST_RAISE,AudioManager.FLAG_SHOW_UI);
            }
            return true;
          case KeyEvent.KEYCODE_VOLUME_DOWN:
            if (action == KeyEvent.ACTION_DOWN) {
              audioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC,AudioManager.ADJUST_LOWER,AudioManager.FLAG_SHOW_UI);
            }
            return true;
        }
        return true;
      }
    });


  }
}
