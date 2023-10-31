package com.wallet.pools.presentation.screen.settingTheme

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.RadioGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.wallet.pools.R
import com.wallet.pools.base.BaseFragment
import com.wallet.pools.data.local.CachePreferencesHelper
import com.wallet.pools.databinding.FragmentSettingThemeBinding
import com.wallet.pools.enum.TypeThemeApp
import com.wallet.pools.presentation.screen.main.MainActivity
import com.wallet.pools.util.setSafeOnClickListener
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class SettingThemeFragment : BaseFragment<FragmentSettingThemeBinding, SettingThemeViewModel>() {


    override val viewModel: SettingThemeViewModel by viewModels()

    @Inject
    lateinit var cachePreferencesHelper: CachePreferencesHelper
    override fun getViewBinding(): FragmentSettingThemeBinding =
        FragmentSettingThemeBinding.inflate(layoutInflater)

    override fun onBackFragment() {

        findNavController().navigateUp()

    }

    override fun onStart() {
        (requireActivity() as MainActivity).hideBottomView()
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
            frmBack.setSafeOnClickListener { onBackFragment() }
            layoutThemeLight.setOnClickListener {
                radioGroup.check(
                    R.id.radio_theme_light
                )
            }
            layoutThemeDark.setOnClickListener {
                radioGroup.check(
                    R.id.radio_theme_dark
                )
            }
            layoutThemeAuto.setOnClickListener {
                radioGroup.check(
                    R.id.radio_theme_auto
                )
            }

            when(cachePreferencesHelper.themeAppCurrent){
                TypeThemeApp.THEME_LIGHT.value->{
                    radioGroup.check(
                        R.id.radio_theme_light
                    )
                }
                TypeThemeApp.THEME_DARK.value->{
                    radioGroup.check(
                        R.id.radio_theme_dark
                    )
                }
                else ->{
                    radioGroup.check(
                        R.id.radio_theme_auto
                    )
                }
            }



            radioGroup.setOnCheckedChangeListener { _: RadioGroup?, checkedId: Int ->
                when (checkedId) {
                    R.id.radio_theme_light -> {
                        cachePreferencesHelper.themeAppCurrent = TypeThemeApp.THEME_LIGHT.value
                        viewModel.setTheme(context, TypeThemeApp.THEME_LIGHT.value)
                    }

                    R.id.radio_theme_dark -> {
                        cachePreferencesHelper.themeAppCurrent = TypeThemeApp.THEME_DARK.value
                        viewModel.setTheme(context, TypeThemeApp.THEME_DARK.value)
                    }

                    else -> {
                        cachePreferencesHelper.themeAppCurrent = TypeThemeApp.THEME_AUTO.value
                        viewModel.setTheme(context, TypeThemeApp.THEME_AUTO.value)
                    }
                }

                val intent = Intent(requireActivity(), MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
                    requireActivity().finish()
            }

        }


    }


}