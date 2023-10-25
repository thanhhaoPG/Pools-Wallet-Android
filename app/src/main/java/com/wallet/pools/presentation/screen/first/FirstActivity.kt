package com.wallet.pools.presentation.screen.first

import android.os.Bundle
import androidx.activity.viewModels
import com.wallet.pools.base.BaseActivity
import com.wallet.pools.base.BaseViewModel
import com.wallet.pools.databinding.ActivityFirstBinding
import com.wallet.pools.extenstion.hideKeyboard
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FirstActivity : BaseActivity<ActivityFirstBinding, BaseViewModel>() {

    override fun getViewBinding() = ActivityFirstBinding.inflate(layoutInflater)

    override val viewModel: BaseViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        initView()


    }


    private fun initView() {

        binding.rootFirst.setOnClickListener {
            hideKeyboard()

        }
    }


}