package top.koguma.gymclub.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.laputapp.ui.adapter.RxRecyclerAdapter;
import top.koguma.gymclub.R;
import top.koguma.gymclub.model.Dashboard;

public class DashboardListAdapter extends RxRecyclerAdapter<Dashboard> {

    public DashboardListAdapter(Context context) {
        super(context);
    }

    @Override
    public void bindView(Dashboard item, int position, RecyclerView.ViewHolder viewHolder) {
        ListViewHolder holder = (ListViewHolder) viewHolder;
        holder.bind(item);
    }

    @NonNull @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = getLayoutInflater().inflate(R.layout.list_dashboard_item, parent, false);
        return new ListViewHolder(view);
    }

    class ListViewHolder extends RecyclerView.ViewHolder {

        private SimpleDraweeView image;
        private SimpleDraweeView userAvatar;
        private TextView title;
        private TextView userName;
        private TextView flavor;

        ListViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.img_item);
            userAvatar = itemView.findViewById(R.id.img_avatar);
            title = itemView.findViewById(R.id.txt_title);
            userName = itemView.findViewById(R.id.txt_name);
            flavor = itemView.findViewById(R.id.txt_flavor);
            image.setAspectRatio(1.33f);
        }

        public void bind(Dashboard item) {
            image.setImageURI(item.imageUrl);
            userAvatar.setImageURI(item.avatarUrl);
            title.setText(item.title);
            userName.setText(item.userName);
            flavor.setText(item.flavorCount);
        }

    }
}
