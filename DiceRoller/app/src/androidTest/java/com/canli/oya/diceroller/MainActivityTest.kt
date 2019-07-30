package com.canli.oya.diceroller

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest{

    private var idlingResource : IdlingResource? = null

    @Before
    fun registerIdlingResource() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        activityScenario.onActivity { activity ->
            idlingResource = activity.getIdlingResource()
            // To prove that the test fails, omit this call:
            IdlingRegistry.getInstance().register(idlingResource)
        }
    }

    @Test
    fun clickRollDice_showsDiceAndNumbers() {
        //Click to roll dice button
        onView(withId(R.id.roll_btn))
            .perform(click())

        //Check whether correct images are shown
        onView(withId(R.id.first_dice))
            .check(matches(withDrawable(drawableIdForFirstDice)))
        onView(withId(R.id.second_dice))
            .check(matches(withDrawable(drawableIdForSecondDice)))

        //Check whether textviews show correct numbers
        onView(withId(R.id.first_number))
            .check(matches(withText(sampleFirstDice.toString())))
        onView(withId(R.id.second_number))
            .check(matches(withText(sampleSecondDice.toString())))
    }

    @Test
    fun enterCorrectSum_saysCongratulations(){
        //Click to roll dice button
        onView(withId(R.id.roll_btn))
            .perform(click())

        //Enter the correct sum to edittext
        val correctSum = sampleFirstDice + sampleSecondDice
        onView(withId(R.id.sum_et))
            .perform(typeText(correctSum.toString()))

        //Click "ok" button
        onView(withId(R.id.ok_btn))
            .perform(click())

        //Check whether congratulation message is shown
        onView(withId(R.id.result_tv))
            .check(matches(withText(R.string.correct_answer)))

        //Check whether check mark icon is shown
        onView(withId(R.id.result_icon))
            .check(matches(withDrawable(R.drawable.ic_check)))
    }

    @Test
    fun enterWrongSum_saysTryAgain(){
        //Click to roll dice button
        onView(withId(R.id.roll_btn))
            .perform(click())

        //Enter a wrong sum to edittext
        val sampleWrongSum = (sampleFirstDice + sampleSecondDice) + 2
        onView(withId(R.id.sum_et))
            .perform(typeText(sampleWrongSum.toString()))

        //Click "ok" button
        onView(withId(R.id.ok_btn))
            .perform(click())

        //Check whether try again message is shown
        onView(withId(R.id.result_tv))
            .check(matches(withText(R.string.wrong_answer)))

        //Check whether cross mark icon is shown
        onView(withId(R.id.result_icon))
            .check(matches(withDrawable(R.drawable.ic_wrong)))
    }

    @After
    fun unregisterIdlingResource() {
        if (idlingResource != null) {
            IdlingRegistry.getInstance().unregister(idlingResource)
        }
    }

}
