package ua.com.anyapps.alicrab.ui.fragments

import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.view.*
import android.webkit.WebChromeClient
import androidx.fragment.app.Fragment
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.google.android.material.appbar.MaterialToolbar
import ua.com.anyapps.alicrab.R
import ua.com.anyapps.alicrab.viewmodel.BrowseViewModel
import ua.com.anyapps.alicrab.viewmodel.SharedViewModel


class BrowseFragment : Fragment() {

    private lateinit var browseViewModel: BrowseViewModel

    private var webView: WebView? = null
    private var topAppBar: MaterialToolbar? = null

    private val sharedVM: SharedViewModel by activityViewModels()

    private var pbProgress: ProgressBar? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun setupMenu(){
        topAppBar?.menu?.clear()
        topAppBar?.inflateMenu(R.menu.top_app_bar_menu)
    }

    private fun menuListener(): Toolbar.OnMenuItemClickListener {
        return Toolbar.OnMenuItemClickListener{
            when(it.itemId){
                R.id.browser_menu_item_home -> browseViewModel.navHomeClick()
                R.id.browser_menu_item_back -> browseViewModel.navBackClick()
                R.id.browser_menu_item_forward -> browseViewModel.navForwardClick()
                R.id.browser_menu_item_refresh -> browseViewModel.navRefreshClick()
            }
            true
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_browse, container, false)

        webView = view.findViewById(R.id.vwBrowser)
        pbProgress = view.findViewById(R.id.pbProgress)

        topAppBar = requireActivity().findViewById(R.id.topAppBar)

        setupViewModel()

        setupWebView()
        setupMenu()

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
                    browseViewModel.saveLastUrl(url.toString(), "html11111111111111" получить html)
                    pbProgress?.setVisibility(ProgressBar.GONE)
                }

                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    super.onPageStarted(view, url, favicon)
                    pbProgress?.setVisibility(ProgressBar.VISIBLE)
                }
            }

            it.webChromeClient = object : WebChromeClient(){
                override fun onProgressChanged(view: WebView?, newProgress: Int) {
                    if(newProgress < 100 && pbProgress?.getVisibility() == ProgressBar.GONE){
                        pbProgress?.setVisibility(ProgressBar.VISIBLE)
                        //txtview.setVisibility(View.VISIBLE);
                        //pbProgress?.progress = newProgress
                    }

                    pbProgress?.progress = newProgress
                    if(newProgress == 100) {
                        pbProgress?.setVisibility(ProgressBar.GONE);
                        //txtview.setVisibility(View.GONE);
                    }
                    super.onProgressChanged(view, newProgress)
                    pbProgress?.progress = newProgress
                }

            }
        }

        topAppBar?.setOnMenuItemClickListener(menuListener())
    }

    private fun setupViewModel(){
        browseViewModel = BrowseViewModel()
    }
}