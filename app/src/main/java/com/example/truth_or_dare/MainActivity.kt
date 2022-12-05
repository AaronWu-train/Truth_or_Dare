package com.example.truth_or_dare

import android.os.Bundle
import android.view.animation.RotateAnimation
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Handler

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rollButton: Button = findViewById(R.id.start_button)
        val addQuestionButton: Button = findViewById(R.id.add_button)
        val questionText: TextView = findViewById(R.id.question)
        val inputText: EditText = findViewById(R.id.text_input)

        val questionSet: MutableSet<String> =
            mutableSetOf<String>("你喜歡誰？", "大叫：我喜歡OOO")

        var startDegree = 0.0f
        var endDeg = 0.0f

        rollButton.setOnClickListener {
            // 跳出提示訊息
            Toast.makeText(this, "開始旋轉！", Toast.LENGTH_SHORT).show()

            // 旋轉動畫
            var rotateDeg : Int = (1..360).random()
            rotateDeg += (3..6).random() * 360
            endDeg = startDegree + rotateDeg;
            val am = RotateAnimation(startDegree, endDeg,
                                    RotateAnimation.RELATIVE_TO_SELF, 0.5F,
                                    RotateAnimation.RELATIVE_TO_SELF, 0.5F)
            val spinTime : Long= (rotateDeg*3).toLong()
            am.duration = spinTime
            am.setFillAfter(true)
            val rotateArrow : ImageView = findViewById(R.id.imageView2)
            rotateArrow.startAnimation(am)
            startDegree = endDeg % 360

            val handler = Handler()
            handler.postDelayed(Runnable {     // 計時器
                // 出現提問
                var chosenQuestion = questionSet.random()
                while (chosenQuestion == questionText.text) {
                    chosenQuestion = questionSet.random()
                }
                questionText.setText(chosenQuestion)
            }, spinTime)
        }

        addQuestionButton.setOnClickListener {
            val stringInTextField = inputText.text.toString()
            if (stringInTextField.isNotEmpty()) {
                questionSet.add(stringInTextField)
            } else {
                Toast.makeText(this, "請先輸入內容", Toast.LENGTH_SHORT).show()
            }
            inputText.setText("")
        }
    }

}
