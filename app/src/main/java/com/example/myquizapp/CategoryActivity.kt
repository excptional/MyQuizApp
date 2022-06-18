package com.example.myquizapp

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView

class CategoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        val layoutGK: LinearLayout = findViewById(R.id.layoutGK)
        val layoutSports: LinearLayout = findViewById(R.id.layoutSports)
        val layoutScience: LinearLayout = findViewById(R.id.layoutScience)
        val layoutMath: LinearLayout = findViewById(R.id.layoutMath)
        val layoutHistory: LinearLayout = findViewById(R.id.layoutHistory)
        val layoutGeography: LinearLayout = findViewById(R.id.layoutGeography)
        val layoutPolitics: LinearLayout = findViewById(R.id.layoutPolitics)
        val layoutAnimals: LinearLayout = findViewById(R.id.layoutAnimals)
        val layoutMovies: LinearLayout = findViewById(R.id.layoutMovies)
        val cardGK: CardView = findViewById(R.id.cardGK)
        val cardSports: CardView = findViewById(R.id.cardSports)
        val cardScience: CardView = findViewById(R.id.cardScience)
        val cardMath: CardView = findViewById(R.id.cardMath)
        val cardHistory: CardView = findViewById(R.id.cardHistory)
        val cardGeography: CardView = findViewById(R.id.cardGeography)
        val cardPolitics: CardView = findViewById(R.id.cardPolitics)
        val cardAnimals: CardView = findViewById(R.id.cardAnimals)
        val cardMovies: CardView = findViewById(R.id.cardMovies)
        val text10: TextView = findViewById(R.id.text10)
        val text20: TextView = findViewById(R.id.text20)
        val text30: TextView = findViewById(R.id.text30)
        val text40: TextView = findViewById(R.id.text40)
        val text50: TextView = findViewById(R.id.text50)
        val textEasy: TextView = findViewById(R.id.text_easy)
        val textMedium: TextView = findViewById(R.id.text_medium)
        val textHard: TextView = findViewById(R.id.text_hard)

        var url: String = ""

        var difficulty: String = "easy"
        var numberOfQuestions: String = "5"

        val layoutStart: LinearLayout = findViewById(R.id.layout_start)
        layoutStart.isClickable = false

        var click = 0


        text10.setOnClickListener {
            text10.setTextColor(Color.parseColor("#2196F3"))
            text20.setTextColor(Color.WHITE)
            text30.setTextColor(Color.WHITE)
            text40.setTextColor(Color.WHITE)
            text50.setTextColor(Color.WHITE)
            numberOfQuestions = text10.text.toString()
        }
        text20.setOnClickListener {
            text20.setTextColor(Color.parseColor("#2196F3"))
            text10.setTextColor(Color.WHITE)
            text30.setTextColor(Color.WHITE)
            text40.setTextColor(Color.WHITE)
            text50.setTextColor(Color.WHITE)
            numberOfQuestions = text20.text.toString()
        }
        text30.setOnClickListener {
            text30.setTextColor(Color.parseColor("#2196F3"))
            text20.setTextColor(Color.WHITE)
            text10.setTextColor(Color.WHITE)
            text40.setTextColor(Color.WHITE)
            text50.setTextColor(Color.WHITE)
            numberOfQuestions = text30.text.toString()
        }
        text40.setOnClickListener {
            text40.setTextColor(Color.parseColor("#2196F3"))
            text20.setTextColor(Color.WHITE)
            text30.setTextColor(Color.WHITE)
            text10.setTextColor(Color.WHITE)
            text50.setTextColor(Color.WHITE)
            numberOfQuestions = text40.text.toString()
        }
        text50.setOnClickListener {
            text50.setTextColor(Color.parseColor("#2196F3"))
            text20.setTextColor(Color.WHITE)
            text30.setTextColor(Color.WHITE)
            text40.setTextColor(Color.WHITE)
            text10.setTextColor(Color.WHITE)
            numberOfQuestions = text50.text.toString()
        }

        textEasy.setOnClickListener {
            textEasy.setTextColor(Color.parseColor("#2196F3"))
            textMedium.setTextColor(Color.WHITE)
            textHard.setTextColor(Color.WHITE)
            difficulty = textEasy.text.toString()
        }
        textMedium.setOnClickListener {
            textMedium.setTextColor(Color.parseColor("#2196F3"))
            textEasy.setTextColor(Color.WHITE)
            textHard.setTextColor(Color.WHITE)

            difficulty = textMedium.text.toString()
        }
        textHard.setOnClickListener {
            textHard.setTextColor(Color.parseColor("#2196F3"))
            textEasy.setTextColor(Color.WHITE)
            textMedium.setTextColor(Color.WHITE)

            difficulty = textHard.text.toString()

        }

        layoutGK.setOnClickListener {
            click = 1
            layoutStart.isClickable = true
            cardGK.setCardBackgroundColor(Color.parseColor("#2196F3"))
            cardSports.setCardBackgroundColor(Color.parseColor("#27000000"))
            cardScience.setCardBackgroundColor(Color.parseColor("#27000000"))
            cardMath.setCardBackgroundColor(Color.parseColor("#27000000"))
            cardHistory.setCardBackgroundColor(Color.parseColor("#27000000"))
            cardGeography.setCardBackgroundColor(Color.parseColor("#27000000"))
            cardPolitics.setCardBackgroundColor(Color.parseColor("#27000000"))
            cardAnimals.setCardBackgroundColor(Color.parseColor("#27000000"))
            cardMovies.setCardBackgroundColor(Color.parseColor("#27000000"))

            url =
                "https://the-trivia-api.com/api/questions?categories=general_knowledge&limit=1&difficulty=$difficulty&region=IN"

        }
        layoutSports.setOnClickListener {
            click = 1
            layoutStart.isClickable = true
            cardGK.setCardBackgroundColor(Color.parseColor("#27000000"))
            cardSports.setCardBackgroundColor(Color.parseColor("#2196F3"))
            cardScience.setCardBackgroundColor(Color.parseColor("#27000000"))
            cardMath.setCardBackgroundColor(Color.parseColor("#27000000"))
            cardHistory.setCardBackgroundColor(Color.parseColor("#27000000"))
            cardGeography.setCardBackgroundColor(Color.parseColor("#27000000"))
            cardPolitics.setCardBackgroundColor(Color.parseColor("#27000000"))
            cardAnimals.setCardBackgroundColor(Color.parseColor("#27000000"))
            cardMovies.setCardBackgroundColor(Color.parseColor("#27000000"))

            url =
                "https://the-trivia-api.com/api/questions?categories=sport_and_leisure&limit=1&region=IN&difficulty=$difficulty"

        }
        layoutScience.setOnClickListener {
            click = 1
            layoutStart.isClickable = true
            cardGK.setCardBackgroundColor(Color.parseColor("#27000000"))
            cardSports.setCardBackgroundColor(Color.parseColor("#27000000"))
            cardScience.setCardBackgroundColor(Color.parseColor("#2196F3"))
            cardMath.setCardBackgroundColor(Color.parseColor("#27000000"))
            cardHistory.setCardBackgroundColor(Color.parseColor("#27000000"))
            cardGeography.setCardBackgroundColor(Color.parseColor("#27000000"))
            cardPolitics.setCardBackgroundColor(Color.parseColor("#27000000"))
            cardAnimals.setCardBackgroundColor(Color.parseColor("#27000000"))
            cardMovies.setCardBackgroundColor(Color.parseColor("#27000000"))

            url =
                "https://the-trivia-api.com/api/questions?categories=science&limit=1&region=IN&difficulty=$difficulty"

        }
        layoutMath.setOnClickListener {
            click = 1
            layoutStart.isClickable = true
            cardGK.setCardBackgroundColor(Color.parseColor("#27000000"))
            cardSports.setCardBackgroundColor(Color.parseColor("#27000000"))
            cardScience.setCardBackgroundColor(Color.parseColor("#27000000"))
            cardMath.setCardBackgroundColor(Color.parseColor("#2196F3"))
            cardHistory.setCardBackgroundColor(Color.parseColor("#27000000"))
            cardGeography.setCardBackgroundColor(Color.parseColor("#27000000"))
            cardPolitics.setCardBackgroundColor(Color.parseColor("#27000000"))
            cardAnimals.setCardBackgroundColor(Color.parseColor("#27000000"))
            cardMovies.setCardBackgroundColor(Color.parseColor("#27000000"))

            url =
                "https://the-trivia-api.com/api/questions?limit=1&region=IN&tags=mathematics"

        }
        layoutHistory.setOnClickListener {
            click = 1
            layoutStart.isClickable = true
            cardGK.setCardBackgroundColor(Color.parseColor("#27000000"))
            cardSports.setCardBackgroundColor(Color.parseColor("#27000000"))
            cardScience.setCardBackgroundColor(Color.parseColor("#27000000"))
            cardMath.setCardBackgroundColor(Color.parseColor("#27000000"))
            cardHistory.setCardBackgroundColor(Color.parseColor("#2196F3"))
            cardGeography.setCardBackgroundColor(Color.parseColor("#27000000"))
            cardPolitics.setCardBackgroundColor(Color.parseColor("#27000000"))
            cardAnimals.setCardBackgroundColor(Color.parseColor("#27000000"))
            cardMovies.setCardBackgroundColor(Color.parseColor("#27000000"))

            url =
                "https://the-trivia-api.com/api/questions?categories=history&limit=1&region=IN&difficulty=$difficulty"

        }
        layoutGeography.setOnClickListener {
            click = 1
            layoutStart.isClickable = true
            cardGK.setCardBackgroundColor(Color.parseColor("#27000000"))
            cardSports.setCardBackgroundColor(Color.parseColor("#27000000"))
            cardScience.setCardBackgroundColor(Color.parseColor("#27000000"))
            cardMath.setCardBackgroundColor(Color.parseColor("#27000000"))
            cardHistory.setCardBackgroundColor(Color.parseColor("#27000000"))
            cardGeography.setCardBackgroundColor(Color.parseColor("#2196F3"))
            cardPolitics.setCardBackgroundColor(Color.parseColor("#27000000"))
            cardAnimals.setCardBackgroundColor(Color.parseColor("#27000000"))
            cardMovies.setCardBackgroundColor(Color.parseColor("#27000000"))

            url =
                "https://the-trivia-api.com/api/questions?categories=geography&limit=1&region=IN&difficulty=$difficulty"

        }
        layoutPolitics.setOnClickListener {
            click = 1
            layoutStart.isClickable = true
            cardGK.setCardBackgroundColor(Color.parseColor("#27000000"))
            cardSports.setCardBackgroundColor(Color.parseColor("#27000000"))
            cardScience.setCardBackgroundColor(Color.parseColor("#27000000"))
            cardMath.setCardBackgroundColor(Color.parseColor("#27000000"))
            cardHistory.setCardBackgroundColor(Color.parseColor("#27000000"))
            cardGeography.setCardBackgroundColor(Color.parseColor("#27000000"))
            cardPolitics.setCardBackgroundColor(Color.parseColor("#2196F3"))
            cardAnimals.setCardBackgroundColor(Color.parseColor("#27000000"))
            cardMovies.setCardBackgroundColor(Color.parseColor("#27000000"))

            url =
                "https://the-trivia-api.com/api/questions?limit=1&region=IN&difficulty=$difficulty&tags=politics"

        }
        layoutAnimals.setOnClickListener {
            click = 1
            layoutStart.isClickable = true
            cardGK.setCardBackgroundColor(Color.parseColor("#27000000"))
            cardSports.setCardBackgroundColor(Color.parseColor("#27000000"))
            cardScience.setCardBackgroundColor(Color.parseColor("#27000000"))
            cardMath.setCardBackgroundColor(Color.parseColor("#27000000"))
            cardHistory.setCardBackgroundColor(Color.parseColor("#27000000"))
            cardGeography.setCardBackgroundColor(Color.parseColor("#27000000"))
            cardPolitics.setCardBackgroundColor(Color.parseColor("#27000000"))
            cardAnimals.setCardBackgroundColor(Color.parseColor("#2196F3"))
            cardMovies.setCardBackgroundColor(Color.parseColor("#27000000"))

            url =
                "https://the-trivia-api.com/api/questions?limit=1&region=IN&difficulty=$difficulty&tags=animals"

        }
        layoutMovies.setOnClickListener {
            click = 1
            layoutStart.isClickable = true
            cardGK.setCardBackgroundColor(Color.parseColor("#27000000"))
            cardSports.setCardBackgroundColor(Color.parseColor("#27000000"))
            cardScience.setCardBackgroundColor(Color.parseColor("#27000000"))
            cardMath.setCardBackgroundColor(Color.parseColor("#27000000"))
            cardHistory.setCardBackgroundColor(Color.parseColor("#27000000"))
            cardGeography.setCardBackgroundColor(Color.parseColor("#27000000"))
            cardPolitics.setCardBackgroundColor(Color.parseColor("#27000000"))
            cardAnimals.setCardBackgroundColor(Color.parseColor("#27000000"))
            cardMovies.setCardBackgroundColor(Color.parseColor("#2196F3"))

            url =
                "https://the-trivia-api.com/api/questions?categories=film_and_tv&limit=1&region=IN&difficulty=$difficulty"

        }

        layoutStart.setOnClickListener {
            if (click == 0) {
                Toast.makeText(
                    this,
                    "You are not select any category, select a category first",
                    Toast.LENGTH_LONG
                ).show()
            }else{
                val intent = Intent(this, DashboardActivity::class.java)
                intent.putExtra("url", url)
                intent.putExtra("noOfQuestion", numberOfQuestions)
                startActivity(intent)
                finish()

                difficulty = "easy"
                numberOfQuestions = "5"

                Handler().postDelayed({
                    layoutStart.isClickable = false
                    cardGK.setCardBackgroundColor(Color.parseColor("#27000000"))
                    cardSports.setCardBackgroundColor(Color.parseColor("#27000000"))
                    cardScience.setCardBackgroundColor(Color.parseColor("#27000000"))
                    cardMath.setCardBackgroundColor(Color.parseColor("#27000000"))
                    cardHistory.setCardBackgroundColor(Color.parseColor("#27000000"))
                    cardGeography.setCardBackgroundColor(Color.parseColor("#27000000"))
                    cardPolitics.setCardBackgroundColor(Color.parseColor("#27000000"))
                    cardAnimals.setCardBackgroundColor(Color.parseColor("#27000000"))
                    cardMovies.setCardBackgroundColor(Color.parseColor("#27000000"))
                }, 2000)
            }
        }

    }
}