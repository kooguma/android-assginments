package top.koguma.gymclub.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import cn.bmob.v3.b.V;
import com.facebook.drawee.view.SimpleDraweeView;
import com.laputapp.ui.adapter.RxRecyclerAdapter;
import com.youth.banner.Banner;
import java.util.Arrays;
import top.koguma.gymclub.R;
import top.koguma.gymclub.helper.BannerImageLoader;
import top.koguma.gymclub.model.Course;

public class CoursesListAdapter extends RxRecyclerAdapter<Course> {

    private static final int VIEW_TYPE_HEADER = 0;
    private static final int VIEW_TYPE_ITEM = 1;
    private static final String[] IMAGE_URLS = new String[] {
        "http://img02.tooopen.com/images/20150503/tooopen_sy_121171916311.jpg",
        "http://img02.tooopen.com/images/20150503/tooopen_sy_121173296845.jpg",
        "http://pic.shiliyoupin.com/tupian/HTTP2ltZzAyLnRvb29wZW4uY29tL2ltYWdlcy8yMDE1MDUwMy90b29vcGVuX3N5XzEyMTE3Mjc2NjQzOS5qcGclog.jpg",
        "http://img02.tooopen.com/images/20150503/tooopen_sy_121173163976.jpg",
        "http://img02.tooopen.com/images/20150503/tooopen_sy_121173296845.jpg" };

    private OnItemClickListener<Course> mListener;
    private OnHeaderItemClickListener mHeaderItemClickListener;

    public CoursesListAdapter(Context context) {
        super(context);
    }

    public void setOnItemClickListener(OnItemClickListener<Course> listener) {
        this.mListener = listener;
    }

    public void setHeaderItemClickListener(OnHeaderItemClickListener listener) {
        this.mHeaderItemClickListener = listener;
    }

    @Override public void bindView(Course course, int pos, RecyclerView.ViewHolder viewHolder) {
        if (viewHolder instanceof CoursesItemHolder) {
            CoursesListAdapter.CoursesItemHolder holder = (CoursesItemHolder) viewHolder;
            holder.bind(course);
        }
    }

    @NonNull @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        if (viewType == VIEW_TYPE_HEADER) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.list_courses_header, null);
            return new CoursesHeaderHolder(view);
        } else {
            view = LayoutInflater.from(getContext())
                .inflate(R.layout.list_courses_item, parent, false);
            return new CoursesItemHolder(view);
        }

    }

    @Override public int getItemViewType(int position) {
        return position == 0 ? VIEW_TYPE_HEADER : VIEW_TYPE_ITEM;
    }

    class CoursesHeaderHolder extends RecyclerView.ViewHolder {
        Banner imgHeader;
        TextView mTxtEmail;
        TextView mTxtSMS;
        TextView mTxtCall;
        TextView mTxtLocation;

        CoursesHeaderHolder(View itemView) {
            super(itemView);
            imgHeader = itemView.findViewById(R.id.img_header);
            mTxtEmail = itemView.findViewById(R.id.txt_email);
            mTxtSMS = itemView.findViewById(R.id.txt_sms);
            mTxtCall = itemView.findViewById(R.id.txt_call);
            mTxtLocation = itemView.findViewById(R.id.txt_location);
            imgHeader.setImageLoader(new BannerImageLoader());
            imgHeader.setImages(Arrays.asList(IMAGE_URLS));
            imgHeader.start();
            HeaderItemClickListener listener = new HeaderItemClickListener();
            mTxtEmail.setOnClickListener(listener);
            mTxtSMS.setOnClickListener(listener);
            mTxtCall.setOnClickListener(listener);
            mTxtLocation.setOnClickListener(listener);
        }
    }

    class HeaderItemClickListener implements View.OnClickListener {

        @Override public void onClick(View v) {
            if (mHeaderItemClickListener != null) {
                v.setOnClickListener(new View.OnClickListener() {
                    @Override public void onClick(View v) {
                        mHeaderItemClickListener.onHeaderItemClick(v);
                    }
                });
            }
        }
    }

    class CoursesItemHolder extends RecyclerView.ViewHolder {
        TextView title;
        SimpleDraweeView image;
        TextView desc;
        TextView readAndCollectionCount;
        SimpleDraweeView avatar;
        TextView name;
        View view;

        CoursesItemHolder(View itemView) {
            super(itemView);
            view = itemView;
            title = itemView.findViewById(R.id.txt_title);
            image = itemView.findViewById(R.id.img_item);
            desc = itemView.findViewById(R.id.txt_desc);
            readAndCollectionCount = itemView.findViewById(R.id.txt_read_collection_count);
            avatar = itemView.findViewById(R.id.img_avatar);
            name = itemView.findViewById(R.id.txt_name);
            image.setAspectRatio(2f);
        }

        void bind(final Course course) {
            title.setText(course.title);
            image.setImageURI(course.imageUrl);
            avatar.setImageURI(course.avatarUrl);
            desc.setText(course.description.trim());
            readAndCollectionCount.setText(
                getContext().getString(R.string.read_collection_count_format, course.readCount,
                    course.collectCount)
            );
            name.setText(course.name);
            if (mListener != null) {
                view.setOnClickListener(new View.OnClickListener() {
                    @Override public void onClick(View v) {
                        mListener.onItemClick(course);
                    }
                });
            }
        }
    }

}
