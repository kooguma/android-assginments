package top.koguma.gymclub;

import android.content.Context;
import android.content.Intent;
import top.koguma.gymclub.activity.LoginActivity;
import top.koguma.gymclub.activity.RegisterActivity;

public final class Navigator {

    private Navigator() {
    }

    private static void startActivity(Context context, Class clz) {
        Intent intent = new Intent(context, clz);
        context.startActivity(intent);
    }


    public static void startLoginActivity(Context context){
        startActivity(context,LoginActivity.class);
    }

    public static void startRegisterActivity(Context context){
        startActivity(context,RegisterActivity.class);

    }
}
