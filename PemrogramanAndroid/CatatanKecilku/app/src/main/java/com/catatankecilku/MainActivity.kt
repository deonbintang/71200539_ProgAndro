package com.catatankecilku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.widget.EditText
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import com.catatankecilku.databinding.ActivityMainBinding
import com.catatankecilku.TaskList
import com.catatankecilku.ListDetailActivity
import com.catatankecilku.MainFragment
import com.catatankecilku.MainViewModel
import com.catatankecilku.MainViewModelFactory

class MainActivity : AppCompatActivity(), MainFragment.MainFragmentInteractionListener {

    private lateinit var binding: ActivityMainBinding

    private lateinit var viewModel: MainViewModel

    private lateinit var activityDetailResultLauncher: ActivityResultLauncher<Intent>

    companion object {
        const val INTENT_LIST_KEY = "list"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(
            this,
            MainViewModelFactory(
                PreferenceManager.getDefaultSharedPreferences(this)
            )
        )
            .get(MainViewModel::class.java)

        if (savedInstanceState == null) {
            //attach fragment to fragment container
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_container, MainFragment.newInstance(this))
                .commitNow()
        }

        binding.fabButton.setOnClickListener {
            showCreateListDialog()
        }

        activityDetailResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if(result.resultCode == RESULT_OK){
                    result.data.let {

                    }
                }
            }
    }

    private fun showCreateListDialog() {
        val dialogTitle = getString(R.string.name_of_list)
        val positiveButtonTitle = getString(R.string.create_list)

        val builder = AlertDialog.Builder(this)
        val listTitleEditText = EditText(this)
        listTitleEditText.inputType = InputType.TYPE_CLASS_TEXT

        builder.setTitle(dialogTitle)
        builder.setView(listTitleEditText)
        builder.setPositiveButton(positiveButtonTitle) { dialog, _ ->
            dialog.dismiss()
            viewModel.saveList(TaskList(listTitleEditText.text.toString()))
        }

        builder.create().show()
    }

    override fun listItemTapped(list: TaskList) {
        showListDetail(list)
    }

    private fun showListDetail(list: TaskList) {
        val listDetailIntent = Intent(this, ListDetailActivity::class.java)
        listDetailIntent.putExtra(INTENT_LIST_KEY, list)
        Log.d("showListDetail", "showListDetail: " + list.name)
        activityDetailResultLauncher.launch(listDetailIntent)
    }
}
