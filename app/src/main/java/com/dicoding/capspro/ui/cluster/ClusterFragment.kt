package com.dicoding.capspro.ui.cluster

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import com.dicoding.capspro.databinding.ClusterFragmentBinding
import com.dicoding.capspro.utils.ToJSConverter.Companion.toJSArray
import org.koin.androidx.viewmodel.ext.android.viewModel

class ClusterFragment : Fragment() {

    private val viewModel: ClusterViewModel by viewModel()
    private var _binding: ClusterFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ClusterFragmentBinding.inflate(inflater, container, false)
        viewModel.getClusterData().observe(viewLifecycleOwner, {
            binding.clusteringMap.settings.javaScriptEnabled = true
            binding.clusteringMap.webViewClient = ViewClient(it.reportCase, it.clusteringCode)
            binding.clusteringMap.loadUrl("file:///android_asset/index.html")
        })

        val root: View = binding.root
        return root
    }

    class ViewClient(
        private val dataReport: ArrayList<Int>,
        private val dataTingkat: ArrayList<Int>
    ) : WebViewClient() {
        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            view?.loadUrl("javascript:getData(${dataReport.toJSArray()},${dataTingkat.toJSArray()})")
        }
    }


}