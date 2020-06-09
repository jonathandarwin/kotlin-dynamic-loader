package com.jonathandarwin.dynamicloader

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.jonathandarwin.dynamicloader.databinding.ActivityMainBinding
import com.jonathandarwin.dynamicloader.databinding.LoadingBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var root : ViewGroup
    private lateinit var loading : LoadingBinding
    private var isLoading = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        root = (binding.root as ViewGroup)
        loading = LoadingBinding.inflate(LayoutInflater.from(this), root, false)


        binding.btnLoading.setOnClickListener {
            binding.btnLoading.text = if(isLoading) {
                // Remove Loading
                root.removeView(loading.root)
                "Show Loading"
            } else{
                // Add Loading
                root.addView(loading.root)
                "Remove Loading"
            }
            isLoading = !isLoading
        }
    }
}