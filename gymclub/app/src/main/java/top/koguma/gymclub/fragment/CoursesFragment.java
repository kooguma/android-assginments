package top.koguma.gymclub.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import com.dynamic.refresher.RefreshHelper;
import com.fastui.uipattern.IRecycler;
import com.laputapp.http.BaseResponse;
import com.laputapp.ui.BaseFragment;
import com.laputapp.ui.adapter.RxRecyclerAdapter;
import io.reactivex.Flowable;
import java.util.ArrayList;
import java.util.List;
import top.koguma.gymclub.Navigator;
import top.koguma.gymclub.R;
import top.koguma.gymclub.adapter.CoursesListAdapter;
import top.koguma.gymclub.adapter.DashboardListAdapter;
import top.koguma.gymclub.adapter.OnHeaderItemClickListener;
import top.koguma.gymclub.adapter.OnItemClickListener;
import top.koguma.gymclub.helper.BmobQueryFactory;
import top.koguma.gymclub.model.Course;
import top.koguma.gymclub.model.Dashboard;
import top.koguma.gymclub.utils.Toaster;

public class CoursesFragment extends GymClubBaseFragment implements IRecycler<Course> {

    private static final String TAG = "CoursesFragment";

    @Nullable @Override
    public View onCreateView(
        @NonNull LayoutInflater inflater,
        @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_courses, container, false);
    }

    @Override protected void onSetupToolbar(Toolbar toolbar) {
        super.onSetupToolbar(toolbar);
        toolbar.setTitle(R.string.main_courses);
    }

    @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getRecyclerManager().setRefreshMode(RefreshHelper.RefreshMode.NONE);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(),
            DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(
            ContextCompat.getDrawable(getContext(), R.drawable.divider_8dp));
        getRecyclerManager().getRecyclerView().addItemDecoration(dividerItemDecoration);
    }

    @Override public RxRecyclerAdapter<Course> createRecyclerViewAdapter() {
        final CoursesListAdapter adapter = new CoursesListAdapter(getContext());
        adapter.setOnItemClickListener(new OnItemClickListener<Course>() {
            @Override public void onItemClick(Course course) {
                Navigator.startMediaPlayActivity(getContext(), course.videoUrl);
            }
        });
        adapter.setHeaderItemClickListener(new OnHeaderItemClickListener() {
            @Override public void onHeaderItemClick(View view) {
                switch (view.getId()) {
                    case R.id.txt_call:
                        Navigator.startDialService(getContext());
                        break;
                    case R.id.txt_sms:
                        Navigator.startSMSService(getContext());
                        break;
                    case R.id.txt_email:
                        Navigator.startEmailService(getContext());
                        break;
                    case R.id.txt_location:
                        Navigator.startMapActivity(getContext());
                        break;
                    default:
                        break;
                }
            }
        });
        return adapter;
    }

    @Override
    public Flowable<? extends BaseResponse<List<Course>>> requestData(String offset, String page, String pageSize) {
        BmobQuery<Course> courseQuery = BmobQueryFactory.createQuery(Course.class);
        courseQuery.findObjects(new FindListener<Course>() {
            @Override public void done(List<Course> list, BmobException e) {
                if (e == null) {
                    getRecyclerManager().onCacheLoaded(list);
                } else {
                    Log.e(TAG, e.getMessage());
                }
            }
        });
        return null;
    }
}
