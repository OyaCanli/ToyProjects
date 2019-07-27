package com.canli.oya.diceroller

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest{

    @Rule
    @JvmField
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun clickRollDice_showsDiceAndNumbers() {
        //Click to roll dice button
        onView(withId(R.id.roll_btn))
            .perform(click())

        //Check whether textviews show correct numbers
        onView(withId(R.id.first_number))
            .check(matches(withText(sampleFirstDice.toString())))
        onView(withId(R.id.second_number))
            .check(matches(withText(sampleSecondDice.toString())))

        //Check whether correct images are shown
        //TODO: needs a custom matcher
    }

}
