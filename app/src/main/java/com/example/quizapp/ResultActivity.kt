package com.example.quizapp

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.TextView
import android.content.Intent
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.withStyledAttributes

class ResultActivity : AppCompatActivity() {

    private var tvName :TextView?=null
    private var tvResult :TextView?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.acitivity_result)

        tvName=findViewById(R.id.resultName)
        tvResult=findViewById(R.id.resultScore)

        tvName?.let {
            it.text=intent.getStringExtra(Constants.USER_NAME)
        }

        val currentAnswer= intent.getIntExtra(Constants.CORRECT_ANSWERS,0)
        val totalAnswers =intent.getIntExtra(Constants.TOTAL_QUESTIONS,0)

        tvResult?.let {

            it.text="Your Score is $currentAnswer/$totalAnswers"
        }

        findViewById<Button>(R.id.btnfinish).setOnClickListener {

            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }


    }
}

