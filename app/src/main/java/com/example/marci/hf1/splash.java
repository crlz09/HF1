package com.example.marci.hf1;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import java.util.Timer;
import java.util.TimerTask;

import static android.content.ContentValues.TAG;
import static com.bumptech.glide.load.engine.DiskCacheStrategy.NONE;

/**
 * Created by marci on 20/11/2017.
 */

public class splash extends Activity{

    private static final long SPLASH_SCREEN_DELAY = 5000;


    /**
     * Method to make json array request where response starts with [
     * */

    // Progress dialo

    VideoView videoSplash;

    FrameLayout frameLayout;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.splash);

        videoSplash = (VideoView) findViewById(R.id.videoView);
        frameLayout = (FrameLayout) findViewById(R.id.placeholder);

        Uri video = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.hf_1);

        videoSplash.setVideoURI(video);


        videoSplash.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setOnInfoListener(new MediaPlayer.OnInfoListener() {
                    @Override
                    public boolean onInfo(MediaPlayer mp, int what, int extra) {
                        Log.d(TAG, "onInfo, what = " + what);
                        if (what == MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START) {
                            // video started; hide the placeholder.
                            frameLayout.setVisibility(View.GONE);
                            videoSplash.start();
                            return true;
                        }
                        return false;
                    }
                });
            }
        });

        videoSplash.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                startActivity(new Intent(splash.this, LoginActivity.class));
                finish();
            }
        });

        videoSplash.start();


    }

}
