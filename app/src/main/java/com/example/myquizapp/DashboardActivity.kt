package com.example.myquizapp

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.google.android.material.progressindicator.LinearProgressIndicator

class DashboardActivity : AppCompatActivity() {

    var timerValue = 10
    var timerValueInLong: Long = timerValue.toLong() * 1000
    private var count = 0
    private var correctCount = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val url: String = intent.getStringExtra("url").toString()
        val noOfQuestion: String = intent.getStringExtra("noOfQuestion").toString()

        val card2: CardView = findViewById(R.id.card2)
        val card3: CardView = findViewById(R.id.card3)
        val card4: CardView = findViewById(R.id.card4)
        val card5: CardView = findViewById(R.id.card5)

        card2.isClickable = true
        card3.isClickable = true
        card4.isClickable = true
        card5.isClickable = true


        val linearProgressBar: LinearProgressIndicator = findViewById(R.id.linearProgressBar)
        val timer = object : CountDownTimer(timerValueInLong, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                linearProgressBar.progress = timerValue
                timerValue--
            }

            override fun onFinish() {
                linearProgressBar.progress = 0

                val dialog = Dialog(this@DashboardActivity)
                dialog.setCanceledOnTouchOutside(false)
                dialog.setCancelable(false)
                dialog.onBackPressed()

                ///make the dialog background transparent
                dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

                dialog.setContentView(R.layout.dialog_window)

                ///when we click the Next Question button on the dialog box, then the timer stop, fetchData function run and the dialog hide
                val dialogBTN: LinearLayout = dialog.findViewById(R.id.dialog_btn)
                dialogBTN.setOnClickListener {

                    this.cancel()
                    timerValue = 10
                    count++
                    fetchData(this, url)
                    dialog.hide()
                }
                dialog.show()

                val nextBtn: LinearLayout = findViewById(R.id.next_btn_LinearLayout)
                nextBtn.visibility = View.VISIBLE
                count++
            }
        }

        fetchData(timer, url)

        val nextBtn: LinearLayout = findViewById(R.id.next_btn_LinearLayout)
        nextBtn.setOnClickListener {
            count++
            if (count.toString() == noOfQuestion) {
                finished(timer)
            } else {
                timer.cancel()
                timerValue = 10
                fetchData(timer, url)
            }
        }

        val progressBar: ProgressBar = findViewById(R.id.progressBar)
        val tryAgainBTN: LinearLayout = findViewById(R.id.tryAgain_btn)
        tryAgainBTN.setOnClickListener {
            progressBar.visibility = View.VISIBLE
            fetchData(timer, url)
        }

        val topBar: RelativeLayout = findViewById(R.id.topBar)

        val exitText: TextView = topBar.findViewById(R.id.exitText)
        exitText.setOnClickListener {
            timer.cancel()
            val dialog = Dialog(this@DashboardActivity)
            dialog.setCanceledOnTouchOutside(false)
            dialog.setCancelable(false)

            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.setContentView(R.layout.exit_dialog)
            dialog.show()

            val exitYes: TextView = dialog.findViewById(R.id.yes_btn_text_exit)
            val exitNo: TextView = dialog.findViewById(R.id.no_btn_text_exit)

            exitYes.setOnClickListener {
                finished(timer)
                dialog.hide()
            }
            exitNo.setOnClickListener {
                dialog.hide()
                if (timerValue == 10) {
                    count++
                    if (count.toString() == noOfQuestion) {
                        finished(timer)
                    } else {
                        timer.cancel()
                        timerValue = 10
                        fetchData(timer, url)
                    }
                } else {
                    timer.start()
                }
            }
        }

        val backBtn: ImageView = topBar.findViewById(R.id.backIcon)
        backBtn.setOnClickListener {
            timer.cancel()
            val dialog = Dialog(this@DashboardActivity)

            dialog.setCanceledOnTouchOutside(false)
            dialog.setCancelable(false)
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.setContentView(R.layout.dialog_return_to_menu)
            dialog.show()

            val returnNo: TextView = dialog.findViewById(R.id.no_btn_text_return)
            val returnYes: TextView = dialog.findViewById(R.id.yes_btn_text_return)


            returnYes.setOnClickListener {
                timer.cancel()
                dialog.hide()
                val intent = Intent(this, CategoryActivity::class.java)
                startActivity(intent)
                finish()
                timerValue = 10
            }
            returnNo.setOnClickListener {
                dialog.hide()
                if (timerValue == 10) {
                    count++
                    if (count.toString() == noOfQuestion) {
                        finished(timer)
                    } else {
                        timer.cancel()
                        timerValue = 10
                        fetchData(timer, url)
                    }
                } else {
                    timer.start()
                }
            }
        }


    }


    private fun fetchData(timer: CountDownTimer, url: String) {

        val progressBar: ProgressBar = findViewById(R.id.progressBar)

        val question: TextView = findViewById(R.id.question)
        val optionA: TextView = findViewById(R.id.optionA)
        val optionB: TextView = findViewById(R.id.optionB)
        val optionC: TextView = findViewById(R.id.optionC)
        val optionD: TextView = findViewById(R.id.optionD)
        val dashBoardLayout: LinearLayout = findViewById(R.id.dashBoardActivity)
        val onlineLayout: LinearLayout = findViewById(R.id.onlineLayout)
        val offlineLayout: LinearLayout = findViewById(R.id.offlineLayout)


        val jsonArrayRequest = JsonArrayRequest(
            Request.Method.GET, url, null,
            {
                progressBar.visibility = View.GONE
                onlineLayout.visibility = View.VISIBLE
                offlineLayout.visibility = View.GONE
                timer.start()

                var myCount = count + 1

                val questionCount: TextView = findViewById(R.id.questionCount)
                questionCount.text = "Q.$myCount"

                val resultsJsonArray = it
                question.text = resultsJsonArray.getJSONObject(0).getString("question").toString()

                val correctANS: String =
                    resultsJsonArray.getJSONObject(0).getString("correctAnswer").toString()

                val incorrectAnsJSONArray =
                    resultsJsonArray.getJSONObject(0).getJSONArray("incorrectAnswers")

                var answers: MutableList<String> = mutableListOf()
                answers.add(correctANS)

                answers.add(incorrectAnsJSONArray[0].toString())
                answers.add(incorrectAnsJSONArray[1].toString())
                answers.add(incorrectAnsJSONArray[2].toString())

                answers.shuffle()

                isCorrectAns(correctANS, answers, timer)

                val nextBtn: LinearLayout = findViewById(R.id.next_btn_LinearLayout)
                nextBtn.visibility = View.GONE

            },
            {
                progressBar.visibility = View.GONE
                onlineLayout.visibility = View.GONE
                offlineLayout.visibility = View.VISIBLE

            })
        MySingleton.getInstance(this).addToRequestQueue(jsonArrayRequest)
    }

    private fun isCorrectAns(ans: String, ansList: MutableList<String>, timer: CountDownTimer) {

        val card2: CardView = findViewById(R.id.card2)
        val card3: CardView = findViewById(R.id.card3)
        val card4: CardView = findViewById(R.id.card4)
        val card5: CardView = findViewById(R.id.card5)
        val optionA: TextView = findViewById(R.id.optionA)
        val optionB: TextView = findViewById(R.id.optionB)
        val optionC: TextView = findViewById(R.id.optionC)
        val optionD: TextView = findViewById(R.id.optionD)

        card2.isClickable = true
        card3.isClickable = true
        card4.isClickable = true
        card5.isClickable = true

        card2.setCardBackgroundColor(Color.WHITE)
        card3.setCardBackgroundColor(Color.WHITE)
        card4.setCardBackgroundColor(Color.WHITE)
        card5.setCardBackgroundColor(Color.WHITE)

        optionA.text = ansList[0]
        optionB.text = ansList[1]
        optionC.text = ansList[2]
        optionD.text = ansList[3]

        card2.setOnClickListener {

            timer.cancel()
            timerValue = 10

            card2.isClickable = false
            card3.isClickable = false
            card4.isClickable = false
            card5.isClickable = false

            val nextBtn: LinearLayout = findViewById(R.id.next_btn_LinearLayout)
            nextBtn.visibility = View.VISIBLE

            when (ans) {
                optionA.text -> {
                    card2.setCardBackgroundColor(Color.parseColor("#61FA68")) //Green
                    correctCount++
                }
                optionB.text -> {
                    card3.setCardBackgroundColor(Color.parseColor("#61FA68")) //Green
                    card2.setCardBackgroundColor(Color.parseColor("#FD5044")) //Red
                }
                optionC.text -> {
                    card4.setCardBackgroundColor(Color.parseColor("#61FA68")) //Green
                    card2.setCardBackgroundColor(Color.parseColor("#FD5044")) //Red
                }
                optionD.text -> {
                    card5.setCardBackgroundColor(Color.parseColor("#61FA68")) //Green
                    card2.setCardBackgroundColor(Color.parseColor("#FD5044")) //Red
                }
            }
        }

        card3.setOnClickListener {

            timer.cancel()
            timerValue = 10

            card2.isClickable = false
            card3.isClickable = false
            card4.isClickable = false
            card5.isClickable = false

            val nextBtn: LinearLayout = findViewById(R.id.next_btn_LinearLayout)
            nextBtn.visibility = View.VISIBLE

            when (ans) {
                optionA.text -> {
                    card2.setCardBackgroundColor(Color.parseColor("#61FA68")) //Green
                    card3.setCardBackgroundColor(Color.parseColor("#FD5044")) //Red
                }
                optionB.text -> {
                    card3.setCardBackgroundColor(Color.parseColor("#61FA68")) //Green
                    correctCount++
                }
                optionC.text -> {
                    card4.setCardBackgroundColor(Color.parseColor("#61FA68")) //Green
                    card3.setCardBackgroundColor(Color.parseColor("#FD5044")) //Red
                }
                optionD.text -> {
                    card5.setCardBackgroundColor(Color.parseColor("#61FA68")) //Green
                    card3.setCardBackgroundColor(Color.parseColor("#FD5044")) //Red
                }
            }

        }

        card4.setOnClickListener {

            timer.cancel()
            timerValue = 10

            card2.isClickable = false
            card3.isClickable = false
            card4.isClickable = false
            card5.isClickable = false

            val nextBtn: LinearLayout = findViewById(R.id.next_btn_LinearLayout)
            nextBtn.visibility = View.VISIBLE

            when (ans) {
                optionA.text -> {
                    card2.setCardBackgroundColor(Color.parseColor("#61FA68")) //Green
                    card4.setCardBackgroundColor(Color.parseColor("#FD5044")) //Red
                }
                optionB.text -> {
                    card3.setCardBackgroundColor(Color.parseColor("#61FA68")) //Green
                    card4.setCardBackgroundColor(Color.parseColor("#FD5044")) //Red
                }
                optionC.text -> {
                    card4.setCardBackgroundColor(Color.parseColor("#61FA68")) //Green
                    correctCount++
                }
                optionD.text -> {
                    card5.setCardBackgroundColor(Color.parseColor("#61FA68")) //Green
                    card4.setCardBackgroundColor(Color.parseColor("#FD5044")) //Red
                }
            }

        }

        card5.setOnClickListener {

            timer.cancel()
            timerValue = 10

            card2.isClickable = false
            card3.isClickable = false
            card4.isClickable = false
            card5.isClickable = false

            val nextBtn: LinearLayout = findViewById(R.id.next_btn_LinearLayout)
            nextBtn.visibility = View.VISIBLE

            when (ans) {
                optionA.text -> {
                    card2.setCardBackgroundColor(Color.parseColor("#61FA68")) //Green
                    card5.setCardBackgroundColor(Color.parseColor("#FD5044")) //Red
                }
                optionB.text -> {
                    card3.setCardBackgroundColor(Color.parseColor("#61FA68")) //Green
                    card5.setCardBackgroundColor(Color.parseColor("#FD5044")) //Red
                }
                optionC.text -> {
                    card4.setCardBackgroundColor(Color.parseColor("#61FA68")) //Green
                    card5.setCardBackgroundColor(Color.parseColor("#FD5044")) //Red
                }
                optionD.text -> {
                    card5.setCardBackgroundColor(Color.parseColor("#61FA68")) //Green
                    correctCount++
                }
            }

        }

    }

    private fun finished(timer: CountDownTimer) {
        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra("count", count)
        intent.putExtra("correctCount", correctCount)
        startActivity(intent)
        finish()
        timer.cancel()
        timerValue = 10
    }

    private fun returnToMenu(timer: CountDownTimer) {
        val intent = Intent(this, CategoryActivity::class.java)
        startActivity(intent)
        finish()
        timer.cancel()
        count = 0
        correctCount = 0
        timerValue = 10
    }

}