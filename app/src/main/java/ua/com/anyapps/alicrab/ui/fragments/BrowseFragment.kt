package ua.com.anyapps.alicrab.ui.fragments

import android.content.Context
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.*
import android.webkit.*
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import android.widget.ProgressBar
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import ua.com.anyapps.alicrab.R
import ua.com.anyapps.alicrab.databinding.FragmentBrowseBinding
import ua.com.anyapps.alicrab.dialogs.ChartDialog
import ua.com.anyapps.alicrab.utils.Utils
import ua.com.anyapps.alicrab.viewmodel.BrowseViewModel
import ua.com.anyapps.alicrab.viewmodel.SharedViewModel
import java.nio.file.Files.size


class BrowseFragment : Fragment() {

    private lateinit var browseViewModel: BrowseViewModel
    private var topAppBar: MaterialToolbar? = null


    private var binding: FragmentBrowseBinding? = null

    private lateinit var sharedVM: SharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        val tmpSharedVM: SharedViewModel by activityViewModels<SharedViewModel>()
        sharedVM = tmpSharedVM
    }

    fun setupMenu(){
        topAppBar?.menu?.clear()
        topAppBar?.inflateMenu(R.menu.top_app_bar_menu)
    }

    private fun menuListener(): Toolbar.OnMenuItemClickListener {
        return Toolbar.OnMenuItemClickListener{
            when(it.itemId){
                R.id.browser_menu_item_home -> {
                    browseViewModel.navHomeClick()
                    binding?.vwBrowser?.loadUrl(resources.getString(R.string.start_url))
                }
                R.id.browser_menu_item_back -> {
                    browseViewModel.navBackClick()
                    if(binding?.vwBrowser?.canGoBack() == true)
                        binding?.vwBrowser?.goBack()
                }
                R.id.browser_menu_item_forward -> {
                    browseViewModel.navForwardClick()
                    if(binding?.vwBrowser?.canGoForward() == true)
                        binding?.vwBrowser?.goForward()
                }
                R.id.browser_menu_item_refresh -> {
                    browseViewModel.navRefreshClick()
                    binding?.vwBrowser?.reload()
                }
            }
            true
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_browse, container, false)

        topAppBar = requireActivity().findViewById(R.id.topAppBar)
        // Inflate the layout for this fragment
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentBrowseBinding.bind(view)

        setupViewModel()
        setupMenu()

        setupWebView()
        initObservers()
        initListeners()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onStart() {
        super.onStart()
        binding?.fabBrowse?.visibility = View.VISIBLE
    }

    override fun onStop() {
        super.onStop()
        binding?.fabBrowse?.visibility = View.GONE
    }

    private fun setupWebView(){
        binding?.vwBrowser?.let {
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
        // начинатб с последней страницы
        browseViewModel.lastUrl.observe(requireActivity(), Observer {
            // стартовая страница https://m.aliexpress.com/
            binding?.vwBrowser?.loadUrl(it)
        })
    }

    private fun initListeners(){
        binding?.vwBrowser?.let {
            it.webViewClient = object : WebViewClient() {
                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    super.onPageStarted(view, url, favicon)
                    binding?.fabBrowse?.isEnabled = false
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    // урл сохраняется для отображения последней посещённой страницы
                    browseViewModel.saveLastUrl(url.toString())
                    // и для передачи урла в фрагмент статистики
                    sharedVM.setUrl(url.toString())

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        view?.evaluateJavascript("(function() { return document.getElementsByTagName('html')[0].innerHTML; })();", ValueCallback<String?> { s ->
                            if(Utils.isValidGoodPage(url.toString(), s)){
                                binding?.fabBrowse?.isEnabled = true
                            }
                        })
                    }
                }

                override fun onReceivedError(view: WebView?, errorCode: Int, description: String?, failingUrl: String?) {
                    super.onReceivedError(view, errorCode, description, failingUrl)
                    // при ошибка переход к статистике невозможен
                    binding?.fabBrowse?.isEnabled = false
                }
            }

            it.webChromeClient = object : WebChromeClient(){
                override fun onProgressChanged(view: WebView?, newProgress: Int) {
                    binding?.pbProgress?.let {
                        if (newProgress < 100 && binding?.pbProgress?.getVisibility() == ProgressBar.GONE) {
                            binding?.pbProgress?.setVisibility(ProgressBar.VISIBLE)
                        }

                        binding?.pbProgress?.progress = newProgress
                        if (newProgress == 100) {
                            binding?.pbProgress?.setVisibility(ProgressBar.GONE)
                        }
                        super.onProgressChanged(view, newProgress)
                    }
                }
            }
        }

        topAppBar?.setOnMenuItemClickListener(menuListener())

        binding?.fabBrowse?.setOnClickListener(View.OnClickListener {

            sharedVM.getChartDataBtnClick()

            val chartDialog: ChartDialog = ChartDialog().also {
                it.show(requireActivity().supportFragmentManager, it.javaClass.simpleName)
            }
        })
    }

    private fun setupViewModel(){
        browseViewModel = BrowseViewModel()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val navView: BottomNavigationView = requireActivity().findViewById(R.id.bottom_menu)
        // Find the menu item and then disable it
        navView.menu.findItem(R.id.chartFragment).isEnabled = false
        navView.menu.setGroupCheckable(0, true, false)
        for (i in 0 until navView.menu.size()) {
            navView.menu.getItem(i).isChecked = false
        }
        navView.menu.setGroupCheckable(0, true, true)
        //requireActivity().findViewById<View>(R.id.chartFragment).setBackgroundColor(R.color.purple_200)
    }
}