package com.example.app.ui.main

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.app.R
import com.example.app.databinding.FragmentMainBinding
import com.examples.imageloaderlibrary.ImageLoader
import com.examples.imageloaderlibrary.imagesource.AssetImageSource
import com.examples.imageloaderlibrary.task.ImageLoadingOptions

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val options = ImageLoadingOptions.Builder()
            .fadeIn(true)
            .config(Bitmap.Config.RGB_565)
            .skipMemoryCache()
            .skipDiskCache()
            .build()
        ImageLoader.getInstance().display(
            AssetImageSource(resources.assets, "avatar.jpg"),
            options,
            binding.imageView,
            resources
        )
    }

    companion object {
        private const val TAG = "echo"

        fun newInstance() = MainFragment()
    }
}
