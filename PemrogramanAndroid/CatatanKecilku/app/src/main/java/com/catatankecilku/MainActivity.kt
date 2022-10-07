package com.catatankecilku

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View.inflate
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.resources.Compatibility.Api21Impl.inflate
import androidx.core.content.res.ColorStateListInflaterCompat.inflate
import androidx.core.content.res.ComplexColorCompat.inflate
import androidx.core.graphics.drawable.DrawableCompat.inflate
import androidx.preference.PreferenceManager.*
import com.catatankecilku.databinding.ActivityMainBinding.inflate
import com.catatankecilku.databinding.LayoutAddBinding
import com.catatankecilku.databinding.LayoutAddBinding.inflate

class MainActivity : AppCompatActivity(){

    private lateinit var binding : LayoutAddBinding
    private lateinit var viewModel: ListViewModel
    private val sharedpreferenceFile ="mySharedPref"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //binding = ListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = (ListViewModel())

        val adapter = ListSelectionRecycleViewAdapter(viewModel.lists)

        viewModel.onListAdded = {
            adapter.listsUpdate()
        }.toString()

        binding.tvCreate.setOnClickListener {
            viewModel.saveList(this)
        }

        val sharedPreferences: SharedPreferences = this.getSharedPreferences(sharedpreferenceFile,
            MODE_PRIVATE
        )
        val editor:SharedPreferences.Editor = sharedPreferences.edit()

    }
}
