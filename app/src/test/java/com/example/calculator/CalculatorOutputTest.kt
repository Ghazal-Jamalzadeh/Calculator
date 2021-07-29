package com.example.calculator

import com.example.calculator.calculator.CalculatorOutputInterfaceView
import com.example.calculator.calculator.CalculatorOutputPresenter
import org.junit.Test
import org.mockito.BDDMockito.then
import org.mockito.Mockito

class CalculatorOutputTest{
    private  val mPresenter : CalculatorOutputPresenter = CalculatorOutputPresenter
    private  val mockView : CalculatorOutputInterfaceView = Mockito.mock(CalculatorOutputInterfaceView::class.java)

    @Test
    //search markdown highlight code... for this format
    // مثلا خورشید زرد است / اگر ابر بیاد جلوش / باید قرمز شه
    fun `1 plus 1` (){
        //given that the view is attached : مراحل تست
        mPresenter.attach(mockView)
        // when a number is added
        mPresenter.add("1")
        // then the correct equation should be set
      //  then(mockView).should().setEquation("1")
        //when a operator is added
        mPresenter.add("+")
        //then the correct equation should be set
     //   then(mockView).should().setEquation("1+")
        //when a operator is added
        mPresenter.add("1")
        //then the correct equation should be set
        then(mockView).should().setEquation("1+1")
        //Then the correct outcome should be set
        then(mockView).should().setOutcome("result.toString()")
        //clear presenter
        mPresenter.clear()
    }
    @Test
    fun `2 plus 2 minus 1 is 3`() {
        //given that the view is attached : مراحل تست
        mPresenter.attach(mockView)
        // when a number is added
        mPresenter.add("2")
        // then the correct equation should be set
        then(mockView).should().setEquation("2")
        //when a operator is added
        mPresenter.add("+")
        //then the correct equation should be set
        then(mockView).should().setEquation("2+")
        //when a operator is added
        mPresenter.add("2")
        //then the correct equation should be set
        then(mockView).should().setEquation("2+2")
        //Then the correct outcome should be set
        then(mockView).should().setOutcome("4")
        //when a operator is added
        mPresenter.add("-")
        //then the correct equation should be set
        then(mockView).should().setEquation("2+2-")
        //when a operator is added
        mPresenter.add("1")
        //then the correct equation should be set
   //     then(mockView).should().setEquation("2+2-1")
        //Then the correct outcome should be set
    //    then(mockView).should().setOutcome("3")

    }
}