package com.example.week3calculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.view.View.OnClickListener
import android.view.View
import android.widget.TextView
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val add = findViewById<Button>(R.id.btn1)
        val subtract = findViewById<Button>(R.id.btn2)
        val multiply = findViewById<Button>(R.id.btn3)
        val divide = findViewById<Button>(R.id.btn4)

        add.setOnClickListener{
            val et1 = findViewById<EditText>(R.id.et1)
            val val1 = et1.getText().toString().toDouble()
            val et2 = findViewById<EditText>(R.id.et2)
            val val2 = et2.getText().toString().toDouble()
            val calculation = val1 + val2
            val tv1 = findViewById<TextView>(R.id.tv1)
            tv1.setText("Addition of $val1 and $val2 is : $calculation")
        }

        subtract.setOnClickListener{
            val et1 = findViewById<EditText>(R.id.et1)
            val val1 = et1.getText().toString().toDouble()
            val et2 = findViewById<EditText>(R.id.et2)
            val val2 = et2.getText().toString().toDouble()
            val calculation = val1 - val2
            val tv1 = findViewById<TextView>(R.id.tv1)
            tv1.setText("Subtraction of $val1 and $val2 is : $calculation")
        }

        multiply.setOnClickListener{
            val et1 = findViewById<EditText>(R.id.et1)
            val val1 = et1.getText().toString().toDouble()
            val et2 = findViewById<EditText>(R.id.et2)
            val val2 = et2.getText().toString().toDouble()
            val calculation = val1 * val2
            val tv1 = findViewById<TextView>(R.id.tv1)
            tv1.setText("Multiplication of $val1 and $val2 is : $calculation")
        }

        divide.setOnClickListener{
            val et1 = findViewById<EditText>(R.id.et1)
            val val1 = et1.getText().toString().toDouble()
            val et2 = findViewById<EditText>(R.id.et2)
            val val2 = et2.getText().toString().toDouble()
            val calculation = val1 / val2
            val tv1 = findViewById<TextView>(R.id.tv1)
            tv1.setText("Division of $val1 and $val2 is : $calculation")
        }
    }
}