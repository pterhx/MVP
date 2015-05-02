package co.pterhx.mvp;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class LoginActivityTest {

    @Rule
    public ActivityTestRule<LoginActivity> mActivityRule = new ActivityTestRule<>(
            LoginActivity.class
    );

    @Test
    public void testUsernameRequired() {
        onView(withId(R.id.login))
                .perform(click());
        onView(withId(R.id.status))
                .check(matches(withText("You must enter a username!")));
    }

    @Test
    public void testPasswordRequired() {
        onView(withId(R.id.username))
                .perform(typeText("Peter"));
        onView(withId(R.id.login))
                .perform(click());
        onView(withId(R.id.status))
                .check(matches(withText("You must enter a password!")));
    }

    @Test
    public void testSuccess() {
        onView(withId(R.id.username))
                .perform(typeText("Peter"));
        onView(withId(R.id.password))
                .perform(typeText("Peter"));
        onView(withId(R.id.login))
                .perform(click());
        onView(withId(R.id.status))
                .check(matches(withText("Success!")));
    }
}
