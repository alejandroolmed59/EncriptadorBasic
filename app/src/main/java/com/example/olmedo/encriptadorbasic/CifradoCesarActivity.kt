package com.example.olmedo.encriptadorbasic

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_cifrado_cesar.*
import kotlinx.android.synthetic.main.activity_main.*

class CifradoCesarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cifrado_cesar)

        btn_encrypt_cesar.setOnClickListener {
            val msgCrypted = encryptCesar(et_msg_cesar.text.toString(), 3,1)
            tv_resultado_cesar.text = msgCrypted
        }

        btn_share_cesar.setOnClickListener {
            val sendIntent = Intent().apply {
                val textMessage = "El mensaje encriptado es: " + "EL RESULTADO"
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, textMessage)
                type = "text/plain"
            }
            if (sendIntent.resolveActivity(packageManager) != null) {
                startActivity(sendIntent)
            }
        }

        bbtn_decrypt_cesar.setOnClickListener {
            Toast.makeText(this, "Aun no lo implemento lol", Toast.LENGTH_LONG).show()
            /*val msgDecrypted = encryptCesar(tv_resultado_cesar.toString(), 3,-1)
            tv_resultado_cesar.text = msgDecrypted*/
        }
    }

    private fun encryptCesar(msg: String, key: Int,sign:Int): String {
        val letrasAbecedario = "abcdefghijklmnñopqrstuvwxyz"
        val charAbecedarios = letrasAbecedario.toCharArray()
        var msgCifrado = ""
        //Posiciones a adelantar
        //Convertimos el mensaje en un array de caracteres
        val letras = msg.toCharArray()
        for ((i, char) in letras.withIndex()) {
            msgCifrado += if (charAbecedarios.contains(char)) {
                charAbecedarios[((((charAbecedarios.indexOf(char)) + (key*sign)) % 27))]
            } else {
                char
            }
        }
        return msgCifrado
    }
}
