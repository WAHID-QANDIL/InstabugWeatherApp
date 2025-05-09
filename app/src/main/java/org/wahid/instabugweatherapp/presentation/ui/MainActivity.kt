package org.wahid.instabugweatherapp.presentation.ui

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.navigation.compose.rememberNavController
import org.wahid.instabugweatherapp.presentation.navigation.AppNav
import org.wahid.instabugweatherapp.presentation.ui.screens.home.HomeViewModel

class MainActivity : ComponentActivity() {
    private val homeVM: HomeViewModel by viewModels<HomeViewModel>()
    private lateinit var permissionLauncher: ActivityResultLauncher<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

            permissionLauncher = registerForActivityResult(
                ActivityResultContracts.RequestPermission()
            ) { isGranted ->
                if (isGranted) homeVM.onPermissionGranted()
                else homeVM.onPermissionDenied()
            }


        enableEdgeToEdge()
        setContent {
            Log.d("setContent", "setContent: ${Thread.currentThread()}")

            val navHostController = rememberNavController()
            AppNav(homeViewModel = homeVM, navHostController = navHostController)
        }
    }

    override fun onStart() {
        super.onStart()

            if (ContextCompat.checkSelfPermission(
                    this, Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                homeVM.onPermissionGranted()
            } else {
                permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            }

    }

    override fun onStop() {
        super.onStop()
        homeVM.onStopUpdates()
    }
}