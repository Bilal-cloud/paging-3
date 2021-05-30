package com.example.paging3withmvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.paging3withmvvm.Adapter.LoadingStateAdapter
import com.example.paging3withmvvm.Adapter.PicsAdapter
import com.example.paging3withmvvm.ViewModel.MainViewModel
import com.example.paging3withmvvm.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var editText: EditText
    lateinit var btn: Button
    private lateinit var binding:ActivityMainBinding
    private val mainViewModel:MainViewModel by viewModels()
    @Inject
    lateinit var picsAdapter:PicsAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        editText=findViewById(R.id.edittext) as EditText

        btn=findViewById(R.id.getdata_btn) as Button

        btn.setOnClickListener(View.OnClickListener {
            var inputPage: Int = editText.text.toString().toInt()
            Toast.makeText(this@MainActivity, ""+inputPage, Toast.LENGTH_SHORT).show()


            LifeCycleScope(inputPage)

            initRecyclerview()


        })


    }

    private fun LifeCycleScope(input:Int) {
        lifecycleScope.launchWhenStarted {
            mainViewModel.getAllPics(input).collectLatest { response->
                binding.apply {
                    progressBar.isVisible=false
                    recyclerview.isVisible=true
                    mentionText.isVisible=false
                }
                picsAdapter.submitData(response)
            }
        }
    }

    private fun initRecyclerview() {
        binding.apply {
            recyclerview.apply {
                setHasFixedSize(true)
                layoutManager=StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
                adapter=picsAdapter.withLoadStateHeaderAndFooter(
                 header =LoadingStateAdapter{picsAdapter.retry()},
                    footer =LoadingStateAdapter{picsAdapter.retry()}
                )
            }
        }
    }
}