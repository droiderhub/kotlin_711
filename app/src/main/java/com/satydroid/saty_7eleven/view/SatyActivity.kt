package com.satydroid.saty_7eleven.view

import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import com.satydroid.saty_7eleven.R
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.satydroid.saty_7eleven.Logger
import com.satydroid.saty_7eleven.SatySingleton
import com.satydroid.saty_7eleven.showToast


class SatyActivity : AppCompatActivity() , SatyAdapter.OnItemClickListener{




    lateinit var name_tv : TextView
    var name_list :  ArrayList<String>  = ArrayList()
    lateinit var satyAdapter : SatyAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saty)
        initView()
    }

    private fun initView() {
        showToast("satyajit tarafdar")
        initRecyclerView()

        name_tv = findViewById(R.id.name_tv)
        val bundle = intent.extras

        val name = bundle!!.getString("my_name", "Default")
        name_tv.setText(name)
        name_tv.setOnClickListener {
            name_tv.setText(SatySingleton.name)
        }


    }
    private fun initRecyclerView() {

        name_list.add("saty")
        name_list.add("aditi")
        name_list.add("maa")
        name_list.toMutableList().add("babu")
        name_list.add("maa")
        Logger.s("name_list---"+name_list.toString())

        val listAr: ArrayList<String> = ArrayList()
        listAr.add("text")
        Logger.s("name_list---"+listAr.toString())

        val list = mutableListOf("saty","adtit","maa","babu","bla bla")
        list.add("phew")

        Logger.s("name_list---"+list.toString())
        Logger.s("name_list_get---"+list.get(2))

        var names_rv : RecyclerView
        names_rv = findViewById(R.id.names_rv)
        names_rv.apply {
            layoutManager = LinearLayoutManager(this@SatyActivity)
            val decoration = DividerItemDecoration(this@SatyActivity, DividerItemDecoration.VERTICAL)
            addItemDecoration(decoration)
            satyAdapter = SatyAdapter(this@SatyActivity, list as ArrayList<String>)
            adapter = satyAdapter

        }
    }

    override fun onItemEditCLick(name: String) {
        name_tv.text = name
    }
}