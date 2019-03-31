package com.tokopedia.crysp

import android.Manifest
import android.annotation.TargetApi
import android.os.Build
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import com.crysp.sdk.CryspAPI

import kotlinx.android.synthetic.main.activity_crysp.*
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.R.string.ok
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityCompat.shouldShowRequestPermissionRationale
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog


class CryspActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crysp)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            // Get a reference to the APPs context
            val mContext = applicationContext
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) { // Check if Android 6.0+ and check if runtime permissions were granted
                        if (checkLocationPermission()) { // All Permissions are Available
                            CryspAPI.getInstance().initXAC(mContext, CryspActivity@this);
                        } else {
//                            requestMultiplePermissions();
                        }
            } else { // This is not Marshmallow. No runtime permissions required
                CryspAPI.getInstance().initXAC(mContext, CryspActivity@this);
            }
            // Additional code goes here
        }

    }

    val MY_PERMISSIONS_REQUEST_LOCATION = 99

    fun checkLocationPermission(): Boolean {
        if (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_FINE_LOCATION) !== PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                AlertDialog.Builder(this)
                        .setTitle("akses lokasi")
                        .setMessage("akses lokasi")
                        .setPositiveButton("OK", object : DialogInterface.OnClickListener {
                            override fun onClick(dialogInterface: DialogInterface, i: Int) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions(this@CryspActivity,
                                        arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                                        MY_PERMISSIONS_REQUEST_LOCATION)
                            }
                        })
                        .create()
                        .show()


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        arrayOf<String>(Manifest.permission.ACCESS_FINE_LOCATION),
                        MY_PERMISSIONS_REQUEST_LOCATION)
            }
            return false
        } else {
            return true
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            MY_PERMISSIONS_REQUEST_LOCATION -> {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.size > 0 && grantResults[0] === PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // location-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this,
                                    Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                    }

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.

                }
                return
            }
        }
//all is good, continue flow
            CryspAPI.getInstance().initXAC(this@CryspActivity, this@CryspActivity);
    }

}
