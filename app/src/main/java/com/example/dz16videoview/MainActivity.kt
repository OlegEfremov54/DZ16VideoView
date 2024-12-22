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

    private lateinit var binding: ActivityMainBinding

    private var videoList = mutableListOf(
        R.raw.small,
        R.raw.smoll2,
        R.raw.smoll3
    )
    private var video = "https://videocdn.cdnpk.net/videos/6227a07e-ba94-46ac-ae62-4368d626090c/horizontal/previews/clear/large.mp4?token=exp=1734799323~hmac=30747497ec0d761a1c8b2fb7c904504d3028ce7625e19ba7a6ea4307bfc128a9"
private var  video1 ="https://videocdn.cdnpk.net/joy/content/video/free/2014-06/large_preview/Blue_Sky_and_Clouds_Timelapse_0892__Videvo.mp4?token=exp=1734803454~hmac=04d8c13e4f1a5a490d45f6bb2a0ec514754c0b733b69a5631ad8dad8dd29fbce"


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var countListPley:Int=0

        val mediaController = android.widget.MediaController(this)
        mediaController.setAnchorView(mediaController)
        val onLaneUri = Uri.parse(video)
        val offLaneURI = Uri.parse("android.resource://" + packageName + "/" + R.raw.sky)

        binding.playButton.setOnClickListener{
            pley(videoList[countListPley])
        }

        binding.pauseButton.setOnClickListener{
            payse()
        }

        binding.stopButton.setOnClickListener{
            stop()
        }

        binding.nextButton.setOnClickListener{
            countListPley = (countListPley + 1) % videoList.size // Циклический переход вперед
            if(countListPley>videoList.size){
                videoList[0]
            }
            switchVideo(countListPley)

        }

    }

    private fun pley (uri: Int){
        val offLaneURI = Uri.parse("android.resource://" + packageName + "/" + uri)
        val mediaController = android.widget.MediaController(this)
        binding.videoView.setMediaController(mediaController)
        binding.videoView.setVideoURI(offLaneURI)
        binding.videoView.requestFocus()

        Toast.makeText(
            applicationContext,
            "Идет  Воспроизведение",
            Toast.LENGTH_LONG
        ).show()
        binding.videoView.start()
    }

    private fun stop (){
        binding.videoView.stopPlayback()

    }

    private fun payse(){
        binding.videoView.pause()
    }



    private fun switchVideo(countListPley:Int) {
        binding.videoView.stopPlayback()

        pley(videoList[countListPley])
        Toast.makeText(
            applicationContext,
            "Выбранo видео ${countListPley}, нажмите Воспроизведение",
            Toast.LENGTH_LONG
        ).show()
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