package com.canli.oya.aboutme.collectinfo


import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import com.canli.oya.aboutme.R
import com.canli.oya.aboutme.databinding.EditTextLayoutBinding
import com.stepstone.stepper.BlockingStep
import com.stepstone.stepper.StepperLayout
import com.stepstone.stepper.VerificationError
import timber.log.Timber


/**
 * The fragment which collects text based input from the user
 *
 */
class EditTextFragment : Fragment(), BlockingStep {

    private var stepNumber: Int = 0

    init {
        Timber.d("init called")
    }

    private lateinit var binding: EditTextLayoutBinding
    private lateinit var mViewModel: CollectInfoViewModel

    private val hints = listOf(
        R.string.enter_your_name,
        R.string.enter_your_nick,
        R.string.enter_your_email,
        R.string.tell_us_more
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = EditTextLayoutBinding.inflate(inflater, container, false)
        Timber.d("OnCreateView is called")
        stepNumber = arguments!!.getInt(KEY_STEP_NUMBER)
        with(binding) {
            stepInfo = getString(hints[stepNumber])
            invalidateAll()
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewModel = ViewModelProviders.of(requireActivity()).get(CollectInfoViewModel::class.java)
        binding.userInput = mViewModel.userInfo[stepNumber]
        Timber.d("OnActivityCreatedd is called")
    }

    override fun onSelected() {
        Timber.d("onSelected called")
    }

    override fun verifyStep(): VerificationError? {
        //TODO
        return null
    }

    override fun onError(error: VerificationError) {
        Timber.e(error.errorMessage)
    }

    override fun onNextClicked(callback: StepperLayout.OnNextClickedCallback) {
        setNext()
        callback.goToNextStep()
    }

    override fun onBackClicked(callback: StepperLayout.OnBackClickedCallback?) {
        callback?.goToPrevStep()
    }

    override fun onCompleteClicked(callback: StepperLayout.OnCompleteClickedCallback?) {
        //On complete won't be seen from this type of fragment
    }

    private fun setNext() {
        //For hiding soft keyboard
        val inputManager = activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(binding.userInputEt.windowToken, 0)

        //Save user input
        val input: String? = binding.userInputEt.text.toString().trim()
        mViewModel.userInfo[stepNumber] = input
    }
}
