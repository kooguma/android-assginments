package top.koguma.gymclub.activity;

import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.SeekBar;
import top.koguma.gymclub.Navigator;
import top.koguma.gymclub.R;
import top.koguma.gymclub.helper.MediaLoader;

public class MediaPlayerActivity extends GymClubBaseActivity implements SurfaceHolder.Callback {

    private SurfaceView mSurfaceView;
    private SurfaceHolder mSurfaceHolder;
    private SeekBar mSeekBar;
    private Handler mHandler = new Handler();

    private MediaLoader mMediaLoader;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media);
        initView();
        initMediaLoader();
        //runOnUiThread();
    }

    public void initView() {
        mSurfaceView = (SurfaceView) findViewById(R.id.surface_view);
        mSeekBar = (SeekBar) findViewById(R.id.seek_bar);
        mSurfaceHolder = mSurfaceView.getHolder();
        mSurfaceHolder.setFormat(PixelFormat.TRANSPARENT);
        mSurfaceHolder.setKeepScreenOn(true);
        mSurfaceHolder.addCallback(this);
    }

    public void initMediaLoader() {
        final String url = getIntent().getStringExtra(Navigator.EXTRA_VIDEO_URL);
        mMediaLoader = new MediaLoader(this, url);
        mMediaLoader.setSeekBar(mSeekBar);
    }

    public void runOnUiThread() {
        runOnUiThread(new Runnable() {
            @Override public void run() {
                final int currentPosition = mMediaLoader.getCurrentPosition() / 1000;
                Log.e("TAG", "currentPosition = " + currentPosition);
                mSeekBar.setProgress(currentPosition);
                mHandler.postDelayed(this, 1000);
            }
        });
    }

    @Override protected void onPause() {
        super.onPause();
        mMediaLoader.onPause();
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        mMediaLoader.onDestroy();
    }

    @Override public void surfaceCreated(SurfaceHolder holder) {
        mMediaLoader.setDisplay(holder);
    }

    @Override public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
