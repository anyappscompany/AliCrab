package ua.com.anyapps.alicrab.ui.fragments

import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.lifecycle.Observer
import ua.com.anyapps.alicrab.R
import ua.com.anyapps.alicrab.viewmodel.BrowseViewModel
import ua.com.anyapps.alicrab.viewmodel.SettingsViewModel
import java.text.SimpleDateFormat
import java.util.*


class BrowseFragment : Fragment() {

    private lateinit var browseViewModel: BrowseViewModel
    private var webView: WebView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_browse, container, false)
        webView = view.findViewById(R.id.vwBrowser)

        setupViewModel()
        setupWebView()
        initObservers()
        initListeners()
        return view
    }

    private fun setupWebView(){
        webView?.let {
            it.settings.javaScriptEnabled = true
            it.settings.setJavaScriptEnabled(true);
            it.settings.setLoadWithOverviewMode(true);
            it.settings.setUseWideViewPort(true);
            it.settings.setSupportZoom(true);
            it.settings.setBuiltInZoomControls(false);
            it.settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
            it.settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
            it.settings.setDomStorageEnabled(true);
            it.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
            it.setScrollbarFadingEnabled(true);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                it.setLayerType(View.LAYER_TYPE_HARDWARE, null);
            } else {
                it.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
            }
        }
    }

    private fun initObservers(){
        browseViewModel.lastUrl.observe(requireActivity(), Observer {
            // стартовая страница https://m.aliexpress.com/
            webView?.loadUrl(it)
        })
    }

    private fun initListeners(){
        webView?.let {
            it.webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    browseViewModel.saveLastUrl(url.toString())
                }
            }
        }
    }

    private fun setupViewModel(){
        browseViewModel = BrowseViewModel()
    }
}