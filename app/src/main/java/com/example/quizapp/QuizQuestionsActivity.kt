package com.example.quizapp

import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {

    private var mCurrentPostion :Int=1
    private var mQuestionsList:ArrayList<Question>?=null
    private var mSelectedOption :Int=0

    private var progressBar :ProgressBar?=null
    private var progress :TextView?=null
    private var tvQuestion:TextView?=null
    private var tvImage:ImageView?=null
    private var tvOptionOne:TextView?=null
    private var tvOptionTwo:TextView?=null
    private var tvOptionThree:TextView?=null
    private var tvOptionFour:TextView?=null
    private var btnSubmit:Button?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        progressBar=findViewById(R.id.tvProgressBar)
        progress=findViewById(R.id.tvProgress)
        tvQuestion=findViewById(R.id.tvQuestion)
        tvImage=findViewById(R.id.tvImage)

        tvOptionOne=findViewById(R.id.tvOptionOne)
        tvOptionTwo=findViewById(R.id.tvOptionTwo)
        tvOptionThree=findViewById(R.id.tvOptionThree)
        tvOptionFour=findViewById(R.id.tvOptionFour)
        btnSubmit=findViewById(R.id.btnSubmit)

        tvOptionOne?.setOnClickListener(this)
        tvOptionTwo?.setOnClickListener(this)
        tvOptionThree?.setOnClickListener(this)
        tvOptionFour?.setOnClickListener(this)
        btnSubmit?.setOnClickListener (this)


         mQuestionsList = Constants.getQuestions()
        setQuestion()


    }

    private fun setQuestion() {



        val question: Question = mQuestionsList!![mCurrentPostion - 1]
        progressBar?.progress = mCurrentPostion
        progress?.text = "${mCurrentPostion}/${progressBar?.max?.plus(1)}"
        tvQuestion?.text = question.question
        tvImage?.setImageResource(question.image)

        tvOptionOne?.let {
            it.text = question.optionOne
        }

        tvOptionTwo?.let {
            it.text = question.optionTwo
        }

        tvOptionThree?.let {

            it.text = question.optionThree
        }
        tvOptionFour?.let {

            it.text = question.optionFour
        }


    }

    private fun defaultOptionView(){

        val option = ArrayList<TextView>()

        tvOptionOne?.let {

            option.add(0,it)
        }
        tvOptionTwo?.let {

            option.add(1,it)
        }
        tvOptionThree?.let {

            option.add(2,it)
        }
        tvOptionFour?.let {

            option.add(3 ,it)
        }

        option.forEach {

            it.setTextColor(Color.parseColor("#7A8089"))

            it.typeface= Typeface.DEFAULT
            it.background=ContextCompat.getDrawable(
                this,
                R.drawable.default_option_border_bg
            )

        }
    }

    private  fun selectedOptionView(tv:TextView,selectedOptionNum:Int)
    {
        defaultOptionView()

        mSelectedOption=selectedOptionNum
        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface,Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(this,R.drawable.default_option_border_bg)
    }
    override fun onClick(v: View?) {

        when(v?.id){

            R.id.tvOptionOne->{
                tvOptionOne?.let {
                    selectedOptionView(it,1)
                }
            }
            R.id.tvOptionTwo->{
                tvOptionTwo?.let {
                    selectedOptionView(it,2)
                }
            }
            R.id.tvOptionThree->{
                tvOptionThree?.let {
                    selectedOptionView(it,3)
                }
            }
            R.id.tvOptionFour->{
                tvOptionFour?.let {
                    selectedOptionView(it,4)
                }
            }

            R.id.btnSubmit ->{

                if (mSelectedOption ==0)
                {
                    mCurrentPostion++

                    when{

                        mCurrentPostion<= mQuestionsList?.size!! ->{

                            defaultOptionView()
                            setQuestion()

                        }

                        else ->{
                            Toast.makeText(this,"You made it to the end",Toast.LENGTH_SHORT)
                        }
                    }
                }else{

                    val question =mQuestionsList?.get(mCurrentPostion-1)
                    if(question!!.correctAnswer != mSelectedOption){
                        answerView(mSelectedOption ,R.drawable.wrong_option_border_bg)
                    }
                    answerView(question.correctAnswer,R.drawable.correct_option_border_bg)

                    if(mCurrentPostion == mQuestionsList?.size)
                    {
                        btnSubmit?.text ="Finish"
                    }else{

                        btnSubmit?.text="Next Question"
                    }

                    mSelectedOption=0
                }
            }
        }
    }

    private  fun answerView(answer:Int,drawableInt:Int)
    {
        when(answer)
        {
            1->{
                tvOptionOne?.background=ContextCompat.getDrawable(
                    this,
                    drawableInt

                )
            }
            2->{
                tvOptionTwo?.background=ContextCompat.getDrawable(
                    this,
                    drawableInt

                )
            }
            3->{
                tvOptionThree?.background=ContextCompat.getDrawable(
                    this,
                    drawableInt

                )
            }
            4->{
                tvOptionFour

                    ?.background=ContextCompat.getDrawable(
                    this,
                    drawableInt

                )
            }
        }
    }


}

