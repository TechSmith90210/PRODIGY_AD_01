package com.example.calculator_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import org.mariuszgromada.math.mxparser.Expression

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val output = findViewById<TextView>(R.id.output)
        val canAddOperator = false

        // Set OnClickListener for digit buttons
        val digitButtonIds = arrayOf(
            R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
            R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9
        )

        for (buttonId in digitButtonIds) {
            findViewById<Button>(buttonId).setOnClickListener {
                val digit = (it as Button).text.toString()
                val currentText = output.text.toString()
                output.text = if (currentText == "0") digit else currentText + digit
            }
        }
        // set onclicklistener for operators
        val operators = arrayOf(
            R.id.btnplus, R.id.btnminus, R.id.btnmultiply, R.id.btndivide, R.id.btnpercent
        )

        for (operator in operators) {
            findViewById<Button>(operator).setOnClickListener {
                val operator = (it as Button).text.toString()
                val currentText = output.text.toString()
                output.text =
                    if (currentText == "0" && !canAddOperator) operator else currentText + operator
            }
        }

        //functionality for equal button
        val equalsButton = findViewById<Button>(R.id.btnequal)
        equalsButton.setOnClickListener {
            val expression = output.text.toString()
            val result = evaluateExpression(expression)
            output.text = result.toString()
        }

        //functionality for clear button
        val clearbtn = findViewById<Button>(R.id.btnclear)
        clearbtn.setOnClickListener {
            output.text = "0"
        }

    }

    //function to evaluate expression
    private fun evaluateExpression(expression: String): Double {
        val parsedExpression = expression.replace("x", "*")
        return Expression(parsedExpression).calculate()
    }
}