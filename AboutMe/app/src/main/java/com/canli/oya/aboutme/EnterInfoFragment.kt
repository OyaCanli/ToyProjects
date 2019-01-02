package com.canli.oya.aboutme


import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputMethodManager
import com.canli.oya.aboutme.databinding.EnterInfoBinding


/**
 * The fragment which collects input from the user
 *
 */
class EnterInfoFragment : Fragment() {

    private lateinit var binding: EnterInfoBinding
    private lateinit var mViewModel: MainViewModel

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
        binding = EnterInfoBinding.inflate(inflater, container, false)
        binding.doneBtn.setOnClickListener {
            setNext()
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewModel = ViewModelProviders.of(requireActivity()).get(MainViewModel::class.java)
    }

    private fun setNext() {
        val stepNo = mViewModel.stepNumber

        //For hiding soft keyboard
        val inputManager = activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(binding.userInputEt.windowToken, 0)

        //Save user input
        val input: String? = binding.userInputEt.text.toString().trim()
        with(mViewModel) {
            when (stepNo) {
                0 -> name = input
                1 -> nick = input
                2 -> {
                    mail = input
                    binding.doneBtn.text = getText(R.string.save)
                }
                //Todo: implement image pick here
                3 -> {
                    bio = input
                    //That was the last step. Open ShowProfileFragment and don't execute the rest of the method
                    fragmentManager!!.beginTransaction()
                        .replace(R.id.main_container, ShowProfileFragment())
                        .addToBackStack(null)
                        .commit()
                    return
                }
            }
        }

        //Update the information for next step (except last step)
        if (stepNo < 3) {
            val animationOut = AnimationUtils.loadAnimation(activity, R.anim.slide_out_left)
            animationOut.setAnimationListener(object : myAnimListener {
                override fun onAnimationEnd(animation: Animation) {
                    val animationIn = AnimationUtils.loadAnimation(activity, R.anim.slide_in_right)
                    with(binding) {
                        //Clean edit text and update info text
                        userInputEt.text = null
                        stepInfoTv.text = getText(hints[stepNo + 1])
                        //When exit animation ends, start enter animation
                        startAnimOnViews(animationIn, stepInfoTv, userInputEt)
                    }
                }
            })
            startAnimOnViews(animationOut, binding.stepInfoTv, binding.userInputEt)
        }

        //Increment step number in the view model and on the step indicator
        mViewModel.stepNumber++
        binding.stepIndicator.currentStepPosition = stepNo + 1

    }

    interface myAnimListener : Animation.AnimationListener {
        override fun onAnimationStart(animation: Animation) {}
        override fun onAnimationRepeat(animation: Animation) {}
        override fun onAnimationEnd(animation: Animation) {}
    }

    fun startAnimOnViews(anim: Animation, vararg views: View) {
        for (v in views) {
            v.startAnimation(anim)
        }
    }

    companion object {
        internal val TAG = "EnterInfoFragment"
    }
}
