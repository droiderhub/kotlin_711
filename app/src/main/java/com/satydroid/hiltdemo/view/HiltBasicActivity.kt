package com.satydroid.hiltdemo.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.satydroid.saty_7eleven.Logger
import com.satydroid.saty_7eleven.R
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.android.scopes.FragmentScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton

//todo add this where you want to inject dependency into
@AndroidEntryPoint
class HiltBasicActivity : AppCompatActivity() {

    //todo field injection
@Inject
lateinit var someClass: SomeClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hilt_basic)
        Logger.s("HiltBasicActivity")
        Logger.s("${someClass.doAThing()}")
        Logger.s("${someClass.doSomeOtherThing()}")
    }
}

@FragmentScoped
class MyFragment : Fragment(){

    @Inject
    lateinit var someClass: SomeClass

}

@ActivityScoped
class SomeClass
@Inject
constructor(
    private val someOtherClass: SomeOtherClass //todo constructor injection
    ,private val someInterface: SomeInterface
//    ,private val someInterfaceImpl: SomeInterfaceImpl //todo contructor injection of interface or 3rd party library class like gson, will give complie time error
){
    fun doAThing(): String{
        return "look,i did something"+someInterface.getAThing()
    }

    fun doSomeOtherThing() :  String{
        return someOtherClass.doSomeOtherThing()
    }
}






class SomeInterfaceImpl
@Inject
constructor(private val someDependency : String) : SomeInterface{
    override fun getAThing(): String {
        return "A thing"
    }
}

interface SomeInterface{
    fun getAThing() : String
}


/*//todo first option to use constructor injection of a interface
@InstallIn(SingletonComponent::class)
@Module
abstract class MyModule{

    @Singleton
    @Binds //or @provides
    abstract fun bindSomeDependency(
        someInterfaceImpl: SomeInterfaceImpl
    ) : SomeInterface
}*/

//todo second option to use constructor injection of a interface
@InstallIn(SingletonComponent::class)
@Module
class MyModule{

    @Singleton
    @Provides
    fun provideSomeString(): String{
        return "from my module"
    }

    @Singleton
    @Provides //or @binds
     fun provideSomeInterface(someString : String): SomeInterface{

         return SomeInterfaceImpl(someString)
     }
}



class SomeOtherClass
@Inject
constructor(){

    fun doSomeOtherThing() :  String{
        return "Look I did some other thing"
    }
}