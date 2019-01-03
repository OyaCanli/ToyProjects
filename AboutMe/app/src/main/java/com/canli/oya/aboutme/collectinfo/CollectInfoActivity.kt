package com.canli.oya.aboutme.collectinfo

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.canli.oya.aboutme.R
import com.canli.oya.aboutme.databinding.CollectInfoBinding

class CollectInfoActivity : AppCompatActivity() {

    private lateinit var binding: CollectInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_collect_info)

        with(binding) {
            viewpager.adapter = CustomPagerAdapter(supportFragmentManager)
            stepIndicator.setupWithViewPager(viewpager)
        }
    }
}
