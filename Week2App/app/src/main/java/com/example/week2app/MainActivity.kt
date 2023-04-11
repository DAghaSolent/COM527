package com.example.week2app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.view.View.OnClickListener
import android.view.View
import android.widget.TextView
import android.widget.EditText

class MainActivity : AppCompatActivity () {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val b = findViewById<Button>(R.id.btn1)
        b.setOnClickListener {
            val et1 = findViewById<EditText>(R.id.et1)
            val feet = et1.getText().toString().toDouble()
            val metres = feet*0.305
            val tv1 = findViewById<TextView>(R.id.tv1)
            tv1.setText("In metres that is: $metres")
        }
    }
}