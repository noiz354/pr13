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
import android.util.Log
import android.widget.Toast
import com.crysp.sdk.CryspNetworkManager


open class CryspActivity : AppCompatActivity() {
    val TAG = "Tokopedia"

    val MY_API_KEY = "08B0002A6A064DE3A372378EBB4DE906"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    protected fun test(username:String) {
        val mContext = applicationContext
        CryspNetworkManager.sendAysncRequest(mContext, username, MY_API_KEY) {
            if (it.getErrorCode() == 1) { // Server Returned an Error
                val errorMsg = it.errorMsg;
                // Do some Error handling here
            } else { // Received a Valid Response from Server
                val txn_Id = it.xtid;
                val timestamp = it.timestamp;
                val hmac = it.hmac;
                val isDeviceInitialized = it.getIsDevInit();
                // Now submit the user Credentials and CryspResponse
                // to your Authentication Server
                Log.i(TAG, "txn_Id ${txn_Id} timestamp ${timestamp} hmac ${hmac} isDeviceInitialized ${isDeviceInitialized}")

            }
        }
    }

    val PERMISSION_REQUEST_CONTACT = 120;
    fun askForContactPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {

                // Should we show an explanation?
                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                                Manifest.permission.READ_CONTACTS)) {
                    val builder = AlertDialog.Builder(this)
                    builder.setTitle("Contacts access needed")
                    builder.setPositiveButton(android.R.string.ok, null)
                    builder.setMessage("please confirm Contacts access")//TODO put real question
                    builder.setOnDismissListener {
                        requestPermissions(
                                arrayOf(Manifest.permission.READ_CONTACTS,
                                        Manifest.permission.WRITE_CONTACTS,
                                        Manifest.permission.ACCESS_WIFI_STATE,
                                        Manifest.permission.ACCESS_COARSE_LOCATION,
                                        Manifest.permission.ACCESS_FINE_LOCATION), PERMISSION_REQUEST_CONTACT)
                    }
                    builder.show()
                    // Show an expanation to the user *asynchronously* -- don't block
                    // this thread waiting for the user's response! After the user
                    // sees the explanation, try again to request the permission.

                } else {

                    // No explanation needed, we can request the permission.

                    ActivityCompat.requestPermissions(this,
                            arrayOf(Manifest.permission.READ_CONTACTS,
                                    Manifest.permission.WRITE_CONTACTS,
                                    Manifest.permission.ACCESS_WIFI_STATE,
                                    Manifest.permission.ACCESS_COARSE_LOCATION,
                                    Manifest.permission.ACCESS_FINE_LOCATION),
                            PERMISSION_REQUEST_CONTACT)

                    // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                    // app-defined int constant. The callback method gets the
                    // result of the request.
                }
            } else {
//                getContact()
            }
        } else {
//            getContact()
        }
    }

    protected fun doexcitingThings(){
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
            PERMISSION_REQUEST_CONTACT -> {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.size > 0 && grantResults[0] === PackageManager.PERMISSION_GRANTED) {
//                    getContact()
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {
//                    Toast.makeText(getActivity(), "No permission for contacts")
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
