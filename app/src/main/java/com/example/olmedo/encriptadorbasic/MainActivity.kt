package com.example.olmedo.encriptadorbasic

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Bt_go.setOnClickListener {
            val res = revertidor(Tv_msg.text.toString())
            resultado.text = res
        }
        Bt_Share.setOnClickListener {
            val sendIntent = Intent().apply {
                val textMessage = "El mensaje encriptado es: " + revertidor(Tv_msg.text.toString())
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, textMessage)
                type = "text/plain"
            }
            if (sendIntent.resolveActivity(packageManager) != null) {
                startActivity(sendIntent)
            }
        }
    }

    private fun revertidor(msg: String): String {
        val newMsg = msg.split(" ")
        //var aux = mutableListOf<String>()
        var newWord: String = ""
        for (word in newMsg) {
            //  aux.add(word.reversed())
            newWord = newWord + " " + word.reversed()
        }
        return newWord
    }
}
