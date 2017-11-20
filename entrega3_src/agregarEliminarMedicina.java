package es.usc.citius.servando.calendula.activities;


import android.support.test.espresso.ViewInteraction;
//import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import es.usc.citius.servando.calendula.R;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class agregarEliminarMedicina {

    //@Rule
    //public ActivityTestRule<StartActivity> mActivityTestRule = new ActivityTestRule<>(StartActivity.class);

    @Test
    public void agregarEliminarMedicina() {
        pressBack();
        ViewInteraction appCompatTextView = onView(
            allOf(withClassName(is("android.support.v7.widget.AppCompatTextView")), isDisplayed()));
        appCompatTextView.perform(click());
        ViewInteraction viewPager = onView(
            allOf(withId(R.id.container),  withParent(withId(R.id.main_content)),  isDisplayed()));
        viewPager.perform(swipeLeft());
        ViewInteraction floatingActionButton = onView(
            allOf(withId(R.id.add_button), isDisplayed()));
        floatingActionButton.perform(click());
        ViewInteraction appCompatButton = onView(
            allOf(withId(android.R.id.button2), withText("Cancel")));
        appCompatButton.perform(scrollTo(), click());
        ViewInteraction appCompatAutoCompleteTextView = onView(
            allOf(withId(R.id.medicine_edit_name), isDisplayed()));
        appCompatAutoCompleteTextView.perform(replaceText("dolex"), closeSoftKeyboard());
        ViewInteraction appCompatImageView = onView(
            withId(R.id.med_presentation_2));
        appCompatImageView.perform(scrollTo(), click());
        ViewInteraction floatingActionButton2 = onView(
            allOf(withId(R.id.add_button), isDisplayed()));
        floatingActionButton2.perform(click());
        ViewInteraction relativeLayout = onView(
            allOf(withId(R.id.medicines_list_item_container),
            withParent(childAtPosition(withId(R.id.medicines_list), 0)), isDisplayed()));
        relativeLayout.perform(click());
        ViewInteraction appCompatAutoCompleteTextView2 = onView(
            allOf(withId(R.id.medicine_edit_name), withText("dolex"), isDisplayed()));
        appCompatAutoCompleteTextView2.perform(replaceText("ddolex"), closeSoftKeyboard());
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        ViewInteraction appCompatTextView2 = onView(
            allOf(withId(R.id.title), withText("Remove"), isDisplayed()));
        appCompatTextView2.perform(click());
        ViewInteraction appCompatButton2 = onView(
            allOf(withId(android.R.id.button2), withText("Si")));
        appCompatButton2.perform(scrollTo(), click());
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
