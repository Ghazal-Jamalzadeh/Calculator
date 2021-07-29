package com.example.calculator.calculator

interface CalculatorOutputInterfaceView {
    //the interface between view and presenter
    fun setEquation (equation : String )
    fun setOutcome (outcome : String)
}
// this is the interface which the presenter is calling And then the presenter can set some stuff to the view