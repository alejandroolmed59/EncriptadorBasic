package com.example.olmedo.encriptadorbasic

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_cifrado_cesar.*

class CifradoCesarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cifrado_cesar)

        btn_encrypt_cesar.setOnClickListener {
            val msgCrypted = encryptCesar(et_msg_cesar.text.toString(), 3)
            tv_resultado_cesar.text = msgCrypted
        }

        btn_share_cesar.setOnClickListener {
            val sendIntent = Intent().apply {
                val textMessage = "El mensaje encriptado es: " + tv_resultado_cesar.text.toString()
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, textMessage)
                type = "text/plain"
            }
            if (sendIntent.resolveActivity(packageManager) != null) {
                startActivity(sendIntent)
            }
        }

        btn_decrypt_cesar.setOnClickListener {
            val msgDecrypted = decryptCesar(et_msg_cesar.text.toString(), 3)
            tv_resultado_cesar.text = msgDecrypted
        }
    }

    private fun encryptCesar(msg: String, key: Int): String {
        val letrasAbecedarioMin = "abcdefghijklmnñopqrstuvwxyz"
        val letrasAbecedarioMay = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ"
        val charAbecedariosMin = letrasAbecedarioMin.toCharArray()
        val charAbecedariosMay = letrasAbecedarioMay.toCharArray()
        val letras = msg.toCharArray()
        var code = key % 27
        var msgCifrado = ""
        for (char in letras) {
            if ((char in 'a'..'z') || char == 'ñ') {
                msgCifrado += if (charAbecedariosMin.indexOf(char) + code > charAbecedariosMin.indexOf('z')) {
                    charAbecedariosMin[charAbecedariosMin.indexOf(char) + code - 27]
                } else {
                    charAbecedariosMin[charAbecedariosMin.indexOf(char) + code]
                }
            } else if ((char in 'A'..'Z') || char == 'Ñ') {
                msgCifrado += if (charAbecedariosMay.indexOf(char) + code > charAbecedariosMay.indexOf('Z')) {
                    charAbecedariosMay[charAbecedariosMay.indexOf(char) + code - 27]
                } else {
                    charAbecedariosMay[charAbecedariosMay.indexOf(char) + code]
                }
            } else {
                msgCifrado += char
            }
        }
        /*for ((i, char) in letras.withIndex()) {
            msgCifrado += if (charAbecedariosMin.contains(char)) {
                charAbecedariosMin[(charAbecedariosMin.indexOf(char) + key]
            } else {
                char
            }
        }*/
        return msgCifrado
    }

    private fun decryptCesar(msg: String, key: Int): String {
        val letrasAbecedarioMin = "abcdefghijklmnñopqrstuvwxyz"
        val letrasAbecedarioMay = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ"
        val charAbecedariosMin = letrasAbecedarioMin.toCharArray()
        val charAbecedariosMay = letrasAbecedarioMay.toCharArray()
        val letras = msg.toCharArray()
        var code = key % 27
        var msgDescifrado = ""
        for (char in letras) {
            if ((char in 'a'..'z') || char == 'ñ') {
                msgDescifrado += if (charAbecedariosMin.indexOf(char) - code < charAbecedariosMin.indexOf('a')) {
                    charAbecedariosMin[charAbecedariosMin.indexOf(char) - code + 27]
                } else {
                    charAbecedariosMin[charAbecedariosMin.indexOf(char) - code]
                }
            } else if ((char in 'A'..'Z') || char == 'Ñ') {
                msgDescifrado += if (charAbecedariosMay.indexOf(char) - code < charAbecedariosMay.indexOf('A')) {
                    charAbecedariosMay[charAbecedariosMay.indexOf(char) - code + 27]
                } else {
                    charAbecedariosMay[charAbecedariosMay.indexOf(char) - code]
                }
            } else {
                msgDescifrado += char
            }
        }
        return msgDescifrado
    }
}
