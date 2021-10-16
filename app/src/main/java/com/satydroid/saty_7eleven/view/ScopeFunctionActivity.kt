package com.satydroid.saty_7eleven.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.satydroid.saty_7eleven.Logger
import com.satydroid.saty_7eleven.R

class ScopeFunctionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scope_function)

        //let, run, with, apply , also
        val people = People()
        people.id = 1
        people.name = "satyajit"

        //todo with === returns the last line value
      val id1:Int=  with(people){
            id = 1
            name = "tarafdar"
            Logger.s("people === $name and $id")
          id+4
        }
        Logger.s("after id value === $id1")
        Logger.s("people == ${people.name} and ${people.id}")

        //todo apply = can call the fun of thet class, and returns the context object of that class
       var people1 = people.apply {
         id = 6
         name = "satydroid"

        }.getDetails()

        //todo also ==
        val numberList : MutableList<Int> = mutableListOf(1,2,3)

        //want to perform some operation on nunberlist,...say swape numbers=== we can use also fn

        numberList.add(4)
        Logger.s("numberlist are  $numberList ")
        numberList.add(5)
        Logger.s("numberlist are  $numberList ")

        //so the above can replace with == also
        val duplucateNumber =numberList.also {
            it.add(4)
            Logger.s("numberlist are  $it ")
            it.add(5)
            Logger.s("numberlist are  $it ")
            it.remove(3)
        }
        Logger.s("numberList=== $numberList")
        Logger.s("duplucateNumber === $duplucateNumber")//both results are same

        val duplicatePerson = people.also {
            it.id = 10
            it.name ="droider"
        }
        Logger.s("people=== $people")
        Logger.s("duplicatePerson === $duplicatePerson")// both are same

        //todo let fun=== returns lambda result
            //generally use to avoid nullpointer exception
        //returns the last line value

        var namesaty : String ?=null
/*

        Logger.s(name!!.length) // will trow null pointer , app crashed, as name is not assigned
        Logger.s(name.reversed())
        Logger.s(name.capitalize())
*/

        namesaty = "satyajit"
        //to avoid null pointer exception, use let
        val chi : Int? =namesaty?.let {//if name is null, will not enter to the lambda block
            Logger.s(namesaty!!.length)
            Logger.s(namesaty!!.reversed())
            Logger.s(namesaty!!.capitalize())
            namesaty!!.length
        }
        Logger.s("name length is $chi")

        //todo run === when the people object is defining null initially, can use run
        //returns lambda result
        //combination of with and let fn

//        var people2 : People? = null
        var people2 : People? = People()

       val bio = people2?.run {
            Logger.s(name)
            Logger.s(id)
           id+9
           name + "dsadas"
        }
        Logger.s("bio=== $bio")
    }
}

class People{
    var name : String = ""
    var id : Int =  -1

    fun getDetails(){
        Logger.s("getting people details")
    }
}