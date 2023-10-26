package com.wallet.pools.presentation.screen.createNewWallet

import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.StyleSpan
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.rajat.pdfviewer.PdfViewerActivity
import com.wallet.pools.R
import com.wallet.pools.base.BaseFragment
import com.wallet.pools.base.BaseViewModel
import com.wallet.pools.databinding.FragmentCreateNewWalletBinding
import com.wallet.pools.extenstion.showToast
import com.wallet.pools.presentation.screen.login.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CreatNewWalletFragment : BaseFragment<FragmentCreateNewWalletBinding, BaseViewModel>() {


    override val viewModel: LoginViewModel by viewModels()


    override fun getViewBinding(): FragmentCreateNewWalletBinding =
        FragmentCreateNewWalletBinding.inflate(layoutInflater)

    override fun onBackFragment() {

        findNavController().navigateUp()

    }

    val string = "I have read and agree to the Term of service and Privacy policy"
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewStateChange()
    }


    private fun initViewStateChange() {


    }

    private fun initView() {
        binding.apply {
            frmBack.setOnClickListener{
                findNavController().navigateUp()
            }
            ivScanQR.setOnClickListener {
                findNavController().navigate(R.id.scanQRFragment)
            }
            llCreatePin.setOnClickListener {
                findNavController().navigate(R.id.pinFragment)
            }
        }
        clickableText()

    }

    private fun clickableText() {
        val ss = SpannableString("I have read and agree to the Term of service and Privacy policy")
        val clickableSpan1: ClickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                val url = "https://pools.s3.ap-southeast-1.amazonaws.com/pools-wallet/android/PoolsWallet-TermsOfService.pdf"
                launchPdf(url,"Term of service")
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color = ContextCompat.getColor(requireContext(), R.color.color_06C270_unchanged)
                ds.isUnderlineText = false
            }

        }
        val clickableSpan2: ClickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                val url = "https://pools.s3.ap-southeast-1.amazonaws.com/pools-wallet/android/PoolsWallet-PrivacyPolicy.pdf"
                launchPdf(url,"Privacy policy")

            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color = ContextCompat.getColor(requireContext(), R.color.color_06C270_unchanged)
                ds.isUnderlineText = false
            }

        }

        ss.setSpan(clickableSpan1, 29,44, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        ss.setSpan(clickableSpan2, 48,ss.length,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        val boldSpan1 = StyleSpan(Typeface.BOLD)
        ss.setSpan(boldSpan1, 29, 44, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        val boldSpan2 = StyleSpan(Typeface.BOLD)
        ss.setSpan(boldSpan2, 48, ss.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        binding.tvTermAndPolicy.text = ss
        binding.tvTermAndPolicy.movementMethod = LinkMovementMethod.getInstance()
        binding.tvTermAndPolicy.highlightColor = Color.TRANSPARENT
    }
private fun launchPdf(url: String, title: String) {
    requireContext().startActivity(
        PdfViewerActivity.launchPdfFromUrl(
            context = context, pdfUrl = url,
            pdfTitle = title, directoryName = "dir", enableDownload = false)
    )
}



}


