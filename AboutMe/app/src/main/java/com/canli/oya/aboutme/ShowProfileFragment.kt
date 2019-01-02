package com.canli.oya.aboutme


import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.canli.oya.aboutme.databinding.ShowProfileBinding

/**
 * A simple [Fragment] subclass.
 *
 */
class ShowProfileFragment : Fragment() {

    private lateinit var binding: ShowProfileBinding
    private lateinit var mViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_show_profile, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewModel = ViewModelProviders.of(requireActivity()).get(MainViewModel::class.java)
        binding.viewmodel = mViewModel
    }

    companion object {
        internal val TAG = "ShowProfileFragment"
    }

}
