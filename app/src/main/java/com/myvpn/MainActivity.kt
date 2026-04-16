package com.myvpn

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import java.io.FileOutputStream

class MainActivity : AppCompatActivity() {

    private val vlessLink = "vless://41e6f9a9-1304-4b93-ac14-9b8822831048@fr-d.duckray.co.uk:3443?encryption=none&type=grpc&mode=gun&security=reality&sni=api.github.com&fp=chrome&pbk=Ps_w7KP3VFrZv1niWfX3synmJq_d2c7sVSckxa3sgio#France"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnConnect = findViewById<Button>(R.id.btnConnect)
        val tvStatus = findViewById<TextView>(R.id.tvStatus)

        btnConnect.setOnClickListener {
            tvStatus.text = "Статус: Подготовка..."
            
            try {
                // Копируем файл из assets (куда его положил GitHub Actions) в папку данных приложения
                val binaryFile = File(filesDir, "xray_binary")
                if (!binaryFile.exists()) {
                    assets.open("xray_binary").use { input ->
                        FileOutputStream(binaryFile).use { output ->
                            input.copyTo(output)
                        }
                    }
                }
                
                // Даем права на выполнение
                binaryFile.setExecutable(true)
                
                tvStatus.text = "Статус: Движок готов (тест завершен)"
                Toast.makeText(this, "Бинарник Xray готов к запуску!", Toast.LENGTH_LONG).show()
                
            } catch (e: Exception) {
                tvStatus.text = "Статус: Ошибка!"
                Toast.makeText(this, "Ошибка: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }
}
