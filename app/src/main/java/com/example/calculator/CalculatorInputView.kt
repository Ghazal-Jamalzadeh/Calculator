package com.example.calculator

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import kotlinx.android.synthetic.main.view_calculator_input.view.*


class CalculatorInputView(context: Context, attributeSet: AttributeSet) :
    RelativeLayout(context, attributeSet) {

    init {
        // inflate layout
        LayoutInflater.from(context).inflate(R.layout.view_calculator_input, this, true)

        //Read attribute set
        // this attribute set can be null , so if a null is passed for the attribute set we need to check this one first
        //and this one here is checking is the attribute set is null
        // and this run is only called if the attribute set is not null
        // so if its null we just skip this one from here
        attributeSet?.run {
            val typedArray: TypedArray = context.obtainStyledAttributes(attributeSet, R.styleable.CalculatorInputView)
            val textRecourse: String? = typedArray.getString(R.styleable.CalculatorInputView_item_text)
            val iconRecouse = typedArray.getResourceId(R.styleable.CalculatorInputView_item_icon, -1)

            when {
                iconRecouse != -1 -> {
                    input_element_text_id.visibility = View.GONE
                    //that means that if the icon recourse is not null we are not displaying the text so we are hiding the text
                    input_element_image.apply {
                        // هر چیزی داخل این بلاک بنویسیم روی image اعمال میشود و لازم نیست مثل بالایی اول ای دی را بنویسیسم
                        visibility = View.VISIBLE
                        setImageResource(iconRecouse)
                    }
                }
                    !textRecourse.isNullOrEmpty() -> {
                        input_element_image.visibility = View.GONE
                        input_element_text_id.apply {
                            visibility = View.VISIBLE
                            text = textRecourse
                        }
                    }
                    else ->  {
                        input_element_image.visibility = View.GONE
                        input_element_text_id.visibility = View.GONE
                    }
                    }


//            if (iconRecouse != -1) {
//                input_element_text_1.visibility = View.GONE
//                //that means that if the icon recourse is not null we are not displaying the text so we are hiding the text
//                input_element_image.apply {
//                    // هر چیزی داخل این بلاک بنویسیم روی image اعمال میشود و لازم نیست مثل بالایی اول ای دی را بنویسیسم
//                    visibility = View.VISIBLE
//                    setImageResource(iconRecouse)
//                }
//            } else if (!textRecourse.isNullOrEmpty()) {
//                input_element_image.visibility = View.GONE
//                input_element_text_1.apply {
//                    visibility = View.VISIBLE
//                    text = textRecourse
//                }
//            } else {
//                input_element_image.visibility = View.GONE
//                input_element_text_1.visibility = View.GONE
//            }
            typedArray.recycle()
        }
    }

    override fun setOnClickListener(l: OnClickListener?) {
       // super.setOnClickListener(l) => delete this one
        input_element_click.setOnClickListener(l)
        // اگر کسی روی این Linera lyout کلیک کند، کلیک فقط روی همین ویو اعمال می شود
    }
}