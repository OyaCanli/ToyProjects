package com.canli.oya.aboutme.collectinfo

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

const val KEY_STEP_NUMBER = "stepNumber"

class CustomPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
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