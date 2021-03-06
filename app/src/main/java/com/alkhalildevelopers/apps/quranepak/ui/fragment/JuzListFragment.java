package com.alkhalildevelopers.apps.quranepak.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alkhalildevelopers.data.core.QuranInfo;
import com.alkhalildevelopers.apps.quranepak.QuranApplication;
import com.alkhalildevelopers.apps.quranepak.R;
import com.alkhalildevelopers.apps.quranepak.data.Constants;
import com.alkhalildevelopers.apps.quranepak.data.QuranDisplayData;
import com.alkhalildevelopers.apps.quranepak.ui.QuranActivity;
import com.alkhalildevelopers.apps.quranepak.ui.helpers.QuranListAdapter;
import com.alkhalildevelopers.apps.quranepak.ui.helpers.QuranRow;
import com.alkhalildevelopers.apps.quranepak.util.QuranSettings;
import com.alkhalildevelopers.apps.quranepak.util.QuranUtils;
import com.alkhalildevelopers.apps.quranepak.widgets.JuzView;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;

import static com.alkhalildevelopers.apps.quranepak.data.Constants.JUZ2_COUNT;

/**
 * Fragment that displays a list of all Juz (using {@link QuranListAdapter}, each divided into
 * 8 parts (with headings for each Juz).
 * When a Juz part is selected (or a Juz heading), {@link QuranActivity#jumpTo(int)} is called to
 * jump to that page.
 */
public class JuzListFragment extends Fragment {
  private static int[] sEntryTypes = {
      JuzView.TYPE_JUZ, JuzView.TYPE_QUARTER,
      JuzView.TYPE_HALF, JuzView.TYPE_THREE_QUARTERS };

  private RecyclerView mRecyclerView;
  private Disposable disposable;

  @Inject QuranInfo quranInfo;
  @Inject QuranDisplayData quranDisplayData;

  public static JuzListFragment newInstance() {
    return new JuzListFragment();
  }

  @Override
  public View onCreateView(LayoutInflater inflater,
      ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.quran_list, container, false);

    final Context context = getActivity();
    mRecyclerView = view.findViewById(R.id.recycler_view);
    mRecyclerView.setHasFixedSize(true);
    mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
    mRecyclerView.setItemAnimator(new DefaultItemAnimator());

    final QuranListAdapter adapter =
        new QuranListAdapter(context, mRecyclerView, getJuz2List(), false);
    mRecyclerView.setAdapter(adapter);
    return view;
  }

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    ((QuranApplication) context.getApplicationContext()).getApplicationComponent().inject(this);
  }

  @Override
  public void onPause() {
    if (disposable != null) {
      disposable.dispose();
    }
    super.onPause();
  }

  @Override
  public void onResume() {
    final Activity activity = getActivity();

    if (activity instanceof QuranActivity) {
      disposable = ((QuranActivity) activity).getLatestPageObservable()
          .first(Constants.NO_PAGE)
          .observeOn(AndroidSchedulers.mainThread())
          .subscribeWith(new DisposableSingleObserver<Integer>() {
            @Override
            public void onSuccess(Integer recentPage) {
              if (recentPage != Constants.NO_PAGE) {
                int juz = quranInfo.getJuzFromPage(recentPage);
                int position = (juz - 1) * 9;
                mRecyclerView.scrollToPosition(position);
              }
            }

            @Override
            public void onError(Throwable e) {
            }
          });
    }

    QuranSettings settings = QuranSettings.getInstance(activity);
    if (settings.isArabicNames()) {
      updateScrollBarPositionHoneycomb();
    }

    super.onResume();
  }

  private void updateScrollBarPositionHoneycomb() {
    mRecyclerView.setVerticalScrollbarPosition(View.SCROLLBAR_POSITION_LEFT);
  }

  private QuranRow[] getJuz2List() {
    Activity activity = getActivity();
    Resources res = getResources();
    String[] quarters = res.getStringArray(R.array.quarter_prefix_array);
    QuranRow[] elements = new QuranRow[JUZ2_COUNT * (8 + 1)];

    int ctr = 0;
    for (int i = 0; i < (8 * JUZ2_COUNT); i++) {
      int[] pos = quranInfo.getQuarterByIndex(i);
      int page = quranInfo.getPageFromSuraAyah(pos[0], pos[1]);

      if (i % 8 == 0) {
        int juz = 1 + (i / 8);
        final String juzTitle = activity.getString(R.string.juz2_description,
            QuranUtils.getLocalizedNumber(activity, juz));
        final QuranRow.Builder builder = new QuranRow.Builder()
            .withType(QuranRow.HEADER)
            .withText(juzTitle)
            .withPage(quranInfo.getStartingPageForJuz(juz));
        elements[ctr++] = builder.build();
      }

      final String metadata = getString(R.string.sura_ayah_notification_str, 
          quranDisplayData.getSuraName(activity, pos[0], false), pos[1]);
      final QuranRow.Builder builder = new QuranRow.Builder()
          .withText(quarters[i])
          .withMetadata(metadata)
          .withPage(page)
          .withJuzType(sEntryTypes[i % 4]);
      if (i % 4 == 0) {
        final String overlayText =
            QuranUtils.getLocalizedNumber(activity, 1 + (i / 4));
        builder.withJuzOverlayText(overlayText);
      }
      elements[ctr++] = builder.build();
    }

    return elements;
  }
}
