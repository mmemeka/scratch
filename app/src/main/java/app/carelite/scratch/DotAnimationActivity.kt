package app.carelite.scratch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.ImageView
import kotlin.math.cos
import kotlin.math.sin
import kotlin.random.Random

class DotAnimationActivity : AppCompatActivity() {

    private lateinit var bunny: ImageView
    private var x = 0f
    private var y = 0f
    private var angle = 0f
    private var speed = 0f
    private var speedX = 0f
    private var speedY = 0f
    val handler = Handler(Looper.getMainLooper())
    private var screenWidth = 0
    private var screenHeight = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dot_animation)
        bunny = findViewById(R.id.bunny)

        screenWidth = resources.displayMetrics.widthPixels
        screenHeight = resources.displayMetrics.heightPixels
        x = screenWidth/2f
        y = screenHeight/2f
        speed = Random.nextFloat() * 20
        speedX = speed * Random.nextFloat()
        speedY = speed * Random.nextFloat()
        handler.postDelayed(object : Runnable {
            override fun run() {
                x += speedX
                y += speedY
                checkIfBunnyTouchesTheEdges()
                bunny.x = x
                bunny.y = y
                Log.d("bunny", bunny.x.toString() + " "  +bunny.y.toString())
                handler.postDelayed(this, 16)
            }
        }, 100)
    }

    private fun checkIfBunnyTouchesTheEdges(){
        if(x + bunny.width >= screenWidth  || x <= 0 ){
            speedX = -speedX
//            changeDirection()
//            Log.d("bunny", "width")
        }

        if (y + bunny.height >= screenHeight || y <= 0 ){
            speedY = -speedY
//            changeDirection()
//            Log.d("bunny", "hei")
        }

    }

    private fun changeDirection(){
        angle = (Random.nextFloat() * 90) - 45
        val radianAngle = Math.toRadians(angle.toDouble())
        speedX = (speedX * cos(radianAngle) - speedY * sin(radianAngle)).toFloat()
        speedY = (speedX * sin(radianAngle) + speedY * cos(radianAngle)).toFloat()
//        speedX = speed * cos(radianAngle).toFloat()
//        speedY = speed * sin(radianAngle).toFloat()
    }
}