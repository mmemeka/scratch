package app.carelite.scratch

import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.View

class CustomBtn: androidx.appcompat.widget.AppCompatTextView {
    private var animator : ValueAnimator? = null

    constructor(c: Context, attributeSet: AttributeSet) : super(c, attributeSet){
        this.setBackgroundColor(R.color.blue)
        this.text = "Press"
    }

    fun startAnimation(view: View){
        animator = ValueAnimator.ofFloat(100f, 250f).apply {
            duration = 1000
            repeatMode = ValueAnimator.RESTART
            repeatCount = ValueAnimator.INFINITE
            addUpdateListener { animation ->
                val value = animation.animatedValue as Float
                val layoutParams = view.layoutParams
                layoutParams.width = value.toInt()
                layoutParams.height = value.toInt()
                view.layoutParams = layoutParams
            }
            start()
        }
    }

    fun stopAnimation(view: View){
        animator?.cancel()
        animator = null
        val layoutParams = view.layoutParams
        layoutParams.width = 100
        layoutParams.height = 100
        view.layoutParams = layoutParams
    }
}