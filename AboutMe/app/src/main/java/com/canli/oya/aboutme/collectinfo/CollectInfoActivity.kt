package com.canli.oya.aboutme.collectinfo

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.canli.oya.aboutme.R
import com.canli.oya.aboutme.databinding.CollectInfoBinding
import com.stepstone.stepper.StepperLayout
import com.stepstone.stepper.VerificationError

class CollectInfoActivity : AppCompatActivity(), StepperLayout.StepperListener {

    private lateinit var binding: CollectInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_collect_info)

        binding.stepperLayout.adapter = CustomPagerAdapter(supportFragmentManager, this)
        binding.stepperLayout.setListener(this)
    }

    override fun onStepSelected(newStepPosition: Int) {
    }

    override fun onError(verificationError: VerificationError?) {
    }

    override fun onReturn() {
    }

    override fun onCompleted(completeButton: View?) {
    }
}
