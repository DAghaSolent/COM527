package com.example.roomappbasic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import org.w3c.dom.Text
import androidx.lifecycle.ViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = HittasticDatabase.getDatabase(this)

        db.hitDao().getAllHits().observe(this, Observer {
            findViewById<TextView>(R.id.tvResults).text = it.joinToString("\n");
        });

        val btnAdd = findViewById<Button>(R.id.btnAdd)
        val txtSong = findViewById<TextView>(R.id.txtSong)
        val txtArtist = findViewById<TextView>(R.id.txtArtist)
        val txtYear = findViewById<TextView>(R.id.txtYear)
        val txtId = findViewById<TextView>(R.id.txtId)

        btnAdd.setOnClickListener {

            lifecycleScope.launch {
                val s = txtSong.text.toString()
                val a = txtArtist.text.toString()
                val y = txtYear.text.toString().toInt()
                var id = 0L

                withContext(Dispatchers.IO) {
                    id = db.hitDao().insert(
                        Hit(
                            0,
                            s, a, y,
                            1
                        )
                    )
                }
                if(id > 0L) {
                    txtId.text = id.toString()
                }
            }
        }
    }
}
