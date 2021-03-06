package com.example.youtubeplayer

import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView

const val YOUTUBE_VIDEO_ID = "gxEPV4kolz0"
const val YOUTUBE_PLAYLIST = "PLMC9KNkIncKtPzgY-5rmhvj7fax8fdxoj"
private val TAG = "YoutubeActivity"

class YoutubeActivity : YouTubeBaseActivity() , YouTubePlayer.OnInitializedListener  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        setContentView(R.layout.activity_youtube)
//        val layout = findViewById<ConstraintLayout>(R.id.activity_youtube)

        val layout = layoutInflater.inflate(R.layout.activity_youtube , null) as ConstraintLayout
        setContentView(layout)
////
//        val button1 = Button(this)
//        button1.layoutParams = ConstraintLayout.LayoutParams(400,400)
//        button1.text = "CLICK"
//        layout.addView(button1)

        val playerView = YouTubePlayerView(this)
        playerView.layoutParams = ConstraintLayout.LayoutParams( ViewGroup.LayoutParams.MATCH_PARENT , ViewGroup.LayoutParams.MATCH_PARENT  )
        layout.addView(playerView)

        playerView.initialize(getString(R.string.GOOGLE_API_KEY) , this)


    }

    override fun onInitializationSuccess(provider: YouTubePlayer.Provider?, youTubePlayer: YouTubePlayer?, wasRestored: Boolean){
        Log.d(TAG , "onInitialisationSuccess : provider is ${provider?.javaClass}")
        Log.d(TAG , "onInitialisationSuccess : youtubePLayer is ${youTubePlayer?.javaClass}")
        Toast.makeText(this , "YouTubePlayer initialised successfully " , Toast.LENGTH_SHORT).show()
        youTubePlayer?.setPlaybackEventListener(playbackEventListener)
        youTubePlayer?.setPlayerStateChangeListener(playerStateChangeListener)

        if(!wasRestored){
            youTubePlayer?.loadVideo(YOUTUBE_VIDEO_ID)
        }else{
            youTubePlayer?.play()
        }

    }

    override fun onInitializationFailure(provider: YouTubePlayer.Provider?, youTubeInitializationResult: YouTubeInitializationResult?){
        val requestCode = 0
        if (youTubeInitializationResult?.isUserRecoverableError == true){
            youTubeInitializationResult.getErrorDialog(this , requestCode).show()
        }else{
            val errorMessage = "There was am error initialising the YoutubePlayer $youTubeInitializationResult"
            Toast.makeText(this , errorMessage , Toast.LENGTH_LONG).show()
        }
    }

    private val playbackEventListener = object : YouTubePlayer.PlaybackEventListener{
        override fun onSeekTo(p0: Int) {}

        override fun onBuffering(p0: Boolean) {}

        override fun onPlaying() {
           Toast.makeText(this@YoutubeActivity , "video is playing " , Toast.LENGTH_SHORT).show()
        }

        override fun onStopped() {
            Toast.makeText(this@YoutubeActivity , "video is stopped " , Toast.LENGTH_SHORT).show()
        }

        override fun onPaused() {
            Toast.makeText(this@YoutubeActivity , "video is paused " , Toast.LENGTH_SHORT).show()
        }
    }


    private val playerStateChangeListener = object : YouTubePlayer.PlayerStateChangeListener{
        override fun onAdStarted() {
            Toast.makeText(this@YoutubeActivity , "Ad has popped up" , Toast.LENGTH_SHORT).show()
        }

        override fun onLoading() {}

        override fun onVideoStarted() {
            Toast.makeText(this@YoutubeActivity , "video has started " , Toast.LENGTH_SHORT).show()
        }

        override fun onLoaded(p0: String?) {}

        override fun onVideoEnded() {
            Toast.makeText(this@YoutubeActivity , "video is finished " , Toast.LENGTH_SHORT).show()
        }

        override fun onError(p0: YouTubePlayer.ErrorReason?) {}
    }

}
