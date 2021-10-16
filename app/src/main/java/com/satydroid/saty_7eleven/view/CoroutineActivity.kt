package com.satydroid.saty_7eleven.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.satydroid.saty_7eleven.Logger
import com.satydroid.saty_7eleven.R
import kotlinx.coroutines.*
import kotlin.concurrent.thread
import kotlin.system.measureTimeMillis

class CoroutineActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine)
        //todo checking how thread works
//        checkThread()
        //todo how coroutine works
//        startCoroutine()
        //todo coroutines of launch
//        startLaunchOnMainThread()//running on main thread coz of runBlocking which blocks the main thread
//        startLaunch()
        //todo start async builder of coroutines
//        startAsync()
        //todo cancel a coroutine
//        cancelCoroutine()
        //todo withTimeOut coroutine builders
//        myWithTimeOut()
        //todo checking suspend fn more
//        mySuspendFunction()
        //note... coroutines execute always sequentially

        //todo coroutine scope, coroutine dispatcher and coroutinecontext
        finalCoroutine()
    }


    //***********************************************finalCoroutine *************************************
    private fun finalCoroutine() = runBlocking{
        /*couroutinescope
        *
        *
        * */
        Logger.s("name == $this") //BlockingCoroutine{Active}@6ca7adf
        launch {
            Logger.s("launch name === $this") //StandaloneCoroutine{Active}@d20732c

            launch {
                Logger.s("child launch name === $this") //StandaloneCoroutine{Active}@d38438
            }
        }

        async {
            Logger.s("async name === $this") //DeferredCoroutine{Active}@9f0a1f5
        }

        //so every coroutine have diff object with diff hash code

        /*coroutinecontext
        * can be inherit from child oor parent
        * it has two major component=== dispatcher and job
        *dispatcher === decides on which thread, croutine will execute
        * job == can be controll the cororutine
        *
        * */

        //coroutineContext using this can get coroutine context of the current coroutine
//        coroutineContext
        //without parameter : confined [CONFINED DISPATCHER]

        launch {
            //so this going to inheritate of the parent coroutine context
            Logger.s("checking c1 name in launch === ${Thread.currentThread().name}") //output is main thread
        }

        //with parameter : Dispatcher.Default  [similar to GlobalScope.launch{}]

        launch(Dispatchers.Default){//this is equivalent to Globalscope.launch which creates a separate thread
            Logger.s("checking c2 name in launch === ${Thread.currentThread().name}") //DefaultDispatcher-worker-2
            delay(1000)
            //after suspend fn , the below thread can change, but now it is same
            Logger.s("checking c3 name in launch === ${Thread.currentThread().name}") //DefaultDispatcher-worker-2
        }

        //with parameter : Dispacther.confined   [UNCONFINED DISPATCHER]

        launch(Dispatchers.Unconfined){
            //this also execute on main thread, as it is taking from the parent thread
            //which is runblocking
            Logger.s("checking c4 name in launch === ${Thread.currentThread().name}") //main
            delay(1000)
            //below is some other thread, as after a suspend function
            Logger.s("checking c5 name in launch === ${Thread.currentThread().name}") //kotlinx.coroutines.DefaultExecutor

            launch(coroutineContext){//taking from parent
                Logger.s("checking c6 name in launch === ${Thread.currentThread().name}") //other
                delay(1000)
                Logger.s("checking c7 name in launch === ${Thread.currentThread().name}")//other
            }

        }

        launch(coroutineContext){
            Logger.s("checking c6 name in launch === ${Thread.currentThread().name}") //main
            delay(1000)
            Logger.s("checking c7 name in launch === ${Thread.currentThread().name}")//main
        }
    }



    //***********************************************mySuspendFunction *************************************
    private fun mySuspendFunction() = runBlocking {
        /*todo sequential, concurrent, lazy
        *sequential== fn executing in sequentally
        *concurrent ==  can achieve using async
        *lazy = how to execute block of cose lazily using coroutines
        *
        * */

        Logger.s("main thread starts=== ${Thread.currentThread().name}")

        //todo sequential
        val time = measureTimeMillis { //to check how much taking time by the thread to execute task
            val msgOne = getMessageOne()
            val msgTwo = getMessageTwo()//these two func executing sequentially
            Logger.s("message are=== $msgOne & $msgTwo")
        }
        Logger.s("time taken=== $time")
        // todo concurrent== paralel== async
        /* val deferred :Deferred<String> =async {
             val time = measureTimeMillis { //to check how much taking time by the thread to execute task
                 val msgOne = getMessageOne()
                 val msgTwo = getMessageTwo()//these two func executing sequentially
                 Logger.s("message are=== $msgOne & $msgTwo")
             }
             "return value"
         }*/
        //or
        val timeAsync =
            measureTimeMillis { //to check how much taking time by the thread to execute task
                val msgOne: Deferred<String> = async { getMessageOne() }
                val msgTwo: Deferred<String> = async { getMessageTwo() }//these two func executing sequentially
                Logger.s("message are=== ${msgOne.await()} & ${msgTwo.onAwait}")
            }
        Logger.s("timeAsync taken=== $timeAsync")

//todo lazily execute coroutined

        val msgOne: Deferred<String> = async(start = CoroutineStart.LAZY) { getMessageOne() }
        val msgTwo: Deferred<String> = async (start = CoroutineStart.LAZY){ getMessageTwo() }


        //todo so if you define this as lazy and msgone , msgtwo not executed, the method inside the values also witll not execute
        Logger.s("message lazy are=== ${msgOne.await()} & ${msgTwo.onAwait}")
        Logger.s("main thread ends=== ${Thread.currentThread().name}")

    }

    suspend fun getMessageOne(): String {
        delay(1000) //pretend to do some work
        Logger.s("after working msg1")
        return "getMessageOne"
    }

    suspend fun getMessageTwo(): String {
        delay(1000) //pretend to do some work
        Logger.s("after working msg2")
        return "getMessageTwo"
    }


    //***********************************************coroutines builders*************************************

    /*todo diff builders of coroutines
    *  1.launch : alive till activity live
    * Globalscope.launch, is alive to entire life span of the app
    * Globalscope is basically a companion object, like static object, can use any where
    *
    * launch returns job object where as async returns deffered object
    * deffered is subclass of job
    * 2.async
    * deffered object return onject Unit, string, int
    * also we can use, GlobalScope.async
    *
    * 3.runBlocking
    * generally use to write test case to test suspend function
    *
    * 4.withTimeout and withTimeOutOrNull
    *
    * */
    private fun startLaunchOnMainThread() = runBlocking {//eg thread t1

        launch {//this will also t1
            delay(1000)
            Logger.s("launch thread starts=== ${Thread.currentThread().name}")// output is main thread , as launch willl take the parent which is runblocking
        }
    }


    private fun startLaunch() = runBlocking {

        Logger.s("main thread starts ====${Thread.currentThread().name}")
        val job: Job = launch {
            delay(1000)
            Logger.s("launch thread starts=== ${Thread.currentThread().name}")// output is main thread , as launch willl take the parent which is runblocking
        }
        job.join()// after completeing the bg thread , main thread will ends, otherwise both the thread runs parallaly
//        job.cancel()//to cancel the thread
        //job object allow to control the coroutines
        Logger.s("main thread ends=== ${Thread.currentThread().name}")
    }

    private fun startAsync() = runBlocking {

        Logger.s("main thread starts ====${Thread.currentThread().name}")
        val jobDeffered: Deferred<Int> = async {
            delay(1000)
            Logger.s("async thread starts===${Thread.currentThread().name}")
            15
        }
        val intOut: Int =
            jobDeffered.await()// after completeing the bg thread , main thread will ends, otherwise both the thread runs parallaly

        Logger.s("async_output=== $intOut")
//        job.cancel()//to cancel the thread
        //job object allow to control the coroutines
        Logger.s("main thread ends=== ${Thread.currentThread().name}")
    }

