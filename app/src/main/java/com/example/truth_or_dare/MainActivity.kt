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
        val questionTextBlock: TextView = findViewById(R.id.question)
        val inputTextBlock: EditText = findViewById(R.id.text_input)
        val rotateArrowImage : ImageView = findViewById(R.id.spinnerImage)

        val questionSet: MutableSet<String> = mutableSetOf<String>(
            "測試問題一",
            "測試問題二"
        ) // 寫在這裡的會是app預設就有的問題

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

            rotateArrowImage.startAnimation(am)
            startDe gree = endDeg % 360

            val handler = Handler()    // 建立handler()
            handler.postDelayed( {     // 計時器
                // 出現提問
                var chosenQuestion = questionSet.random()
                while (chosenQuestion == questionTextBlock.text) {
                    chosenQuestion = questionSet.random()
                }
                questionTextBlock.setText(chosenQuestion)
            }, spinTime)
        }

        addQuestionButton.setOnClickListener {
            val stringInTextField = inputTextBlock.text.toString()
            if (stringInTextField.isNotEmpty()) {
                questionSet.add(stringInTextField)
                Toast.makeText(this, "成功新增問題", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "請先輸入內容", Toast.LENGTH_SHORT).show()
            }
            inputTextBlock.setText("")  // 清空文字輸入欄
        }
    }
}
