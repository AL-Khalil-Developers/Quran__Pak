package com.alkhalildevelopers.apps.quranepak.ui;

import com.alkhalildevelopers.apps.quranepak.BaseActivityTest;

import org.junit.Before;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.startsWith;

public class QuranActivityTest extends BaseActivityTest {

  public QuranActivityTest() {
    super(QuranActivity.class);
  }

  @Before
  public void setup() {
    rule.launchActivity(null);
  }

  @Test
  public void testClickingOnSuraOnListViewNavigatesToSura() {
    //given
    onView(withText(startsWith("Quran")))
        .check(matches(isDisplayed()));

    //when
    onView(allOf(withText("Surah Al-Fatihah"), isCompletelyDisplayed()))
        .perform(click());

    //then
    intended(hasComponent(PagerActivity.class.getName()));

    onView(withText("Surah Al-Fatihah"))
        .check(matches(isDisplayed()));
  }
}
