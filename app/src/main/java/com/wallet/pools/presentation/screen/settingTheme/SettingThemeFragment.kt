package com.wallet.pools.presentation.screen.settingTheme

import android.os.Bundle
import android.view.View
import android.widget.RadioGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.wallet.pools.R
import com.wallet.pools.base.BaseFragment
import com.wallet.pools.databinding.FragmentSettingThemeBinding
import com.wallet.pools.presentation.screen.settingTheme.SettingThemeViewModel.Companion.THEME_AUTO
import com.wallet.pools.presentation.screen.settingTheme.SettingThemeViewModel.Companion.THEME_DARK
import com.wallet.pools.presentation.screen.settingTheme.SettingThemeViewModel.Companion.THEME_LIGHT
import com.wallet.pools.presentation.screen.webView.WebViewFragmentArgs
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SettingThemeFragment : BaseFragment<FragmentSettingThemeBinding, SettingThemeViewModel>() {


    override val viewModel: SettingThemeViewModel by viewModels()
    private val args : WebViewFragmentArgs by navArgs()
    override fun getViewBinding(): FragmentSettingThemeBinding =
        FragmentSettingThemeBinding.inflate(layoutInflater)

    override fun onBackFragment() {

        findNavController().navigateUp()

    }

    override fun onStart() {
        super.onStart()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewStateChange()
    }


    private fun initViewStateChange() {


    }

    private fun initView() {
        binding.apply {
            layoutThemeLight.setOnClickListener(View.OnClickListener { v: View? ->
                radioGroup.check(
                    R.id.radio_theme_light
                )
            })
            layoutThemeDark.setOnClickListener(View.OnClickListener { v: View? ->
                radioGroup.check(
                    R.id.radio_theme_dark
                )
            })
            layoutThemeAuto.setOnClickListener(View.OnClickListener { v: View? ->
                radioGroup.check(
                    R.id.radio_theme_auto
                )
            })



            radioGroup.setOnCheckedChangeListener { group: RadioGroup?, checkedId: Int ->
                when (checkedId) {
                    R.id.radio_theme_light -> {
                        viewModel.setTheme(context, THEME_LIGHT)
                    }
                    R.id.radio_theme_dark -> {
                        viewModel.setTheme(context, THEME_DARK)
                    }
                    else -> {
                        viewModel.setTheme(context, THEME_AUTO)
                    }
                }
            }

        }



    }


}