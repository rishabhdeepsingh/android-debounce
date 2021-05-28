package com.rishabh.testapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.rishabh.testapp.blankFragment.BlankFragment
import com.rishabh.testapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setDebounceListener(mBinding.button, lifecycleScope) {
            val fragment = BlankFragment.getInstance()
            supportFragmentManager.beginTransaction().add(R.id.fragmentContainer, fragment, "BlankFragment")
            .addToBackStack("BlankFragment")
            .commitAllowingStateLoss()
        }
    }
}