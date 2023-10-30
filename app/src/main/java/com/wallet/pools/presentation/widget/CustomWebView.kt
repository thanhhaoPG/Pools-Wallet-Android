package com.wallet.pools.presentation.widget

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.webkit.WebChromeClient
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.webkit.WebSettingsCompat
import androidx.webkit.WebViewFeature
import com.wallet.pools.R


class CustomWebView(context: Context, attrs: AttributeSet?) : LinearLayout(context, attrs) {

    private  val webView : WebView
    private  val frmBack : FrameLayout
    private  val tvTitle : TextView
    init {
        LayoutInflater.from(context).inflate(R.layout.view_custom_web_view, this, true)
        webView = findViewById(R.id.webView)
        frmBack = findViewById(R.id.frmBack)
        tvTitle = findViewById(R.id.tvTitle)
        frmBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }
    fun setData(url :String, nameTitle:String){
        if (url.isNotEmpty()){
            webView.settings.javaScriptEnabled = true;
            webView.settings.loadWithOverviewMode = true;
            webView.settings.useWideViewPort = true;

            // Configure a WebViewClient to handle navigation events
            // Configure a WebChromeClient (optional)
            webView.webChromeClient =  WebChromeClient()
                    webView.loadUrl(url)

                    if (WebViewFeature.isFeatureSupported(WebViewFeature.ALGORITHMIC_DARKENING))
                    {
                        WebSettingsCompat.setAlgorithmicDarkeningAllowed(webView.settings, true);
                    }
        }
        tvTitle.text = nameTitle
    }
    }
//    @SuppressLint("SetJavaScriptEnabled")
//    fun setData(url : String , nameTitle :String){
//        if (url.isNotEmpty()){
//            webView.settings.javaScriptEnabled = true;
//            webView.settings.loadWithOverviewMode = true;
//            webView.settings.useWideViewPort = true;
//
//            // Configure a WebViewClient to handle navigation events
//            webView.webViewClient = new WebViewClient() {
//                @Override
//                public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
//                    // Return false to allow the WebView to handle the URL
//                    return false;
//                }
//            };
//
//            // Configure a WebChromeClient (optional)
//            webView.webChromeClient =  WebChromeClient()
//
//            // Generate HTML content to embed the PDF
//            val htmlContent = getPDFHtml(url);
//
//            // Load the HTML content into the WebView
//            webView.loadData(htmlContent, "text/html", "utf-8");
//        }
//        tvTitle.text = nameTitle
//    }


