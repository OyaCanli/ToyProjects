package com.canli.oya.aboutme.collectinfo


import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.canli.oya.aboutme.R
import com.canli.oya.aboutme.databinding.ImageLayoutBinding

/**
 * The fragment for getting user profile photo
 *
 */
class UploadImageFragment : Fragment() {

    private lateinit var binding: ImageLayoutBinding
    private lateinit var mViewModel: CollectInfoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_upload_image, container, false)

        binding.doneBtn.setOnClickListener { saveAndShowAll() }
        binding.pickImage.setOnClickListener {
            //TODO: implement image pick
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewModel = ViewModelProviders.of(requireActivity()).get(CollectInfoViewModel::class.java)
    }

    fun saveAndShowAll() {

    }

    companion object {
        internal val TAG = "UploadImageFragment"
    }

}
