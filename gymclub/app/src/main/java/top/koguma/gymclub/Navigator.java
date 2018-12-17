package top.koguma.gymclub;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import top.koguma.gymclub.activity.ArticleActivity;
import top.koguma.gymclub.activity.LoginActivity;
import top.koguma.gymclub.activity.MapActivity;
import top.koguma.gymclub.activity.MediaPlayerActivity;
import top.koguma.gymclub.activity.RegisterActivity;

public final class Navigator {

    public static final String EXTRA_VIDEO_URL = "extra_video_url";
    public static final String DEFAULT_PHONE_NUMBER = "18515652921";
    public static final String EXTRA_CONTENT_URL = "extra_content_url";

    private Navigator() {
    }

    private static void startActivity(Context context, Class clz) {
        Intent intent = new Intent(context, clz);
        context.startActivity(intent);
    }

    public static void startLoginActivity(Context context) {
        startActivity(context, LoginActivity.class);
    }

    public static void startRegisterActivity(Context context) {
        startActivity(context, RegisterActivity.class);
    }

    public static void startArticleActivity(Context context,String content) {
        Intent intent = new Intent(context, ArticleActivity.class);
        intent.putExtra(EXTRA_CONTENT_URL, content);
        context.startActivity(intent);
    }

    public static void startMediaPlayActivity(Context context, String videoUrl) {
        Intent intent = new Intent(context, MediaPlayerActivity.class);
        intent.putExtra(EXTRA_VIDEO_URL, videoUrl);
        context.startActivity(intent);
    }

    public static void startEmailService(Context context) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc822");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[] {});
        intent.putExtra(Intent.EXTRA_SUBJECT, "email test");
        intent.putExtra(Intent.EXTRA_TEXT, "");
        context.startActivity(Intent.createChooser(intent, ""));
    }

    public static void startDialService(Context context) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + DEFAULT_PHONE_NUMBER));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static void startSMSService(Context context) {
        Intent intent = new Intent(Intent.ACTION_SENDTO,
            Uri.parse("smsto:" + DEFAULT_PHONE_NUMBER));
        intent.putExtra("sms_body", "test sms");
        context.startActivity(intent);
    }

    public static void startMapActivity(Context context) {
        startActivity(context, MapActivity.class);
    }
}
