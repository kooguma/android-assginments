package top.koguma.gymclub.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.fastui.uipattern.IRecycler;
import com.laputapp.http.BaseResponse;
import com.laputapp.ui.adapter.RxRecyclerAdapter;
import io.reactivex.Flowable;
import java.util.ArrayList;
import java.util.List;
import top.koguma.gymclub.R;
import top.koguma.gymclub.adapter.ListAdapter;
import top.koguma.gymclub.decoration.GridItemDecoration;
import top.koguma.gymclub.model.ListItem;

public class ListFragment extends GymClubBaseFragment implements IRecycler<ListItem> {

    @Nullable @Override
    public View onCreateView(
        @NonNull LayoutInflater inflater,
        @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
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
        return new ListAdapter(getContext());
    }

    @Override
    public Flowable<? extends BaseResponse<List<ListItem>>> requestData(String offset, String page, String pageSize) {
        List<ListItem> items = new ArrayList<>();
        items.add(ListItem.testInstance());
        items.add(ListItem.testInstance());
        items.add(ListItem.testInstance());
        items.add(ListItem.testInstance());
        items.add(ListItem.testInstance());
        items.add(ListItem.testInstance());
        items.add(ListItem.testInstance());
        items.add(ListItem.testInstance());
        getRecyclerManager().onCacheLoaded(items);
        return null;
    }

}
