package com.example.calculator.calculator

import bsh.Interpreter
import java.lang.Exception

// this is not a class this is an object
//whats the different between a class and an object ?
//you can instantiate multiple objects or multiple instances of a class
//but an object is a singleton that means that you only can have onr of these objects
object CalculatorOutputPresenter {
    //current attached view
    private var mView: CalculatorOutputInterfaceView? = null

    //current equation
    private var mCurrentEquation: String = ""

    //current outcome
    private var mCurrentOutcome: String = ""

    //interpreter : class that we a added to gradle file
    private val mInterpreter = Interpreter()

    fun attach(view: CalculatorOutputInterfaceView) {
        mView = view
        updateEquation()
        updateOutcome()
    }

    fun detach() {
        mView = null
    }

    // چون که ویو همه چیز را به پرزنتر پاس می‌دهد و خودش هیچ لاجیکی انجام نمی دهد NO BRAIN!!!! پس ما همه چیز را به این ور پاس می دهیم
    fun add(item: String) {
        mCurrentEquation = mCurrentEquation.plus(item)
        updateEquation()
        calculateOutcome()
        updateOutcome()
    }

    fun remove() {
        //   mCurrentEquation = mCurrentOutcome.substring(0, mCurrentEquation.length - 1)
        // substring means that we create a new string from the equation and we started position 0 from the first letter and go until the length
        //-1 : means we are removing the last one for example ife we say -4 instead than the last four characters would be removed
        //حالا یه مشکل داریم چون منفی یک را نوشتیم اگه مثلا فقط یک عدد یک داشته باشیم ساب استرینگ کرش می کند از ۰ تا ۰ (length= 1 -1 = 0)برای حل این موضوع این شرط را مینویسم
//     //روش اول نوشتن شرط if
//       if(mCurrentEquation.length>1){
//           mCurrentOutcome.substring(0, mCurrentEquation.length - 1)
//       }else{
//           mCurrentEquation=""
//       }
        //روش دوم نوشتن شرط if
        mCurrentEquation = if (mCurrentEquation.length > 1) {
            mCurrentOutcome.substring(0, mCurrentEquation.length - 1)
            // مهم نیست چندین خط داخل این براکت بنویسسم خط آخر هر چه باشد در mCurrentEquation ریخته می شود
        } else {
            ""
        }
        updateEquation()
        calculateOutcome()
        updateOutcome()
    }

    fun solve() {
        if (mCurrentOutcome.isNotEmpty()) {
            mCurrentEquation = mCurrentOutcome
            mCurrentOutcome = ""
            //یعنی وقتی رو مساوی می زنیم مجموع را ببرد بالا نشان دهد
        }
        updateEquation()
        updateOutcome()
    }

    fun clear() {
        mCurrentEquation = ""
        mCurrentOutcome = ""
        updateEquation()
        updateOutcome()
    }

    private fun calculateOutcome() {
        if (mCurrentEquation.isNotEmpty()) {
            try {

                mInterpreter.eval("result= $mCurrentEquation")
                val result: Any = mInterpreter.get("result")

                if (result != null && result is Int) {
                    mCurrentOutcome = result.toString()// "error"
                }
            } catch (e : Exception){

                mCurrentOutcome = mCurrentEquation
                // برای این است که اگر چیزی مثل 1+ وارد شده بود برنلمه کرش نکند. چون در عین حال که null نیست معنایی هم ندارد
            }
        } else {
            mCurrentOutcome = ""
        }
    }

    private fun updateEquation() {
        mView?.setEquation(mCurrentEquation)
    }

    private fun updateOutcome() {
        mView?.setOutcome(mCurrentOutcome)
    }
}

// val is final and cant never be changed again