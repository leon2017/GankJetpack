package com.wangjun.gankjetpack.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.support.annotation.ColorInt
import android.support.v7.widget.AppCompatTextView
import android.util.AttributeSet
import android.view.Gravity
import com.wangjun.gankjetpack.R


/**
 * 当前类注释：圆形textview</p>
 * Author: LeonWang </p>
 * Created: 2018/5/7 - 13:25 </p>
 * Desc: </P>
 * Copyright (C)  2018 lijiawangjun@gmail.com
 */
class CircleTextView : AppCompatTextView {

    private var circlePaint: Paint
    private var backPaint: Paint
    private var textPaint: Paint
    private var storkColor = Color.WHITE
    private var circleBackColor = Color.WHITE
    private var storkWidth: Float = 0.toFloat()

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        gravity = Gravity.CENTER
        circlePaint = Paint(Paint.ANTI_ALIAS_FLAG)
        circlePaint.style = Paint.Style.STROKE
        backPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        backPaint.style = Paint.Style.FILL
        textPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        storkWidth = 0f
        if (attrs != null) {
            val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleTextView)
            storkColor = typedArray.getColor(R.styleable.CircleTextView_storkColor, storkColor)
            circleBackColor = typedArray.getColor(R.styleable.CircleTextView_backColor, circleBackColor)
            storkWidth = typedArray.getDimension(R.styleable.CircleTextView_storkWidth, storkWidth)
            typedArray.recycle()
        }
        if (storkWidth != 0f) {
            circlePaint.strokeWidth = storkWidth
            circlePaint.color = storkColor
        }
        backPaint.color = circleBackColor
        textPaint.color = currentTextColor
        textPaint.textSize = textSize
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val height = height
        val width = width
        var radius: Int
        val storkRadius: Int
        val textWidth = textPaint.measureText(text.toString()).toInt()
        if (width > height) {
            if (height > textWidth) {
                radius = height
            } else {
                setHeight(textWidth + paddingTop + paddingBottom)
                radius = textWidth
            }
        } else {
            if (width > textWidth) {
                radius = width
            } else {
                setWidth(textWidth + paddingRight + paddingLeft)
                radius = textWidth
            }
        }
        storkRadius = (radius / 2 - storkWidth).toInt()
        radius = storkRadius - 1
        if (storkWidth != 0f)
            canvas.drawCircle((getWidth() / 2).toFloat(), (getHeight() / 2).toFloat(), storkRadius.toFloat(), circlePaint)
        canvas.drawCircle((getWidth() / 2).toFloat(), (getHeight() / 2).toFloat(), radius.toFloat(), backPaint)
        val fontMetrics = textPaint.fontMetrics
        canvas.drawText(text.toString(), getWidth() / 2 - textPaint.measureText(text.toString()) / 2, getHeight() / 2 - fontMetrics.descent + (fontMetrics.bottom - fontMetrics.top) / 2, textPaint)

    }

    fun setStorkColor(@ColorInt color: Int) {
        this.storkColor = color
        circlePaint.color = storkColor
        invalidate()
    }

    fun setBackColor(@ColorInt color: Int) {
        this.circleBackColor = color
        backPaint.color = circleBackColor
        invalidate()
    }
}