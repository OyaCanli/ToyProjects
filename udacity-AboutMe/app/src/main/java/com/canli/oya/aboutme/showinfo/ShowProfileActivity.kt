package com.canli.oya.aboutme.showinfo

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.canli.oya.aboutme.R
import com.canli.oya.aboutme.collectinfo.KEY_USER_INFO_LIST
import com.canli.oya.aboutme.databinding.ShowProfileBinding
import timber.log.Timber


class ShowProfileActivity : AppCompatActivity() {

    private lateinit var binding: ShowProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_show_profile)

        val bundle: Bundle? = intent?.extras
        val userInfo: Array<out String?>? = bundle?.getStringArray(KEY_USER_INFO_LIST)
        Timber.d("list size: ${userInfo?.size}")

        val factory = ShowProfileViewModelFactory(userInfo)
        val viewModel = ViewModelProviders.of(this, factory).get(ShowProfileViewModel::class.java)

        binding.viewmodel = viewModel
    }

}
