package app.carelite.scratch

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.TextView

class MainActivity : AppCompatActivity(), View.OnTouchListener{
    private lateinit var customBtn: CustomBtn
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        customBtn = findViewById(R.id.customBtn)
        val nextBtn = findViewById<TextView>(R.id.nextBtn)
        customBtn.setOnTouchListener(this)


        nextBtn.setOnClickListener{
            val intent = Intent(this, DotAnimationActivity::class.java)
            startActivity(intent)
        }
//        customBtn.setOnTouchListener { v, event ->
//            when(event?.action){
//                MotionEvent.ACTION_DOWN ->{
//                    customBtn.startAnimation(v)
//                    true
//                }
//                MotionEvent.ACTION_UP -> {
//                    customBtn.stopAnimation(v)
//                    true
//                }
//
//                else -> {false}
//            }
//        }
    }

    override fun onTouch(view: View, event: MotionEvent?): Boolean {
        when(event?.action){
            MotionEvent.ACTION_DOWN ->{
                customBtn.startAnimation(view)
            }
            MotionEvent.ACTION_UP -> {
                customBtn.stopAnimation(view)
            }
        }
        return true
    }
}