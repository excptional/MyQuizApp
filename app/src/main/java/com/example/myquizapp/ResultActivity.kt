package com.example.myquizapp

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.mikhaellopez.circularprogressbar.CircularProgressBar

class ResultActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val count  = intent.getIntExtra("count",10)
        val correctCount = intent.getIntExtra("correctCount", 10)

        val circularProgressBar = findViewById<CircularProgressBar>(R.id.circularProgressBar)
        circularProgressBar.apply {

            // Set Progress
            progress = correctCount.toFloat()

            // or with animation
            setProgressWithAnimation(correctCount.toFloat(), 1000) // =1s

            // Set Progress Max
            progressMax = count.toFloat()

            progressDirection = CircularProgressBar.ProgressDirection.TO_RIGHT
        }

        val textInside : TextView = findViewById(R.id.text_inside)
        val textCorrect : TextView = findViewById(R.id.text_correct)
        val textInCorrect : TextView = findViewById(R.id.text_incorrect)
        val textPercentageScore : TextView = findViewById(R.id.text_score)

        val incorrectCount = count - correctCount
        val percentage : Int = ((correctCount.toFloat() / count.toFloat()) * 100).toInt()

        textInside.text = "$correctCount/$count"
        textCorrect.text = "Correct Answers : $correctCount"
        textInCorrect.text = "Incorrect Answers : $incorrectCount"
        textPercentageScore.text = "Percentage Score : $percentage%"

        val share : LinearLayout = findViewById(R.id.layout_share)
        share.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT,"Hey, checkout this Quiz Application I played and score : $correctCount/$count")
            val chooser = Intent.createChooser(intent,"Share your score using.....")
            startActivity(chooser)
        }

        val returnBtn : ImageView = findViewById(R.id.returnIcon)
        returnBtn.setOnClickListener{
            val intent = Intent(this, CategoryActivity::class.java)
            startActivity(intent)
            finish()

        }
    }
}
