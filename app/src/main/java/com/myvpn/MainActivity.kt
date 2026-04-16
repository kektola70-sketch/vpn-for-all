package com.myvpn

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
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
            tvStatus.text = "Статус: Запускаю..."
            runVpn()
        }
    }

    private fun runVpn() {
        val file = File(filesDir, "xray")
        // Копируем файл из assets в память
        assets.open("xray_binary").use { input ->
            FileOutputStream(file).use { output ->
                input.copyTo(output)
            }
        }
        file.setExecutable(true) // Даем права на запуск

        // Запуск (это упрощенный пример)
        val process = ProcessBuilder(file.absolutePath, "run", "-c", "твоя_ссылка_или_config.json")
            .redirectErrorStream(true)
            .start()
            
        // В реальном приложении тут нужен цикл для чтения логов
    }
}
