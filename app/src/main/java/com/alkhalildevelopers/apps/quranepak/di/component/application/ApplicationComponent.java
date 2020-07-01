package com.alkhalildevelopers.apps.quranepak.di.component.application;

import com.alkhalildevelopers.data.page.provider.QuranPageModule;
import com.alkhalildevelopers.apps.quranepak.QuranApplication;
import com.alkhalildevelopers.apps.quranepak.QuranDataActivity;
import com.alkhalildevelopers.apps.quranepak.QuranForwarderActivity;
import com.alkhalildevelopers.apps.quranepak.QuranImportActivity;
import com.alkhalildevelopers.apps.quranepak.SearchActivity;
import com.alkhalildevelopers.apps.quranepak.di.component.activity.PagerActivityComponent;
import com.alkhalildevelopers.apps.quranepak.data.QuranDataModule;
import com.alkhalildevelopers.apps.quranepak.data.QuranDataProvider;
import com.alkhalildevelopers.apps.quranepak.di.module.application.ApplicationModule;
import com.alkhalildevelopers.apps.quranepak.di.module.application.DatabaseModule;
import com.alkhalildevelopers.common.networking.NetworkModule;
import com.alkhalildevelopers.apps.quranepak.pageselect.PageSelectActivity;
import com.alkhalildevelopers.apps.quranepak.service.AudioService;
import com.alkhalildevelopers.apps.quranepak.service.QuranDownloadService;
import com.alkhalildevelopers.apps.quranepak.ui.AudioManagerActivity;
import com.alkhalildevelopers.apps.quranepak.ui.QuranActivity;
import com.alkhalildevelopers.apps.quranepak.ui.SheikhAudioManagerActivity;
import com.alkhalildevelopers.apps.quranepak.ui.TranslationManagerActivity;
import com.alkhalildevelopers.apps.quranepak.ui.fragment.AddTagDialog;
import com.alkhalildevelopers.apps.quranepak.ui.fragment.AyahPlaybackFragment;
import com.alkhalildevelopers.apps.quranepak.ui.fragment.BookmarksFragment;
import com.alkhalildevelopers.apps.quranepak.ui.fragment.JumpFragment;
import com.alkhalildevelopers.apps.quranepak.ui.fragment.JuzListFragment;
import com.alkhalildevelopers.apps.quranepak.ui.fragment.QuranAdvancedSettingsFragment;
import com.alkhalildevelopers.apps.quranepak.ui.fragment.QuranSettingsFragment;
import com.alkhalildevelopers.apps.quranepak.ui.fragment.SuraListFragment;
import com.alkhalildevelopers.apps.quranepak.ui.fragment.TagBookmarkDialog;

import com.alkhalildevelopers.apps.quranepak.core.worker.di.WorkerModule;
import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
    ApplicationModule.class,
    DatabaseModule.class,
    NetworkModule.class,
    QuranDataModule.class,
    QuranPageModule.class,
    WorkerModule.class
} )
public interface ApplicationComponent {
  // subcomponents
  PagerActivityComponent.Builder pagerActivityComponentBuilder();

  // application
  void inject(QuranApplication quranApplication);

  // content provider
  void inject(QuranDataProvider quranDataProvider);

  // services
  void inject(AudioService audioService);
  void inject(QuranDownloadService quranDownloadService);

  // activities
  void inject(QuranActivity quranActivity);
  void inject(QuranDataActivity quranDataActivity);
  void inject(QuranImportActivity quranImportActivity);
  void inject(AudioManagerActivity audioManagerActivity);
  void inject(SheikhAudioManagerActivity sheikhAudioManagerActivity);
  void inject(QuranForwarderActivity quranForwarderActivity);
  void inject(SearchActivity searchActivity);
  void inject(PageSelectActivity pageSelectActivity);

  // fragments
  void inject(BookmarksFragment bookmarksFragment);
  void inject(QuranSettingsFragment fragment);
  void inject(TranslationManagerActivity translationManagerActivity);
  void inject(QuranAdvancedSettingsFragment quranAdvancedSettingsFragment);
  void inject(SuraListFragment suraListFragment);
  void inject(JuzListFragment juzListFragment);
  void inject(AyahPlaybackFragment ayahPlaybackFragment);
  void inject(JumpFragment jumpFragment);

  // dialogs
  void inject(TagBookmarkDialog tagBookmarkDialog);
  void inject(AddTagDialog addTagDialog);
}
