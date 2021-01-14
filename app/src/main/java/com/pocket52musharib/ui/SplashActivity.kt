package com.pocket52musharib.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.pocket52musharib.R
import com.pocket52musharib.databinding.ActivitySplashBinding
import com.pocket52musharib.ui.viewModule.SplashViewModule
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    private var binding:ActivitySplashBinding?= null
    private val viewMode:SplashViewModule by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_splash)

        viewMode.loadData(this)
       viewMode._update.observe(this,{
           if (it) {
               GlobalScope.launch(context = Dispatchers.Main) {
                   delay(200)
                   startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                   finish()

               }

           }
       })
    }
}