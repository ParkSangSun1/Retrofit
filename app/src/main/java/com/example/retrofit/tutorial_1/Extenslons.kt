package com.example.retrofit.tutorial_1

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
//문자열이 제이슨 형태인지, 제이슨 배열 형태인지
fun String?.isJsonObject():Boolean {
    if (this?.startsWith("{")==true && this.endsWith("}")){
        return true
    }else{
        return false
    }
}

//문자열이 제이슨 배열인지
fun String?.isJsonArray():Boolean {
    if (this?.startsWith("[")==true && this.endsWith("]")){
        return true
    }else{
        return false
    }
}

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