package com.example.dat153oblig1nyversjon;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule
            = new ActivityScenarioRule<>(MainActivity.class);
    @Test
    public void testGalleryButton() {
        Intents.init();
        onView(withId(R.id.galleryButton)).check(matches(isDisplayed()));
        onView(withId(R.id.galleryButton)).check(matches(isClickable()));
        onView(withId(R.id.galleryButton)).perform(click());

        Intents.intended(hasComponent(GalleryActivity.class.getName()));
        Intents.release();
    }
    @Test
    public void testQuizButton() {
        Intents.init();
        onView(withId(R.id.quizButton)).check(matches(isDisplayed()));
        onView(withId(R.id.quizButton)).check(matches(isClickable()));
        onView(withId(R.id.quizButton)).perform(click());

        Intents.intended(hasComponent(QuizActivity.class.getName()));
        Intents.release();
    }

}
