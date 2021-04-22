package com.example.retrofit.tutorial_1

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

//에딧 텍스트에 대한 익스텐션
fun EditText.onMyTextChanged(completion : (Editable?) -> Unit){
    this.addTextChangedListener(object : TextWatcher {
            //글자가 바뀌기 전
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            //글자가 변경될때
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            //글자가 바뀐뒤
            override fun afterTextChanged(editable: Editable?) {
                completion(editable)
            }

        })
}