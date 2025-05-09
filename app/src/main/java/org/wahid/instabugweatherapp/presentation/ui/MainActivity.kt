package org.wahid.instabugweatherapp.presentation.ui

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts.RequestPermission
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.navigation.compose.rememberNavController
import org.wahid.instabugweatherapp.presentation.navigation.AppNav
import org.wahid.instabugweatherapp.presentation.ui.screens.home.HomeViewModel

class MainActivity : ComponentActivity() {
    private val homeVM: HomeViewModel by viewModels<HomeViewModel>()

    @SuppressLint("MissingPermission")
    private  var locationPermissionLauncher: ActivityResultLauncher<String> =  registerForActivityResult(
        RequestPermission()
    ) { granted ->
        if (granted) {
            homeVM.onPermissionGranted()
        } else {
            if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {
               requestLocationPermission(this)
            } else {
                showSettingsDialog()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            Log.d("setContent", "setContent: ${Thread.currentThread()}")

            val navHostController = rememberNavController()
            AppNav(homeViewModel = homeVM, navHostController = navHostController)
        }
    }

    override fun onStart() {
        super.onStart()
        requestLocationPermission(this)
    }

    private fun requestLocationPermission(context: Context) {
        when {
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED -> {
                homeVM.onPermissionGranted()
            }

            shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION) -> {
                showRationaleDialog {
                    locationPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
                }
            }

            else -> {
                locationPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            }
        }
    }

    override fun onStop() {
        super.onStop()
        homeVM.onStopUpdates()
    }

    private fun showRationaleDialog(onProceed: () -> Unit) {
        AlertDialog.Builder(this)
            .setTitle("Location Permission Needed")
            .setMessage("This feature requires location access to show weather data for your area.")
            .setPositiveButton("Allow") { _, _ -> onProceed() }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun showSettingsDialog() {
        AlertDialog.Builder(this)
            .setTitle("Permission Permanently Denied")
            .setMessage("Please enable location in app settings to use this feature.")
            .setPositiveButton("Settings") { _, _ ->
                Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                    data = Uri.fromParts("package", packageName, null)
                    startActivity(this)
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

}