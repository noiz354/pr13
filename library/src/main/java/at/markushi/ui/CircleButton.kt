package at.markushi.ui

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.TypedValue
import android.widget.ImageView
import at.markushi.circlebutton.R

/**
 * Created by normansyahputa on 9/28/17.
 */
class CircleButton @JvmOverloads constructor(context: Context, attributeSet: AttributeSet?, defStyle: Int = 0) : ImageView(context, attributeSet, defStyle) {
    val PRESSED_COLOR_LIGHTUP = 255/25
    val PRESSED_RING_ALPHA = 75
    val DEFAULT_PRESSED_RING_WIDTH_DIP = 4f
    val ANIMATION_TIME_ID = android.R.integer.config_shortAnimTime

    lateinit var circlePaint:Paint
    lateinit var focusPaint:Paint
    var pressedRingWidth:Float = -1f
    private var defaultColor: Int = 0
    var pressedColor = PRESSED_COLOR_LIGHTUP
    var animationProgress = 0f
        set(value) = invalidate()

    private lateinit var pressedAnimator: ObjectAnimator

    fun initUI(context: Context, attributeSet: AttributeSet?){
        isFocusable = true
        scaleType = ScaleType.CENTER_INSIDE
        isClickable = true

        circlePaint = Paint(Paint.ANTI_ALIAS_FLAG)
        circlePaint.style = Paint.Style.FILL

        focusPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        focusPaint.style = Paint.Style.STROKE

        pressedRingWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, DEFAULT_PRESSED_RING_WIDTH_DIP, resources.displayMetrics)

        var color:Int = Color.BLACK
        attributeSet?.let {
            val a = context.obtainStyledAttributes(it, R.styleable.CircleButton)
            color = a.getColor(R.styleable.CircleButton_cb_color, Color.BLACK)
            pressedRingWidth = a.getDimension(R.styleable.CircleButton_cb_pressedRingWidth, pressedRingWidth)
            a.recycle()
        }

        setColor(color)

        focusPaint.strokeWidth = pressedRingWidth
        val pressedAnimationTime = resources.getInteger(ANIMATION_TIME_ID).toLong()
        pressedAnimator = ObjectAnimator.ofFloat(this, "animationProgress", 0f, 0f)
        pressedAnimator.duration = pressedAnimationTime
    }


    fun setColor(color:Int){
        defaultColor = color
        pressedColor = colorHighlight(color, PRESSED_COLOR_LIGHTUP);

        circlePaint.color = defaultColor
        focusPaint.color = defaultColor
        focusPaint.alpha = PRESSED_RING_ALPHA

        invalidate()
    }

    fun colorHighlight(color:Int, amount:Int) :Int
         = Color.argb(Math.min(255, Color.alpha(color)), Math.min(255, Color.red(color) + amount),
                Math.min(255, Color.green(color) + amount), Math.min(255, Color.blue(color) + amount))


    init {
        initUI(context, attributeSet)
    }

    override fun setPressed(pressed: Boolean) {
        super.setPressed(pressed)
        if(pressed) {
            circlePaint.color = pressedColor
            showPressedRing()
        }else {
            circlePaint.color = defaultColor
            hidePressedRing()
        }
    }

    private fun hidePressedRing() {
        pressedAnimator.setFloatValues(animationProgress, pressedRingWidth)
        pressedAnimator.start()
    }

    private fun showPressedRing() {
        pressedAnimator.setFloatValues(pressedRingWidth, 0f)
        pressedAnimator.start()
    }

    private var centerX = 0f

    private var centerY = 0f

    private var pressedRingRadius = 0f

    private var outerRadius = 0f

    override fun onDraw(canvas: Canvas?) {
        canvas?.drawCircle(centerX, centerY, pressedRingRadius + animationProgress, focusPaint)
        canvas?.drawCircle(centerX, centerY, (outerRadius - pressedRingWidth), circlePaint)
        super.onDraw(canvas)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        centerX = w / 2f
        centerY = h / 2f
        outerRadius = Math.min(w,h)/2f
        pressedRingRadius = outerRadius - pressedRingWidth - pressedRingWidth / 2

    }

}