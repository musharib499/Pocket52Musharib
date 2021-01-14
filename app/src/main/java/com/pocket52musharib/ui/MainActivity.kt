package com.pocket52musharib.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.pocket52musharib.R
import com.pocket52musharib.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject lateinit var appNavigatorInterface: AppNavigatorInterface
    private var binding:ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         binding =  DataBindingUtil.setContentView(this,R.layout.activity_main)
        if (savedInstanceState == null) {
            appNavigatorInterface.navigator(Command.HOME)

        }

    }
    override fun onBackPressed() {
        super.onBackPressed()

        if (supportFragmentManager.backStackEntryCount == 0) {
            finish()
        }
    }
}