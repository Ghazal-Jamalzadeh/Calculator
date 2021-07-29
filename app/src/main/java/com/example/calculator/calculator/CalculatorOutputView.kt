package com.example.calculator.calculator

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.example.calculator.R
import kotlinx.android.synthetic.main.view_calculator_output.view.*

class CalculatorOutputView(context: Context, attributeSet: AttributeSet) :
   LinearLayout(context, attributeSet) , CalculatorOutputInterfaceView {
    // we are calling init method. method here is automatically called when the view is created or instantiated
    init {
        //set orientation
        orientation= VERTICAL

        //set gravity
        gravity = Gravity.CENTER_VERTICAL

        //inflate layout :
        // True:
        LayoutInflater.from(context).inflate(R.layout.view_calculator_output, this, true)
        // False:
//    val view =LayoutInflater.from(context).inflate(R.layout.view_calculator_output,this,false)
//    addView(view)
        //# if its true instead of false , the view is automatically added to to this frame layout (to the parent)
    }
//**** this is for recognizing input here
    fun addItem (item: String){
        CalculatorOutputPresenter.add(item)
    }
    fun removeItem(){
        CalculatorOutputPresenter.remove()
    }
    fun solve(){
        CalculatorOutputPresenter.solve()

    }
    fun clear(){
        CalculatorOutputPresenter.clear()
    }
    //**** this is attaching the view to the presenter
    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        CalculatorOutputPresenter.attach(this)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        CalculatorOutputPresenter.detach()
    }
   //**** this is displaying out put
    override fun setEquation(equation: String) {
        calculator_input_equation.text = equation
    }

    override fun setOutcome(outcome: String) {
      calculator_input_outcome.text = outcome
    }
    // now we say if the presenter is setting the equation here is calling this set equation method then we are updating the actual equation text here
}