//***********************************************cancelling of coroutines*************************************

/*job.cancel() --if the coroutine is coopoerative,then cancel it
job.join()---- waits for the coroutine to finish
*job.cancelAndJoin()--- above both
two ways to make coroutines cooperative
1. use below suspend fn of coroutinse like=== delay(),yield(),withContext(),withTimeout()
*
*
*
* */

    private fun cancelCoroutine() = runBlocking {

        val jobDeferred: Job = launch(Dispatchers.Default) {

            try {


                var loopValue: Int = 0
                for (i in 1..100) {
//                   if (!isActive) //check coroutine alive or not
//                       break
                    Logger.s("cancelCoroutine running=== $i")
                    delay(1000)

                    loopValue = i
                }
                loopValue
            } catch (e: Exception) {
                //exception throws coz of Dispatchers passed in launch()
                Logger.s("couroutine exception=== ${e.message}")
            } finally {
                withContext(NonCancellable) {//this will create a fresh coroutine //NonCancellable companion object
                    delay(1000)
                    Logger.s("finally block executed")
                }
            }
        }

        delay(5000)//after this time, coroutine will cancel
//        jobDeferred.cancelAndJoin()
        jobDeferred.cancel(CancellationException("cancel_exception"))
        jobDeferred.join()
        Logger.s("main thread ends")

    }


    private fun myWithTimeOut() = runBlocking {
        withTimeout(5000) {//create a coroutine

            try {
                for (i in 1..100) {
                    Logger.s("cancelCoroutine running=== $i")
                    delay(1000)
                }
            } catch (e: CancellationException) {
                //code
            } finally {
                //code
            }
        }

        val result: String? =
            withTimeoutOrNull(5000) {//create a coroutine/ not throw any exception, try catch not required
                for (i in 1..100) {
                    Logger.s("cancelCoroutine running=== $i")
                    delay(1000)
                }
                "the_droider"
            }

        Logger.s("result===$result")
    }


    //***********************************************basics of coroutines*************************************
    //todo creates a suspend function
    suspend fun mySuspend(time: Long) {
        delay(time)
    }

    private fun startCoroutine() = runBlocking {//creates function,which creates a corouting
        Logger.s("main thread starts===${Thread.currentThread().name}")

        GlobalScope.launch {//creates a background coroutine====> sub coroutine
            Logger.s("thread start===${Thread.currentThread().name}")
//            Thread.sleep(1000)//doing some task
            delay(1000)//can only be called inside corourines,1st coroutine is suspended, next thread can be diff
            //delay is suspend function, so can not called outside coroutines

            mySuspend(1000)
            Logger.s("thread finish===${Thread.currentThread().name}")
        }

        //todo creating runblocking
        /* runBlocking {//blocks  the main thread, creates a diff coroutines

             delay(2000)//blocking for 2sec, then run on main thread
             Logger.s("thread_runblocking finish===${Thread.currentThread().name}")//output== is main thread
         }*/
        mySuspend(1000)

        Logger.s("main thread end===${Thread.currentThread().name}")
    }

    private fun checkThread() {
        Logger.s("main thread starts===${Thread.currentThread().name}")

        //some code
        thread {//creates a background thread
            Logger.s("thread start===${Thread.currentThread().name}")
            Thread.sleep(1000)//doing some task

            Logger.s("thread finish===${Thread.currentThread().name}")
        }

        thread {//creates a background thread
            Logger.s("thread start===${Thread.currentThread().name}")
            Thread.sleep(1000)//doing some task
            Logger.s("thread finish===${Thread.currentThread().name}")
        }

        Logger.s("main thread end===${Thread.currentThread().name}")

    }
}