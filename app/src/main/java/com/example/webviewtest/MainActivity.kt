package com.example.webviewtest

import android.annotation.TargetApi
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.webkit.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import com.example.webviewtest.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAnchorView(R.id.fab)
                .setAction("Action", null).show()
        }

        initWebView()
    }

    private fun initWebView() {
        binding.webView1.settings.allowFileAccessFromFileURLs = true;
        binding.webView1.settings.allowUniversalAccessFromFileURLs = true;
        binding.webView1.settings.javaScriptEnabled = true;
        binding.webView1.settings.setDomStorageEnabled(true);
        binding.webView1.settings.setJavaScriptCanOpenWindowsAutomatically(true);
        binding.webView1.settings.setBuiltInZoomControls(true);
        binding.webView1.settings.setAllowFileAccess(true);
        binding.webView1.settings.setSupportZoom(true);

        binding.webView1.setWebChromeClient(object : WebChromeClient() {
            override fun onPermissionRequest(request: PermissionRequest) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    request.grant(request.resources)
                }
            }
        })

        binding.webView1.setWebViewClient(Callback())

        binding.webView1.loadUrl("http://192.168.10.149:8080");
//        binding.webView1.loadUrl("file:///android_asset/idemia.html")
    }

    private class Callback : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            return false
        }

//        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
//        override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
//            view.loadUrl(request.url.toString(), getCustomHeaders())
//            return true
//        }
//
//        private fun getCustomHeaders(): Map<String, String> {
//            val headers: MutableMap<String, String> = HashMap()
//            headers["User-Agent"] = "Mozilla/5.0 (Linux; Android 10) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.5304.105 Mobile Safari/537.36"
//            return headers
//        }
    }
}