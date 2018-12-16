package top.koguma.gymclub.activity;

import android.os.Bundle;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.sug.OnGetSuggestionResultListener;
import com.baidu.mapapi.search.sug.SuggestionResult;
import com.baidu.mapapi.search.sug.SuggestionSearch;
import com.baidu.mapapi.search.sug.SuggestionSearchOption;
import java.util.ArrayList;
import java.util.List;
import top.koguma.gymclub.R;

public class MapActivity extends GymClubBaseActivity implements OnGetSuggestionResultListener {

    private MapView mMapView;
    private SuggestionSearch mSuggestionSearch;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        mMapView = (MapView) findViewById(R.id.map_View);
        initView();
    }

    private void initView() {
        mSuggestionSearch = SuggestionSearch.newInstance();
        mSuggestionSearch.setOnGetSuggestionResultListener(this);

        LatLng point = new LatLng(39.957811, 116.348861);

        BitmapDescriptor bitmap = BitmapDescriptorFactory
            .fromResource(R.drawable.icon_gcoding);

        //构建MarkerOption，用于在地图上添加Marker

        OverlayOptions option = new MarkerOptions()
            .zIndex(20)
            .position(point)
            .icon(bitmap);

        //在地图上添加Marker，并显示

        mMapView.getMap().addOverlay(option);

        MapStatus.Builder builder = new MapStatus.Builder();
        builder.target(point).zoom(15);

        mMapView.getMap().animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
    }

    @Override protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override protected void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
        mSuggestionSearch.destroy();
    }

    @Override public void onGetSuggestionResult(SuggestionResult res) {
        if (res == null || res.getAllSuggestions() == null) {
            return;
            //未找到相关结果
        }
        //获取在线建议检索结果
        // List<SuggestionResult.SuggestionInfo> infos = res.getAllSuggestions();
        // List<LatLng> points = new ArrayList<>();
        // List<OverlayOptions> options = new ArrayList<>();
        // BitmapDescriptor bitmap = BitmapDescriptorFactory
        //     .fromResource(R.drawable.ic_marker);
        // for (SuggestionResult.SuggestionInfo info : infos) {
        //     LatLng point = info.pt;
        //     OverlayOptions option = new MarkerOptions()
        //         .position(point)
        //         .icon(bitmap);
        //     options.add(option);
        // }
        // //构建MarkerOption，用于在地图上添加Marker
        //
        // //在地图上添加Marker，并显示
        // mMapView.getMap().addOverlays(options);
    }
}
