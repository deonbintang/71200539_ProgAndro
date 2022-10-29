package com.catatankecilku

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.catatankecilku.MainActivity
import com.catatankecilku.databinding.ListDetailActivityBinding
import com.catatankecilku.TaskList

class ListDetailActivity: AppCompatActivity() {

    lateinit var list:TaskList

    lateinit var binding: ListDetailActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ListDetailActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        list = intent.getParcelableExtra(MainActivity.INTENT_LIST_KEY)!!

        title = list.name
    }

    override fun onBackPressed() {
        val intent= Intent()
        setResult(Activity.RESULT_OK, intent)
        super.onBackPressed()

    }
}
