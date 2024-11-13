package app.carelite.scratch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View

class MainActivity : AppCompatActivity(), View.OnTouchListener{
    private lateinit var customBtn: CustomBtn
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        customBtn = findViewById(R.id.customBtn)
        customBtn.setOnTouchListener(this)

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