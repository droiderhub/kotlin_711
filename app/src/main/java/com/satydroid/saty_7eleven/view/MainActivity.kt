package com.satydroid.saty_7eleven.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.satydroid.saty_7eleven.Logger
import com.satydroid.saty_7eleven.R
import com.satydroid.saty_7eleven.SatySingleton
import com.satydroid.saty_7eleven.viewmodel.SatyViewModel

class MainActivity : AppCompatActivity() {

    lateinit var tv: TextView
    lateinit var sum_btn: Button
    lateinit var basic_btn: Button
    lateinit var nullsafety_btn: Button
    lateinit var scope_fn_btn: Button
    lateinit var coroutines_btn: Button
    lateinit var rv_btn: Button
    var output: Int = 0
    lateinit var satyViewModel: SatyViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()

    }

    private fun initViews() {
        satyViewModel = ViewModelProvider(this).get(SatyViewModel::class.java)
        satyViewModel.getSummationObservable().observe(this, Observer<Boolean> {
            if (it == null) {
                Toast.makeText(this@MainActivity, "no result found...", Toast.LENGTH_LONG).show()
            } else if (it) {
                Toast.makeText(this@MainActivity, " result found...", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this@MainActivity, " result false...", Toast.LENGTH_LONG).show()
            }
        })
        tv = findViewById(R.id.tv)
        sum_btn = findViewById(R.id.sum_btn)
        basic_btn = findViewById(R.id.basic_btn)
        nullsafety_btn = findViewById(R.id.nullsafety_btn)
        scope_fn_btn = findViewById(R.id.scope_fn_btn)
        coroutines_btn = findViewById(R.id.coroutines_btn)
        rv_btn = findViewById(R.id.rv_btn)
        onClickButton()


    }

    private fun onClickButton() {
        sum_btn.setOnClickListener {
            output = satyViewModel.doSummationViewModel(4, 5)
            Logger.s(output)
            tv.setText("$output")
        }
        nullsafety_btn.setOnClickListener {
            val intent = Intent(this@MainActivity, NullSafetyActivity::class.java)
            this.startActivity(intent)
        }
        scope_fn_btn.setOnClickListener {
            val intent = Intent(this@MainActivity, ScopeFunctionActivity::class.java)
            this.startActivity(intent)
        }
        coroutines_btn.setOnClickListener {
            val intent = Intent(this@MainActivity, CoroutineActivity::class.java)
            this.startActivity(intent)
        }

        rv_btn.setOnClickListener {
            Toast.makeText(this, "moving...", Toast.LENGTH_LONG).show()
            SatySingleton.name = "tarafdar"

            val intent = Intent(this@MainActivity, SatyActivity::class.java)
            intent.putExtra("my_name", "satyajit")
            this.startActivity(intent)
        }
        basic_btn.setOnClickListener {
            val intent = Intent(this@MainActivity, BasicKotlinActivity::class.java)
            this.startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
    }

    companion object {
        private lateinit var intent: Intent
    }
}