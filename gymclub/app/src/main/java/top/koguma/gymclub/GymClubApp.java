package top.koguma.gymclub;

import android.app.Application;
import com.laputapp.Laputapp;
import top.koguma.gymclub.utils.Toaster;

public class GymClubApp extends Laputapp {

    @Override public void onCreate() {
        super.onCreate();
        Toaster.init(this);
    }
}
