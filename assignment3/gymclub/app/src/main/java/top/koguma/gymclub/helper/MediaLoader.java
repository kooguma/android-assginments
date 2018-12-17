package top.koguma.gymclub.helper;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;
import android.view.SurfaceHolder;
import android.widget.SeekBar;
import java.io.IOException;

public class MediaLoader implements MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener {

    private static final String TAG = "MediaLoader";

    private MediaPlayer mPlayer;
    private SeekBar mSeekBar;

    public MediaLoader(Context context, String url) {
        mPlayer = new MediaPlayer();
        mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mPlayer.setOnPreparedListener(this);
        mPlayer.setOnErrorListener(this);
        try {
            mPlayer.setDataSource(context, Uri.parse(url));
        } catch (IOException e) {
            e.printStackTrace();
        }
        mPlayer.prepareAsync();
    }

    public void setSeekBar(SeekBar seekBar) {
        mSeekBar = seekBar;
    }

    public int getCurrentPosition() {
        if (mPlayer == null) return 0;
        return mPlayer.getCurrentPosition();
    }

    public int getVideoDuration() {
        if (mPlayer == null) return 0;
        return mPlayer.getDuration();
    }

    public void setDisplay(SurfaceHolder holder) {
        mPlayer.setDisplay(holder);
    }

    private void start() {
        if (mPlayer != null) {
            mPlayer.start();
        }
    }

    public void stop() {
        if (mPlayer != null && mPlayer.isPlaying()) {
            mPlayer.stop();
        }
    }

    public void seekTo(int msec) {
        if (mPlayer != null) {
            mPlayer.seekTo(msec);
        }
    }

    public void onPause() {
        if (mPlayer != null && mPlayer.isPlaying()) {
            mPlayer.pause();
        }

    }

    public void onDestroy() {
        if (mPlayer != null && mPlayer.isPlaying()) {
            mPlayer.stop();
            mPlayer.release();
        }
    }

    @Override public void onPrepared(MediaPlayer mp) {
        mSeekBar.setMax(getVideoDuration());
        start();
    }

    @Override public boolean onError(MediaPlayer mp, int what, int extra) {
        Log.e(TAG, "media loader error : " + what);
        return false;
    }

}
