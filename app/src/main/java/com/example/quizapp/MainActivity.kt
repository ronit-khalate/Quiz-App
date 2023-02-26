package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnStart :Button=findViewById(R.id.btn_start)

        btnStart.setOnClickListener {

            val ipText :EditText=findViewById(R.id.ipName)

            if(ipText.text.isEmpty())
            {
                Toast.makeText(this,"Please Enter Your Name",Toast.LENGTH_SHORT).show()
            }
            else
            {
                val intent :Intent=Intent(this,QuizQuestionsActivity::class.java)
                intent.putExtra(Constants.USER_NAME,ipText.text.toString())
                startActivity(intent)
                /*
                * finish() method closes the current activity
                * */
                finish()

            }

        }
    }

}