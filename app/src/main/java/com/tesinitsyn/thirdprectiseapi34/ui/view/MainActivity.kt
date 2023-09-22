package com.tesinitsyn.thirdprectiseapi34.ui.view

import android.Manifest
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.tesinitsyn.thirdprectiseapi34.R
import com.tesinitsyn.thirdprectiseapi34.databinding.ActivityMainBinding
import com.tesinitsyn.thirdprectiseapi34.ui.di.productModel
import com.tesinitsyn.thirdprectiseapi34.ui.viewModel.ProductViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.GlobalContext
import org.koin.dsl.module

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(HomeFragment())
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.homeFragment -> replaceFragment(HomeFragment())
                R.id.listFragment -> replaceFragment(ListFragment())
                R.id.cameraFragment -> replaceFragment(CameraFragment())
                R.id.datesFragment -> replaceFragment(DatesFragment())
                R.id.retrofitFragment -> replaceFragment(InternetFragment())
                else -> {
                }
            }
            true
        }

        GlobalContext.startKoin {
            androidLogger()
            androidContext(this@MainActivity)
            modules(module {
                viewModel { ProductViewModel(application) }
            })
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    companion object {
        private const val TAG = "CameraXApp"
        private const val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"
        val REQUIRED_PERMISSIONS =
            mutableListOf(
                Manifest.permission.CAMERA,
                Manifest.permission.RECORD_AUDIO
            ).apply {
                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) {
                    add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                }
            }.toTypedArray()
    }
}