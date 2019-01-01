package com.canli.oya.aboutme


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import com.canli.oya.aboutme.databinding.EnterInfoBinding

/**
 * A simple [Fragment] subclass.
 *
 */
class EnterInfoFragment : Fragment() {

    private lateinit var binding: EnterInfoBinding

    var name: String? = null
    var nick: String? = null
    var bio: String? = null
    var mail: String? = null
    //var imageUrl: String? = null

    private val hints = listOf(
        R.string.enter_your_name,
        R.string.enter_your_nick,
        R.string.enter_your_email,
        R.string.tell_us_more
    )

    private var stepNumber: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = EnterInfoBinding.inflate(inflater, container, false)

        binding.doneBtn.setOnClickListener {
            if (stepNumber <= 3) {
                setNext()
            }
        }
        return binding.root
    }

    private fun setNext() {

        //For hiding soft keyboard
        val inputManager = activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(binding.userInputEt.windowToken, 0)

        //Save user input
        val input: String? = binding.userInputEt.text.toString().trim()
        when (stepNumber) {
            0 -> name = input
            1 -> nick = input
            2 -> {
                mail = input
                binding.doneBtn.text = getText(R.string.save)
            }
            //Todo: implement image pick here
            3 -> {
                bio = input
                Log.d(TAG, "$name $nick $mail $bio")
                //TODO: Open next fragment
                return
            }
        }

        //Clean edit text
        binding.userInputEt.text = null

        //Update the information for next step (except last step)
        if (stepNumber < 3) {
            binding.stepInfoTv.text = getText(hints[stepNumber + 1])
        }

        stepNumber++

    }

    companion object {
        internal val TAG = "EnterInfoFragment"
    }
}
