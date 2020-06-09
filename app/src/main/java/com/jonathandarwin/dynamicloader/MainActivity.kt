package com.jonathandarwin.dynamicloader

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.viewbinding.ViewBinding
import com.jonathandarwin.dynamicloader.databinding.ActivityMainBinding
import com.jonathandarwin.dynamicloader.databinding.BlockLoadingBinding
import com.jonathandarwin.dynamicloader.databinding.LoadingBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var root : ViewGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // Initialize Root Layout
        root = (binding.root as ViewGroup)

        // Initialize Loader
        val loading = LoadingBinding.inflate(LayoutInflater.from(this), root, false)
        // Cast layoutParams to your specific root layout.
        // Root Layout should be either RelativeLayout or ConstraintLayout
        val param = loading.root.layoutParams as RelativeLayout.LayoutParams

        // Add rule based on your root layout
        // The purpose of adding this rule is to centered your loader
        param.addRule(RelativeLayout.CENTER_IN_PARENT)
        loading.root.layoutParams = param

        // Initialize Blocking Loader
        val blockLoading = BlockLoadingBinding.inflate(LayoutInflater.from(this), root, false)

        // Set Listener
        binding.btnLoading.setOnClickListener {
            showLoading(if(binding.switchMode.isChecked) blockLoading else loading)
        }
    }

    private fun showLoading(loader : ViewBinding){
        // Add Loader to Layout
        root.addView(loader.root)

        // Update UI
        binding.btnLoading.visibility = View.INVISIBLE
        binding.tvMessage.text = "Loading for 3 seconds. Try to click the switch below."

        Handler().postDelayed({
            // Remove Loader
            root.removeView(loader.root)

            // Update UI
            binding.btnLoading.visibility = View.VISIBLE
            binding.tvMessage.text = "Enable Blocking Loader"
        }, 3000)
    }
}