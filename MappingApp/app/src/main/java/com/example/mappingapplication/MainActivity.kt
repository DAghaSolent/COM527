package com.example.mappingapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.Button
import android.widget.EditText
import org.osmdroid.config.Configuration
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // This line sets the user agent, a requirement to download OSM maps
        Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));

        setContentView(R.layout.activity_main)
        val choice = findViewById<Button>(R.id.gobtn)
        val map1 = findViewById<MapView>(R.id.map1)
        map1.controller.setZoom(14.0)
        map1.controller.setCenter(GeoPoint(50.91, -1.36))

        choice.setOnClickListener {
            val etlong = findViewById<EditText>(R.id.etlong)
            val conLong = etlong.getText().toString().toDouble()
            val etlat = findViewById<EditText>(R.id.etlat)
            val conLat = etlat.getText().toString().toDouble()
            map1.controller.setCenter(GeoPoint(conLat, conLong))
        }
    }
}