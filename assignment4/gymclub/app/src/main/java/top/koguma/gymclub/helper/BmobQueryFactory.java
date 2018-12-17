package top.koguma.gymclub.helper;

import android.content.Context;
import cn.bmob.v3.BmobQuery;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import top.koguma.gymclub.utils.NetworkUtils;

public class BmobQueryFactory {

    private static Context sContext;

    public static void init(Context context) {
        sContext = context;
    }

    public static <T> BmobQuery<T> createQuery(final Class<T> clz) {
        BmobQuery<T> query = new BmobQuery<>();
        query.setMaxCacheAge(TimeUnit.DAYS.toMillis(1));
        boolean isCache = query.hasCachedResult(clz);
        boolean isNetWorkConnected = NetworkUtils.isConnected(sContext);
        if (isCache && !isNetWorkConnected) {
            query.setCachePolicy(BmobQuery.CachePolicy.CACHE_ONLY);
        } else {
            query.setCachePolicy(BmobQuery.CachePolicy.NETWORK_ELSE_CACHE);
        }
        return query;
    }

}
