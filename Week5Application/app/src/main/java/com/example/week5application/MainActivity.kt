package com.example.week5application

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView


class MainActivity : AppCompatActivity() {
    lateinit var map1: MapView

    // Create the Launcher
    val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        val returnIntent: Intent? = it.data

        // The lambda function starts here
        // Check that we get an OK (success) result from the second activity
        if (it.resultCode == RESULT_OK) {
            it.data?.apply {
                val opentopomap = this.getBooleanExtra(
                    "com.example.opentopomap",
                    false
                ) // false is a default value

                // See "lateinit" in week 6 for a way of doing this more efficiently

                map1.setTileSource(if (opentopomap) TileSourceFactory.OpenTopo else TileSourceFactory.MAPNIK)
            }
        }
        // The lambda function ends here
    }

    val longlatlauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        val returnIntent: Intent? = it.data

        it.data?.apply{
            val long = this.getDoubleExtra(
                "com.example.week5application.Longitude",
                0.0
            )

            val lat = this.getDoubleExtra(
                "com.example.week5application.Latitude",
                0.0
            )

            map1.controller.setCenter(GeoPoint(lat, long))
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // todo --> add the map like how we did in week 3

        Log.d("lifecycle_app", "onCreate")

        Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));

        setContentView(R.layout.activity_main)
        map1 = findViewById<MapView>(R.id.map1)
        map1.controller.setZoom(14.0)
        map1.controller.setCenter(GeoPoint(50.91, -1.36))

    }

    // Inflate the menu
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    // Handle menu option choices

    override fun onOptionsItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.choosemap -> {
                // Launch the secondary activity

                // Create the Intent
                val intent = Intent(this, MapChooseActivity::class.java)

                launcher.launch(intent)

                return true
            }

            R.id.longlatmap -> {
                // Launch the third activity to showcase long and lat map

                val intent = Intent(this, LongLatActivity::class.java)

                longlatlauncher.launch(intent)

                return true
            }

            R.id.preferences -> {
                //Launch the 4th activity to showcase the preferences

                val intent = Intent(this, MyPrefsActivity::class.java)

                startActivity(intent)

                return true
            }
        }
        return false
    }

    override fun onStart(){
        super.onStart()
        Log.d("lifecycle_app", "onStart")
    }

    override fun onResume(){
        super.onResume()
        val prefs = PreferenceManager.getDefaultSharedPreferences(applicationContext)
        val lat = prefs.getString("lat", "50.9")?.toDouble() ?: 50.9
        val lon = prefs.getString("lon", "-1.4")?.toDouble() ?: -1.4
        val zoom = prefs.getString("zoom", "14")?.toDouble() ?: 14.0
        val checkbox = prefs.getBoolean("TestCheckBox", false) ?: false

        map1.controller.setZoom(zoom)
        map1.controller.setCenter(GeoPoint(lat, lon))

        if(checkbox == true) {
            Log.d("lifecycle_app", "Checkbox Preference has been set")
        }
    }

    override fun onStop(){
        super.onStop()
        Log.d("lifecycle_app", "onStop")
    }

    override fun onPause(){
        super.onPause()
        Log.d("lifecycle_app", "onPause")
    }

    override fun onDestroy(){
        super.onDestroy()
        Log.d("lifecycle_app", "onDestroy")
    }










}