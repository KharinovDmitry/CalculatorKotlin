package com.example.calculator

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val buttons = arrayOf(findViewById<Button>(R.id.button1),
                findViewById(R.id.button2),
                findViewById(R.id.button3),
                findViewById(R.id.button4),
                findViewById(R.id.button5),
                findViewById(R.id.button6),
                findViewById(R.id.button7),
                findViewById(R.id.button8),
                findViewById(R.id.button9),
                findViewById(R.id.button0),
                findViewById(R.id.button_plus),
                findViewById(R.id.button_minus),
            )


        for (button in buttons) {
            button.setOnClickListener { addInput(button.text.toString()) }
        }

       findViewById<Button>(R.id.button_calc).setOnClickListener {
           val res = calc(findViewById<TextView>(R.id.text_view_input).text.toString())
           findViewById<TextView>(R.id.text_view_result).text = res.toString()
           findViewById<TextView>(R.id.text_view_input).text = ""
       }

    }

    private fun addInput(input: String) {
        with(findViewById<TextView>(R.id.text_view_input)) {
            this.text = this.text.toString() + input
        }
    }

    private fun calc(input: String): Int {
        var result = 0
        var currentNumber = StringBuilder()
        var currentSign = 1

        for (char in input) {
            when {
                char.isDigit() -> {
                    currentNumber.append(char)
                }
                char == '+' -> {
                    if (currentNumber.isNotEmpty()) {
                        result += currentSign * currentNumber.toString().toInt()
                        currentNumber.clear()
                    }
                    currentSign = 1
                }
                char == '-' -> {
                    if (currentNumber.isNotEmpty()) {
                        result += currentSign * currentNumber.toString().toInt()
                        currentNumber.clear()
                    }
                    currentSign = -1
                }
            }
        }

        if (currentNumber.isNotEmpty()) {
            result += currentSign * currentNumber.toString().toInt()
        }

        return result
    }
}