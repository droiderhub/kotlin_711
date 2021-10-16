package com.satydroid.saty_7eleven.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.satydroid.saty_7eleven.Logger
import com.satydroid.saty_7eleven.R

class NullSafetyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_null_safety)

        /*todo null safety operator ares:
        *
        * ?. == safe call opearator
        * ?: == elvis operaotr
        * !! ==  not null assertion
        * ?.let{..} == safe call with let operator
        *
        * */
        nullPointer()

        /*todo letinit
        *
        *
        * */

        letInit()
        lazyInit()

    }



    private fun lazyInit() {
        Logger.s("lazyInit()")

        val pi :Float=3.14f //if pi is not in use, the wasting memory, thats y lazyinit comes

        val pil :Float by lazy { 3.14f } //unless you use it, pil value wont be initialise to 3.14
        //lazy is sthread safe, if lazy is using by other thread, the value will be initialise from catch memory

        val area1 = pi*4 //pi initialise 1st time
        val area2 = pi*4 *4 //pi wont initialise
        Logger.s("area2=== $area2")
    }

    private fun letInit() {
        Logger.s("letInit()")
        //lateinit work only with mutable data type
        //it is use only in non nullable data tyype
        //lateinint must be initialise before you use

        var country = Country()
        country.apply {
            name = "India"
        }
        Logger.s("country name is=== ${country.name}")
    }


    private fun nullPointer() {
        Logger.s("nullPointer()")
        //todo safeCall
        //?.
        /*returns the length if the length is not null
        *use it if you dont mind getting null value
        * */

        val name: String? = null//assigning null value
        Logger.s("value is === ${name?.length}")

        //todo safe call with let
        //?.let{}
//        it executes the block only if the value is not null
        name?.let {
            Logger.s("value of name is === ${name.length}")
        }


        //todo elvis operator
        //?:
        //when you havenullable reference
        //otherwise use some non null value
        val len = if (name != null)
            name.length
        else
            -1
        //replacing above with elvis operator
        val length = name?.length ?: -1
        Logger.s("lenght of the name is=== $length")

        //todo non null assertion operator
        //!!
        //use it, when you are sure the value is not null
        //throws nullpointer exception, if value is null
        try {
            Logger.s("value of length is=== ${name!!.length}") //here throws exception

        }catch (ex : NullPointerException){
            Logger.s("exception")
        }

    }
}

class Country{

    lateinit var name : String//initialise it later so lateinit
}