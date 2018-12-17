package top.koguma.gymclub.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import com.fastui.uipattern.IRecycler;
import com.laputapp.http.BaseResponse;
import com.laputapp.ui.adapter.RxRecyclerAdapter;
import io.reactivex.Flowable;
import java.util.ArrayList;
import java.util.List;
import top.koguma.gymclub.R;
import top.koguma.gymclub.adapter.DashboardListAdapter;
import top.koguma.gymclub.decoration.GridItemDecoration;
import top.koguma.gymclub.helper.BmobQueryFactory;
import top.koguma.gymclub.model.Dashboard;
import top.koguma.gymclub.utils.Toaster;

public class ListFragment extends GymClubBaseFragment implements IRecycler<Dashboard> {

    @Nullable @Override
    public View onCreateView(
        @NonNull LayoutInflater inflater,
        @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dashboard_list, container, false);
    }

    @Override public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getRecyclerManager().getRecyclerView()
            .addItemDecoration(
                new GridItemDecoration.Builder(getContext())
                    .setColorResource(R.color.window_background_gray)
                    .setHorizontalSpan(R.dimen.item_decoration)
                    .setVerticalSpan(R.dimen.item_decoration)
                    .build()
            );
        getRecyclerManager().getRecyclerView()
            .setLayoutManager(new GridLayoutManager(getContext(), 2));
    }

    @Override public RxRecyclerAdapter createRecyclerViewAdapter() {
        return new DashboardListAdapter(getContext());
    }

    @Override
    public Flowable<? extends BaseResponse<List<Dashboard>>> requestData(String offset, String page, String pageSize) {
        BmobQuery<Dashboard> dashboardQuery = BmobQueryFactory.createQuery(Dashboard.class);
        dashboardQuery.findObjects(new FindListener<Dashboard>() {
            @Override public void done(List<Dashboard> list, BmobException e) {
                if (e == null) {
                    getRecyclerManager().onCacheLoaded(list);
                } else {
                    Toaster.showToast(e.getErrorCode());
                }
            }
        });
        return null;
    }

}
