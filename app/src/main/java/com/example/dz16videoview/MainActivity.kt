package com.example.dz16videoview

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ComponentCaller
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.dz16videoview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val PICK_VIDEO_REQUEST_CODE = 101
    private lateinit var binding: ActivityMainBinding
    var countListPley: Int = 0

    private var videoList = mutableListOf(
        R.raw.small,
        R.raw.smoll2,
        R.raw.smoll3
    )
    //private var video =
    //    "https://videocdn.cdnpk.net/videos/6227a07e-ba94-46ac-ae62-4368d626090c/horizontal/previews/clear/large.mp4?token=exp=1734799323~hmac=30747497ec0d761a1c8b2fb7c904504d3028ce7625e19ba7a6ea4307bfc128a9"
    //private var video1 =
    //    "https://videocdn.cdnpk.net/joy/content/video/free/2014-06/large_preview/Blue_Sky_and_Clouds_Timelapse_0892__Videvo.mp4?token=exp=1734803454~hmac=04d8c13e4f1a5a490d45f6bb2a0ec514754c0b733b69a5631ad8dad8dd29fbce"


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Инициализация Тулбар
        var toolbarMain = binding.toolbarMain
        setSupportActionBar(toolbarMain)
        title = " Видео плеер"
        toolbarMain.subtitle = " Вер.1.Главная страница"
        toolbarMain.setLogo(R.drawable.pleer)

//Инициация видео

        binding.stopButton.setOnClickListener {
           stop()
        }

        binding.pauseButton.setOnClickListener {
            payse()
        }
        binding.playButton.setOnClickListener{
            val intent = Intent()
            intent.type = "video/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(
                Intent.createChooser(intent, "Select Video"),
                PICK_VIDEO_REQUEST_CODE
            )

            binding.videoView.start()
        }
        binding.nextButton.setOnClickListener {
            countListPley = (countListPley + 1) % videoList.size // Циклический переход вперед
            if (countListPley > videoList.size) {
                videoList[0]
            }
            switchVideo(countListPley)

        }

    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == PICK_VIDEO_REQUEST_CODE) {
            val videoUri = data?.data
            if (videoUri != null) {
                pley(videoUri)
            }
        }
    }

    //получили из галереи
    private fun pley (uri: Uri){

        val mediaController = android.widget.MediaController(this)
        binding.videoView.setMediaController(mediaController)
        binding.videoView.setVideoURI(uri)
        binding.videoView.requestFocus()

        Toast.makeText(
            applicationContext,
            "Идет  Воспроизведение ${uri}",
            Toast.LENGTH_LONG
        ).show()
        binding.videoView.start()
    }
//Проигрывание из списка скачаных видео
    private fun pleyLokal (uri: Int){

        val offLaneURI = Uri.parse("android.resource://" + packageName + "/" + uri)
        val mediaController = android.widget.MediaController(this)
        binding.videoView.setMediaController(mediaController)
        binding.videoView.setVideoURI(offLaneURI)
        binding.videoView.requestFocus()

        Toast.makeText(
            applicationContext,
            "Идет  Воспроизведение видео № ${countListPley}",
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
        Toast.makeText(
            applicationContext,
            "Выбранo видео №  ${countListPley}",
            Toast.LENGTH_LONG
        ).show()
       pleyLokal(videoList[countListPley])

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