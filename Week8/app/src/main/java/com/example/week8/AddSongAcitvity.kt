package com.example.week8

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import android.widget.Button

import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.result.Result
import android.widget.Toast

class AddSongActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_song)

        val songTitle = findViewById<EditText>(R.id.songTitle_field)
        val songArtist = findViewById<EditText>(R.id.songArtist_field)
        val songYear =  findViewById<EditText>(R.id.songYear_field)
        val addnewSongBtn = findViewById<Button>(R.id.addSongBtn)

        addnewSongBtn.setOnClickListener {
            // Check for non-null values first and display error messages as appropriate
            if(songTitle?.text.toString().trim() == "")
            {
                songTitle.error = "The title field cannot be empty!"
            } else if(songArtist?.text.toString().trim() == "") {
                songArtist.error = "The artist field cannot be empty!"
            }

            else {
                // Obtain the values entered in each of the EditTexts to use them for sending a POST request to the server
                val songtitle = songTitle.text.toString()
                val songartist = songArtist.text.toString()
                val songyear = songYear.text.toString()

                // Web communication here

                var url =  "http://10.0.2.2:3000/song/create"
                val createSongPost = listOf("title" to songtitle, "artist" to songartist, "year" to songyear)
                url.httpPost(createSongPost).response{ request, response, result ->
                    when(result){
                        is Result.Success -> {
                            // If the POST request is successful
                            Toast.makeText(this, result.get().decodeToString(), Toast.LENGTH_LONG).show()
                        }

                        is Result.Failure -> {
                            // If the POST request failed
                            Toast.makeText(this, result.error.message, Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }

        }

    }
}