package com.nazmul.hiltmvvmapp.ui.activity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.nazmul.hiltmvvmapp.R
import com.nazmul.hiltmvvmapp.databinding.ActivityMainBinding
import com.nazmul.hiltmvvmapp.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun viewBindingLayout(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

    override fun initializeView(savedInstanceState: Bundle?) {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        binding.bottomNavigationView.setupWithNavController(navHostFragment.navController)
    }

}