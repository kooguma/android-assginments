package top.koguma.gymclub.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.laputapp.rx.RxBus;
import top.koguma.gymclub.event.ProfileUpdateEvent;
import top.koguma.gymclub.model.User;

public class UserUtils {

    private static final String KEY_ACCOUNT_JSON = "key_account_json";

    private static User sCurrentUser;
    private static SharedPreferences sSharedPreferences;
    private static Gson sGson;

    public static void init(Context context) {
        sSharedPreferences = context.getApplicationContext().getSharedPreferences("account", 0);
        sGson = GsonHelper.getDefault();
        readUser();
    }

    public static User getCurrentUser() {
        checkInit();
        return sCurrentUser;
    }

    public static boolean isLoggedIn() {
        checkInit();
        return sCurrentUser != null;
    }

    public static boolean haseName() {
        checkInit();
        return !TextUtils.isEmpty(sCurrentUser.getUsername());
    }

    public static String getPhone() {
        if (sCurrentUser != null && !TextUtils.isEmpty(sCurrentUser.getUsername())) {
            return sCurrentUser.getMobilePhoneNumber();
        }
        return null;
    }

    public static boolean isCurrentUser(String accountId) {
        checkInit();
        return sCurrentUser != null && TextUtils.equals(accountId, sCurrentUser.getObjectId());
    }

    public static void login(User account) {
        saveUser(account);
    }

    public static void update(User account) {
        saveUser(account);
    }

    public static void logout() {
        saveUser(null);
    }

    private static void readUser() {
        String accountJson = sSharedPreferences.getString(KEY_ACCOUNT_JSON, null);
        if (accountJson != null) {
            sCurrentUser = sGson.fromJson(accountJson, User.class);
        }
    }

    private static void saveUser(User user) {
        checkInit();
        sCurrentUser = user;
        String accountJson = user != null ? sGson.toJson(user) : null;
        sSharedPreferences.edit()
            .putString(KEY_ACCOUNT_JSON, accountJson)
            .apply();
        RxBus.getDefault().send(new ProfileUpdateEvent(user));
    }

    private static void checkInit() {
        if (sSharedPreferences == null) {
            throw new IllegalStateException("call init() first");
        }
    }
}
