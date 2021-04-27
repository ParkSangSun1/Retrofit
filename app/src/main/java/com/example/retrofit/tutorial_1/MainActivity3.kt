package com.example.retrofit.tutorial_1

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import com.example.retrofit.R
import com.example.retrofit.databinding.ActivityMain3Binding
import com.example.retrofit.databinding.LayoutButtonSearchBinding
import com.example.retrofit.tutorial_1.retrofit.RetrofitManager

class MainActivity3 : AppCompatActivity() {
    lateinit var binding : ActivityMain3Binding
    private var currentSearchType: SEARCH_TYPE = SEARCH_TYPE.PHOTO
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main3)

        Log.d(Constants.TAG,"MainActivity - onCreate() called")

        //라디오 그룹 가져오기
        //코틀린에서 사용하지 않는 매개변수는 _ 로 표현
        binding.searchTermRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            when(checkedId){
                R.id.photo_search_radio_btn -> {
                    Log.d(Constants.TAG,"사진검색 버튼 클릭!")
                    binding.searchTermTextLayout.hint = "사진검색"
                    binding.searchTermTextLayout.startIconDrawable = resources.getDrawable(R.drawable.ic_baseline_insert_photo_24, resources.newTheme())
                    this.currentSearchType = SEARCH_TYPE.PHOTO
                }

                R.id.user_search_radio_btn ->{
                    Log.d(Constants.TAG,"사용자검색 버튼 클릭!")
                    binding.searchTermTextLayout.hint = "사용자검색"
                    binding.searchTermTextLayout.startIconDrawable = resources.getDrawable(R.drawable.ic_baseline_supervised_user_circle_24, resources.newTheme())
                    this.currentSearchType = SEARCH_TYPE.USER
                }
            }
        }

        //검색 버튼이 글자가 있으면 보이게 없으면 안보이게
        binding.searchTermEditText.onMyTextChanged {
            //입력된 글자가 하나라도 있다면
            if(it.toString().count()>0){
                //검색버튼을 보여준다
                binding.btnSearch.visibility = View.VISIBLE
                binding.searchTermTextLayout.helperText = " "
                binding.mainScrollview.scrollTo(0,200)
            }else{
                binding.btnSearch.visibility = View.INVISIBLE
                binding.searchTermTextLayout.helperText = "검색어를 입력하세요"

            }
            if(it.toString().count() == 12){
                Toast.makeText(this,"검색어는 12자 까지만 입력 가능합니다",Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnSearch.setOnClickListener {
            this.handleSearchButtonUi()

            //검색 api 호출
            RetrofitManager.instance.searchPhotos(searchTerm = binding.searchTermEditText.toString(), completion ={
                responseState, responseBody ->
                when(responseState){
                    RESPONSE_STATE.OKAY->{
                        Log.i(Constants.TAG,"api 호출성공 : $responseBody")
                    }
                    RESPONSE_STATE.FAIL->{
                        Toast.makeText(this,"api호출 에러",Toast.LENGTH_SHORT).show()
                        Log.i(Constants.TAG,"api 호출실패 : $responseBody")
                    }
                }
            } )
        }
    }

    private fun handleSearchButtonUi(){
        binding.btnProgress.visibility = View.VISIBLE
        binding.btnSearch.text = ""
        Handler().postDelayed({
            binding.btnProgress.visibility = View.INVISIBLE
            binding.btnSearch.text = "검색"
        }, 1500)
    }

}