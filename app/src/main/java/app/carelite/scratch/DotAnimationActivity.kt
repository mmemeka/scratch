package app.carelite.scratch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.ImageView
import kotlin.math.asin
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.random.Random

class DotAnimationActivity : AppCompatActivity() {

    private lateinit var bunny: ImageView
    private var x = 0f
    private var y = 0f
    private var angle = 0f
    private var speed = 1f
    private var speedX = 0f
    private var speedY = 0f
    val handler = Handler(Looper.getMainLooper())
    private var screenWidth = 0
    private var screenHeight = 0

    companion object {
        private const val STATE_RIGHT = 1
        private const val STATE_LEFT = 2
        private const val STATE_UP = 3
        private const val STATE_DOWN = 4

        private val speedRange = (1 .. 5)
    }

    val CurrentXStatesList = arrayOf(1, 2)
    var CurrentYStatesList =  arrayOf(3, 4)
    var CurrentXState = CurrentXStatesList.random()
    var CurrentYState = CurrentYStatesList.random()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dot_animation)
        bunny = findViewById(R.id.bunny)

        screenWidth = resources.displayMetrics.widthPixels
        screenHeight = resources.displayMetrics.heightPixels
        x = screenWidth/2f
        y = screenHeight/2f
//        speed = Random.nextFloat() * 20
        speedX = speed * (speedRange).random()
        speedY = speed * (speedRange).random()


        handler.postDelayed(object : Runnable {
            override fun run() {
                when(this@DotAnimationActivity.CurrentXState) {
                    STATE_RIGHT -> {
                        x += speedX
                        when(this@DotAnimationActivity.CurrentYState) {
                            STATE_DOWN -> {
                                angle = 135f
                            }
                            STATE_UP -> {
                                angle = 45f
                            }
                        }
                    }
                    STATE_LEFT -> {
                        x -= speedX
                        when(this@DotAnimationActivity.CurrentYState) {
                            STATE_DOWN -> {
                                angle = 225f
                            }
                            STATE_UP -> {
                                angle = 310f
                            }
                        }
                    }
                }
                when(this@DotAnimationActivity.CurrentYState) {
                    STATE_DOWN -> {
                        y += speedY
                    }
                    STATE_UP -> {
                        y -= speedY
                    }
                }
                checkIfBunnyTouchesTheEdges()
                bunny.x = x
                bunny.y = y
                bunny.rotation = angle
                Log.d("bunny", bunny.x.toString() + " "  +bunny.y.toString())

                handler.postDelayed(this, 16)
            }
        }, 1000)


    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null)
    }

    private fun checkIfBunnyTouchesTheEdges(){

        when(this@DotAnimationActivity.CurrentXState) {
            STATE_RIGHT -> {
                if (x >= screenWidth - bunny.width) {
                    this@DotAnimationActivity.CurrentXState = STATE_LEFT
                    speedX = speed * (speedRange).random()
                    //angle = atan2(y, x) * 180 / Math.PI.toFloat() /// angle in degrees
                }
            }

            STATE_LEFT -> {
                if (x <= 0) {
                    this@DotAnimationActivity.CurrentXState = STATE_RIGHT
                    speedX = speed * (speedRange).random()
                    //angle = atan2(y, -x) * 180 / Math.PI.toFloat() /// angle in degrees
                }
            }
        }

        when(this@DotAnimationActivity.CurrentYState) {
            STATE_DOWN -> {
                if (y >= screenHeight - bunny.height) {
                    this@DotAnimationActivity.CurrentYState = STATE_UP
                    speedY = speed * (speedRange).random()

                    //angle = atan2(-y, x) * 180 / Math.PI.toFloat() /// angle in degrees
                }
            }

            STATE_UP -> {
                if (y <= 0) {
                    this@DotAnimationActivity.CurrentYState = STATE_DOWN
                    speedY = speed * (speedRange).random()
                   // angle = atan2(y, x) * 180 / Math.PI.toFloat() /// angle in degrees
                }
            }
        }
    }
}