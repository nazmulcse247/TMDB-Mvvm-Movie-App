package com.nazmul.hiltmvvmapp.ui.base

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar

abstract class BaseActivity<D:ViewBinding> : AppCompatActivity() {
    protected lateinit var binding : D
    protected abstract fun viewBindingLayout(): D
    protected abstract fun initializeView(savedInstanceState: Bundle?)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = viewBindingLayout()
        setContentView(binding.root)
        initializeView(savedInstanceState)
    }

    protected fun showMessage(message:String?){
        Snackbar.make(findViewById(android.R.id.content),""+message, Snackbar.LENGTH_LONG).show()
    }

    protected fun showToastMessage(message:String?){
        Toast.makeText(applicationContext,message, Toast.LENGTH_SHORT).show()
    }

    protected fun showProgressBar(isLoading: Boolean,view: View){
        if (isLoading) {
            view.visibility = View.VISIBLE
            window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        } else {
            view.visibility = View.GONE
            window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        }
    }
}