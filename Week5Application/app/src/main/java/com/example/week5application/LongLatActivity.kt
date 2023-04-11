package com.example.week5application

import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import org.osmdroid.config.Configuration
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView

class LongLatActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        // This line sets the user agent, a requirement to download OSM maps
        Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));

        setContentView(R.layout.activity_map_long_lat)
        val choice = findViewById<Button>(R.id.gobtn)

        choice.setOnClickListener {
            val etlong = findViewById<EditText>(R.id.etlong)
            val conLong = etlong.getText().toString().toDouble()
            val etlat = findViewById<EditText>(R.id.etlat)
            val conLat = etlat.getText().toString().toDouble()

            val intent = Intent()
            val bundle = bundleOf("com.example.week5application.Longitude" to conLong, "com.example.week5application.Latitude" to conLat)
            intent.putExtras(bundle)

            setResult(RESULT_OK, intent)
            finish()
        }


    }
}