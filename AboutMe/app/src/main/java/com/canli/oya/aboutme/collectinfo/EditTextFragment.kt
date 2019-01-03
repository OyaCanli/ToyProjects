package com.canli.oya.aboutme.collectinfo


import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import com.canli.oya.aboutme.R
import com.canli.oya.aboutme.databinding.EditTextLayoutBinding


/**
 * The fragment which collects text based input from the user
 *
 */
class EditTextFragment : Fragment() {

    init {
        Log.d(TAG, "init called")
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

        with(binding) {
            nextBtn.setOnClickListener { setNext() }
            val stepNumber = arguments!!.getInt(KEY_STEP_NUMBER)
            stepInfo = getString(hints[stepNumber])
            invalidateAll()
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewModel = ViewModelProviders.of(requireActivity()).get(CollectInfoViewModel::class.java)
    }

    private fun setNext() {
        var stepNo = mViewModel.stepNumber

        //For hiding soft keyboard
        val inputManager = activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(binding.userInputEt.windowToken, 0)

        //Save user input
        val input: String? = binding.userInputEt.text.toString().trim()
        with(mViewModel) {
            when (stepNo) {
                0 -> name = input
                1 -> nick = input
                2 -> mail = input
                3 -> bio = input
            }
        }

        //Increment step number in the view model
        mViewModel.stepNumber = ++stepNo

        val viewPager: ViewPager = (activity as AppCompatActivity).findViewById(R.id.viewpager)
        viewPager.currentItem = stepNo
    }

    companion object {
        internal val TAG = "EnterInfoFragment"
    }
}
