package com.joanzapata.iconify.widget

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.joanzapata.iconify.Iconify
import com.joanzapata.iconify.internal.HasOnViewAttachListener
import com.joanzapata.iconify.internal.HasOnViewAttachListener.HasOnViewAttachListenerDelegate
import com.joanzapata.iconify.internal.HasOnViewAttachListener.OnViewAttachListener

class IconTextView : AppCompatTextView, HasOnViewAttachListener {
    private var delegate: HasOnViewAttachListenerDelegate? = null

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        init()
    }

    private fun init() {
        transformationMethod = null
    }

    override fun setText(text: CharSequence, type: BufferType) {
        super.setText(Iconify.compute(context, text, this), type)
    }

    override fun setOnViewAttachListener(listener: OnViewAttachListener?) {
        if (delegate == null) delegate = HasOnViewAttachListenerDelegate(this)
        delegate!!.setOnViewAttachListener(listener)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        delegate!!.onAttachedToWindow()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        delegate!!.onDetachedFromWindow()
    }
}