package com.example.permissions

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import android.Manifest
import android.content.pm.PackageManager
import android.location.LocationListener
import android.location.Location
import android.content.Context
import android.location.LocationManager
import android.preference.PreferenceManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import org.osmdroid.config.Configuration
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.ItemizedIconOverlay
import org.osmdroid.views.overlay.ItemizedOverlay
import org.osmdroid.views.overlay.OverlayItem

class MainActivity : AppCompatActivity(), LocationListener{

    var overlay_items : ItemizedIconOverlay<OverlayItem>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));
        setContentView(R.layout.activity_main)
        checkPermissions()

        overlay_items = ItemizedIconOverlay(this, arrayListOf<OverlayItem>(), null)
        val map1 = findViewById<MapView>(R.id.map1)
        map1.overlays.add(overlay_items)

        val riverside =  OverlayItem("Football Stadium", "Riverside Stadium", GeoPoint(54.5780, -1.2182))
        overlay_items?.addItem(riverside)
        riverside.setMarker(ContextCompat.getDrawable(this, R.drawable.stadium))

        // Add runtime permission handling, as described above (for you to do!!!)

        // this method should be called:
        // - if ACCESS_FINE_LOCATION permission is already granted
        // - as soon as a user has granted ACCESS_FINE_LOCATION permission

    }

    fun checkPermissions(){
        // Check to see if GPS permission has been granted already
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) ==  PackageManager.PERMISSION_GRANTED){
            requestLocation()
        } else {
            //If the permission hasn't been granted yet, request it from the user.
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 0)
        }
    }

    fun requestLocation(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) ==  PackageManager.PERMISSION_GRANTED){
            // note the use of 'as' to perform type casting in Kotlin
            // getSystemService() returns a superclass type of LocationManager,
            // so we need to cast it to LocationManager.
            val mgr = getSystemService(Context.LOCATION_SERVICE) as LocationManager

            mgr.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 0f, this)

        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions:Array<String>, grantResults: IntArray){
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            0 -> {
                if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    requestLocation()
                }
                else{
                    AlertDialog.Builder(this)
                        .setPositiveButton("OK", null) // add an OK button with an optional event handler
                        .setMessage("You have not accepted request Location.") // set the message
                        .show() // show the dialog
                }
            }
        }
    }
    override fun onLocationChanged(newLoc: Location) {
        val map1 = findViewById<MapView>(R.id.map1)
        map1.controller.setZoom(14.0)
        map1.controller.setCenter(GeoPoint(newLoc.latitude, newLoc.longitude))

        val runAway = OverlayItem("Middlesbrough", "NorthEast", GeoPoint(newLoc.latitude, newLoc.longitude))

        //val fernhurst = OverlayItem ("Fernhurst", "Village in West Sussex", GeoPoint)
        overlay_items?.addItem(runAway)

        Toast.makeText (this, "Location=${newLoc.latitude},${newLoc.longitude}", Toast.LENGTH_LONG).show()
    }

    override fun onProviderDisabled(provider: String) {
        Toast.makeText (this, "Provider disabled", Toast.LENGTH_LONG).show()
    }

    override fun onProviderEnabled(provider: String) {
        Toast.makeText (this, "Provider enabled", Toast.LENGTH_LONG).show()
    }

    // Deprecated at API level 29, but must still be included, otherwise your
    // app will crash on lower-API devices as their API will try and call it
    override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {

    }

}