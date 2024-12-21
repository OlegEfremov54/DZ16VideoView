package com.example.dz16videoview

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.media.session.MediaController
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.dz16videoview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var toolbarMain: Toolbar
    private lateinit var binding: ActivityMainBinding
    private var mediaPlayer: MediaPlayer? = null
    private var videoList = mutableListOf(
        R.raw.small,
        R.raw.smoll2,
        R.raw.smoll3
    )
    private var video = "https://dzen.ru/video/watch/6729dafa4cc5bf3cb83e07a4?rid=3929810250.1069.1734601551466.76095&referrer_clid=1400"



    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val mediaController = android.widget.MediaController(this)
        mediaController.setAnchorView(mediaController)
        val onLaneUri = Uri.parse(video)
        binding.videoView.setMediaController(mediaController)
        binding.videoView.setVideoURI(onLaneUri)
        binding.videoView.requestFocus()
        binding.videoView.start()



    }




    //Инициализация Меню
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.infoMenuMain -> {
                Toast.makeText(
                    applicationContext, "Автор Ефремов О.В. Создан 21.12.2024",
                    Toast.LENGTH_LONG
                ).show()
            }

            R.id.exitMenuMain -> {
                Toast.makeText(
                    applicationContext, "Работа приложения завершена",
                    Toast.LENGTH_LONG
                ).show()
                finishAffinity()
            }
        }
        return super.onOptionsItemSelected(item)
    }


}