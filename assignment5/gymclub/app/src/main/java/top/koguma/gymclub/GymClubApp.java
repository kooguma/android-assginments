package top.koguma.gymclub;

import android.app.Application;
import cn.bmob.v3.Bmob;
import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.laputapp.Laputapp;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;
import top.koguma.gymclub.helper.BmobQueryFactory;
import top.koguma.gymclub.utils.Toaster;
import top.koguma.gymclub.utils.UserUtils;

public class GymClubApp extends Laputapp {

    @Override public void onCreate() {
        super.onCreate();
        Toaster.init(this);
        UserUtils.init(this);
        Bmob.initialize(this, BuildConfig.BMOB_APP_ID);
        BmobQueryFactory.init(this);
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        SDKInitializer.initialize(this);
        //自4.3.0起，百度地图SDK所有接口均支持百度坐标和国测局坐标，用此方法设置您使用的坐标类型.
        //包括BD09LL和GCJ02两种坐标，默认是BD09LL坐标。
        SDKInitializer.setCoordType(CoordType.BD09LL);
        //友盟
        UMConfigure.init(this, BuildConfig.UMENG_APP_KEY
            , "umeng", UMConfigure.DEVICE_TYPE_PHONE, "");
        //微信、微博、QQ
        PlatformConfig.setWeixin("wxdc1e388c3822c80b", "3baf1193c85774b3fd9d18447d76cab0");
        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad","http://sns.whalecloud.com");
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");

    }
}
