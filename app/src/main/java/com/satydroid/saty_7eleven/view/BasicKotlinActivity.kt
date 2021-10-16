package com.satydroid.saty_7eleven.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.satydroid.saty_7eleven.Logger
import com.satydroid.saty_7eleven.R

class BasicKotlinActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basic_kotlin)

        addTwoNumber(4, 5, object : MyInterface {
            //since kotlin doesnt have new key word, so object keyword help to define interface
            override fun execute(sum: Int) {
                Logger.s("sum value is === $sum")
            }

        })

        //so lambda expression is nothing but a fun without a name, where s acting as paramater
        val myLambda: (Int) -> Unit =
            { s: Int -> Logger.s("sum getting from lambda===$s") } //Logger.s(s) is body which returns void or Unit
        addTwoNumber(4, 5, myLambda)


        //todo how lambda works explanation
        addTwoNumber(4, 5, { s: Int -> Logger.s("sum getting from lambda===$s") })

        val twoParamLambda: (Int, Int) -> Int = { a, b -> a + b }
        var value: Int = addTwoParam(9, 4) { a, b -> a + b }


        //todo it explanation
//      val strOutPut =  reverseAndDisplay("satyajit", {s  -> s.reversed()})
        // replaced with it, only aplicable only with one paramter, implecit name of single parameter
        val strOutPut = reverseAndDisplay("satyajit", { it.reversed() })
        Logger.s("strOutPut===$strOutPut")

        //todo with and aply explalantin
        val person = Person(15, "saty")
        person.age = 30
        person.name = "satyajit"

        //replacing above innitilisation with "with" function

        with(person) {
            age = 30
            name = "satyajit"
        }

        //replacing above with "apply" function, and you can call the funs available of that class as well
        person.apply {
            age = 30
            name = "satyajit"
        }.startPerson()
        Logger.s("person class output=== ${person.name}====${person.age}")

        /*todo kotlin collection
        *  mutable == can perform both read and write operation
        * immutable == can perform only read operation
        *
        * immutable and mutable list
        * immuatable===>read only
        * listOf, mapOf, setOf
        * mutable ===> read and write
        * ArrayList, arrayListOf, mutableListOf
        * HashMap, hashMapOf, mutableMapOf
        * mutableSetOf, hashSetOf
        * */
        kotlinCollections()

        //todo combination of lambdas, high level fn and collection
        // /*
        // filter =  filter elements in collections
        //map == perform operation, and modify elements
        //Predicates or a condtions that returns true or false
        /*all = do all elements satisfy the predicts
        any = any element satisfy in list
        count = total elements that satisfies
        find =  retursn the first element that satisfies

        more funtion:
        flatmap = filter elements from collection of collection
        distinct = returns unique results

        */
        kotlinLambdasWithCollections()

    }

    private fun kotlinLambdasWithCollections() {
        var immutList : List<Int> = listOf(22,4,66,4,3,6,7,8)
        //find no which is less than 10
//        val mySmallNo = immutList.filter { n -> n<10 }
        val mySmallNo = immutList.filter {it <10 } //retuen predictive which is boolean

        for (numList in mySmallNo){
            Logger.s("no less than 10 are ${numList}")
        }

//        val mySquareNo = immutList.map { num -> num*num }
        val mySquareNo = immutList.map { it*it } //returns value
        for (numList in mySquareNo){
            Logger.s("mySquareNo are ${numList}")
        }
        val mySmallSquareno = immutList.filter { it<10 }.map { it * it }
        for (numList in mySmallSquareno){
            Logger.s("mySmallSquareno are ${numList}")
        }

       var personList= listOf<Person>(Person(10 , "saty"),Person(12 , "droider"),Person(15 , "satydroid"))

//        personList.map { p -> p.name }
       var namesList =  personList.map { it.name }
        for (numList in namesList){
            Logger.s("namesList are ${numList}")
        }

//        var namesListStartD =  personList.map { it.name }.filter { d -> d.startsWith("d") }
        var namesListStartD =  personList.map { it.name }.filter { it.startsWith("d") }

        for (numList in namesListStartD){
            Logger.s("namesListStartD are ${numList}")
        }

        //todo Predicates
        var predlist : List<Int> = listOf(22,4,66,4,3,6,7,8)

        val myPred = {num : Int -> num >10} //it>10 can be replace with myPred

        predlist.all { num -> num>10 }//find all the numbers which greater than 10


       val check1= predlist.all { it>10 }//find all the numbers which greater than 10, returns boolean

        Logger.s("check1===$check1")//false

        val check2 = predlist.any({it>10})
        Logger.s("check2===$check2")//true

        val check3count = predlist.count({it>10})
        Logger.s("check3count====$check3count")//2

        val check4num = predlist.find { it>10 }
        Logger.s("check4num===$check4num")//22
    }



    private fun kotlinCollections() {
//todo defining an array

//        val ar = Array (9) {0}//0 is the default values, 9 is fixed size
        var ar = arrayOf(9, 2, 4, 6)
//        val num = Array(3, {it*1})
//        val num = Array(3, {i-> i*1})

        ar[0] = 1
        ar[1] = 28
        ar[2] = 35
        ar.size

        for (i in 0..ar.size - 1) {
            Logger.s("printing array===${ar[i]}")
        }
        for (element in ar) {
            Logger.s("element are $element")
        }

//        todo defining list\
        val nameList = listOf<String>("saty", "droider", "satydroid")
//       todo nameList.add no add fn as it read operation immutable
        val nameListMut = mutableListOf<String>("saty", "droider")
        val nameListMut1 = arrayListOf<String>("saty", "droider")
        var nameListMut2 = ArrayList<String>()
        nameListMut.add("satydroid")
        for (element in nameList) {
            Logger.s("nameList are==== $element")
        }

        for (element in nameListMut) {
            Logger.s("nameListMut are==== $element")
        }

//        todo defining map

        var immuMap = mapOf<Int, String>(
            2 to "saty",
            4 to "droider",
            9 to "satydroid"
        )//todo immutable, fixed size, only readable

        for (mapVar in immuMap.keys){
            Logger.s("immutable map values are===$mapVar = ${immuMap[mapVar]}")//or immuMpap.get(key)
        }

        var  map = HashMap<Int, String>()//same mutable type map
        var  map1 = hashMapOf<Int, String>()//same mutable type map
        var mutMap = mutableMapOf<Int, String>(//this returns linked hash map
            2 to "saty",
            4 to "droider",
            9 to "satydroid"
        )//todo immutable, fixed size, only readable
        mutMap.put(6, "new")

        for (key in mutMap.keys){
            Logger.s("mutable map values are===$key = ${mutMap.get(key)}")
        }

        //todo defining set
        var immutSet = setOf<Int>(1,3,6,7) //immutable, fixed size, only readable

        //todo below mutable typ set, supports read and write operation
        var mutSet1 = HashSet<Int>()// the sequence are not guranteed in the output
        var mutSet2 = mutableSetOf<Int>()
        var mutSet3 = hashSetOf<Int>()


    }

    fun reverseAndDisplay(str: String, lambda: (String) -> String): String {

        return lambda(str)
    }

    fun addTwoParam(a: Int, b: Int, lambda: ((Int, Int) -> Int)): Int {
        return lambda(a, b)
    }

    //replacing this with lamdbda expression
    fun addTwoNumber(a: Int, b: Int, action: (Int) -> Unit) {
        var sum: Int

        sum = a + b
        action(sum)
    }

    //high level function
    fun addTwoNumber(a: Int, b: Int, action: MyInterface) {
        var sum: Int

        sum = a + b
        action.execute(sum)
    }
}

//higher order function


class Person {
    var name: String = ""
    var age: Int = 30

    constructor( age: Int,name: String) {
        this.name = name
        this.age = age
    }


    fun startPerson() {
        Logger.s("start person executed")
    }
}