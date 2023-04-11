package com.example.roomapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Coroutines
        // A coroutine is a separate process which runs in the background,
        // independendent of the main process

        // Find our add button

        val etTitle = findViewById<EditText>(R.id.etTitle)
        val etArtist = findViewById<EditText>(R.id.etArtist)
        val etYear = findViewById<EditText>(R.id.etYear)



        val db = HittasticDatabase.getDatabase(application)
        findViewById<Button>(R.id.btnCreate).setOnClickListener {
            // Launch the coroutine in the scope of the activity lifecycle
            // (when the activity is destroyed, the coroutine will terminate
            // as the activity is its parent)

            lifecycleScope.launch {
                // Read in the title, artist and year from the UI
                val title = findViewById<EditText>(R.id.etTitle).text.toString()
                val artist = findViewById<EditText>(R.id.etArtist).text.toString()
                val year = findViewById<EditText>(R.id.etYear).text.toString().toInt()

                // Declare a variable to hold the ID allocated to the new record
                var insertId = 0L

                // Switch to the background context to do the query
                withContext(Dispatchers.IO) {

                    val song =  Song(0, title, artist, year)

                    insertId = db.songDao().insert(song)
                }

                val etId = findViewById<EditText>(R.id.etId)
                etId.setText("$insertId")
            }
        }

        findViewById<Button>(R.id.btnSearch).setOnClickListener {
            lifecycleScope.launch {
                var song:Song? = null
                val etId = findViewById<EditText>(R.id.etId).text.toString().toLong()
                withContext(Dispatchers.IO) {
                    song =  db.songDao().getSongById(etId)
                }

                song?.apply {
                    etTitle.setText(this.title)
                    etArtist.setText(this.artist)
                    etYear.setText(this.year.toString())
                }
            }
        }

        findViewById<Button>(R.id.btnUpdate).setOnClickListener{
            lifecycleScope.launch{
                var song:Song? = null

                // Find the song by the inputted ID
                val etId =  findViewById<EditText>(R.id.etId).text.toString().toLong()

                // Update the Song Details
                val title = findViewById<EditText>(R.id.etTitle).text.toString()
                val artist = findViewById<EditText>(R.id.etArtist).text.toString()
                val year = findViewById<EditText>(R.id.etYear).text.toString().toInt()

                var updatedCount = 0

                withContext(Dispatchers.IO) {

                    //Found Song
                    val song =  Song(etId, title, artist, year)


                    updatedCount =  db.songDao().update(song)
                }


               if(updatedCount == 1){
                   AlertDialog.Builder(this@MainActivity)
                       .setPositiveButton("OK", null) // Adding an OK button
                       .setMessage("Song Updated") // Song Updated Message
                       .show() // Show the dialog
               } else {
                   AlertDialog.Builder(this@MainActivity)
                       .setPositiveButton("OK", null)
                       .setMessage("Song Can't be found")
                       .show() // Show the dialog
               }
            }
        }

        findViewById<Button>(R.id.btnDelete).setOnClickListener{
            lifecycleScope.launch{
                 var song:Song? = null

                // Find the song by the inputted ID
                val etId =  findViewById<EditText>(R.id.etId).text.toString().toLong()

                var updatedCount = 0


                withContext(Dispatchers.IO) {
                    // Found Song
                    val song = Song(etId, "", "", 0)

                    updatedCount = db.songDao().delete(song)
                }

                if(updatedCount ==  1){
                    AlertDialog.Builder(this@MainActivity)
                        .setPositiveButton("OK", null) // Adding an OK button
                        .setMessage("Song Deleted") // Song Updated Message
                        .show() //Show the dialog
                }else{
                    AlertDialog.Builder(this@MainActivity)
                        .setPositiveButton("OK", null)
                        .setMessage("Song Can't be found")
                        .show() // Show the dialog
                }
            }
        }
    }
}