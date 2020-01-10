package com.canli.oya.aboutme.collectinfo

import android.content.Context
import android.os.Bundle
import android.support.v4.app.FragmentManager
import com.stepstone.stepper.Step
import com.stepstone.stepper.adapter.AbstractFragmentStepAdapter

const val KEY_STEP_NUMBER = "stepNumber"

class CustomPagerAdapter(fm: FragmentManager, context: Context) : AbstractFragmentStepAdapter(fm, context) {

    override fun createStep(position: Int): Step {
        return when (position) {
            in 0..3 -> {
                val fragment = EditTextFragment()
                val args = Bundle()
                args.putInt(KEY_STEP_NUMBER, position)
                fragment.arguments = args
                fragment
            }
            4 -> UploadImageFragment()
            else -> throw IllegalStateException()
        }
    }

    override fun getCount(): Int {
        return 5
    }

}