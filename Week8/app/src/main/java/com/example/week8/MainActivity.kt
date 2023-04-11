package com.example.week8

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.github.kittinunf.fuel.core.Parameters
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import com.github.kittinunf.fuel.json.responseJson


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val searchButton = findViewById<Button>(R.id.searchSongArtistBtn)
        val search = findViewById<EditText>(R.id.songArtistSearchName)
        val tv =  findViewById<TextView>(R.id.webTextDisplay)

        searchButton.setOnClickListener {

            // URL Returns JSON information about songs that matches artists
            val artistName = search.text.toString()
            var url =  "http://10.0.2.2:3000/artist/$artistName"

            url.httpGet().responseJson { request, response, result ->
                when(result) {
                    is Result.Success -> {
                        val jsonArray = result.get().array()
                        var str = ""

                        for(i in 0 until jsonArray.length()) {
                            val curObj = jsonArray.getJSONObject(i)
                            str += "title: ${curObj.getString("title")} artist:${curObj.getString("artist")}"
                        }
                        // result.get() gives ByteArray, decode to string
                      //  tv.text = result.get().decodeToString()
                        tv.text = str
                    }

                    is Result.Failure -> {
                        // is failure if HTTP error
                        tv.text = "ERROR ${result.error.message}"
                    }
                }
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
       when(item.itemId) {
           R.id.addsongActivity -> {
               val intent = Intent(this, AddSongActivity::class.java)
               startActivity(intent)
               return true
           }
       }
        return false
    }
}