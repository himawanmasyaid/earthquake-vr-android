package com.siklusdev.gempavr.ui.vr

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.navigation.navArgs
import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.spherical.SphericalGLSurfaceView
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.siklusdev.gempavr.databinding.ActivitySplashScreenBinding
import com.siklusdev.gempavr.databinding.ActivityVirtualRealityBinding
import com.siklusdev.gempavr.model.VideoModel

class VirtualRealityActivity : AppCompatActivity() {

    private val args by navArgs<VirtualRealityActivityArgs>()

    private var videoPlayer: SimpleExoPlayer? = null
    private lateinit var binding: ActivityVirtualRealityBinding

    private var playWhenReady: Boolean = true
    private var currentWindows = 0
    private var playBackPostion: Long = 0

//    private val siklusdev = "http://siklusdev.com/video/Earthquake%20Virtual%20Reality%20Experience%20360%20Movie%20Vol.1%20in%20a%20classroom.mp4"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityVirtualRealityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

        val params = binding.playerView.layoutParams
        params.width = ViewGroup.LayoutParams.MATCH_PARENT
        params.height = ViewGroup.LayoutParams.MATCH_PARENT
        binding.playerView.layoutParams = params

        (binding.playerView.videoSurfaceView as SphericalGLSurfaceView)
            .setDefaultStereoMode(C.STEREO_MODE_TOP_BOTTOM)

    }

    private fun initializePlayer() {

        videoPlayer = SimpleExoPlayer.Builder(this).build()

        isLog("id : ${args.id}")

        val video = VideoModel().fetchDetailVideo(args.id)

        isLog("video name : ${video.video_url}")

//        val url = "https://storage.googleapis.com/exoplayer-test-media-1/360/congo.mp4"

        val uri = Uri.parse(video.video_url)
        val mediaSource = buildMediaSource(uri)
        videoPlayer?.prepare(mediaSource)

        binding.playerView.player = videoPlayer
        binding.playerView.onResume()
    }

    override fun onStart() {
        super.onStart()
        if (isSupportedMultiWindow()) {
            initializePlayer()
        }
    }

    override fun onResume() {
        super.onResume()
        hideSystemUi()
        if ((isSupportedMultiWindow() || videoPlayer == null)) {
            initializePlayer()
        }
    }

    override fun onPause() {
        super.onPause()
        if (!isSupportedMultiWindow() || videoPlayer == null) {
            releasePlayer()
        }
    }

    private fun isSupportedMultiWindow() = Build.VERSION.SDK_INT > 23

    override fun onStop() {
        super.onStop()
        if (Build.VERSION.SDK_INT >= 24) {
            releasePlayer()
        }
    }

    private fun buildMediaSource(uri: Uri): MediaSource {
        val dataSourceFactory = DefaultDataSourceFactory(this, "javiermarsicano-VR-app")
        // Create a media source using the supplied URI
        return ProgressiveMediaSource.Factory(dataSourceFactory)
            .createMediaSource(uri)
    }

    private fun releasePlayer() {
        if (videoPlayer != null) {
            playWhenReady = videoPlayer!!.playWhenReady
            playBackPostion = videoPlayer!!.currentPosition
            currentWindows = videoPlayer!!.currentWindowIndex
            videoPlayer!!.release()
            videoPlayer = null
        }
    }

    @SuppressLint("InlinedApi")
    private fun hideSystemUi() {
        binding.playerView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LOW_PROFILE
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
    }

    private fun isLog(msg: String) {
        Log.e("VirtualReality", msg)
    }

}