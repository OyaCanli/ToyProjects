package com.canli.oya.aboutme.showinfo

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.canli.oya.aboutme.R
import com.canli.oya.aboutme.databinding.ShowProfileBinding


class ShowProfileActivity : AppCompatActivity() {

    private lateinit var binding: ShowProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_show_profile)
    }

    companion object {
        internal val TAG = "ShowProfileActivity"
    }
}
