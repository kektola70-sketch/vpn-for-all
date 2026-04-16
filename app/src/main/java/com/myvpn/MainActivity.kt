package com.myvpn

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnConnect = findViewById<Button>(R.id.btnConnect)
        btnConnect.setOnClickListener {
            // Тестовое сообщение
            Toast.makeText(this, "Кнопка нажата! VPN еще не работает, но приложение живое.", Toast.LENGTH_LONG).show()
        }
    }
}
