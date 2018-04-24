package cn.edu.bupt.lab805.pestguide.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.edu.bupt.lab805.pestguide.R;
import cn.edu.bupt.lab805.pestguide.entity.Pest;

/**
 * Created by zby on 2018/4/23.
 */

public class PestBookAdapter extends RecyclerView.Adapter<PestBookAdapter.ViewHolder> {

    private static final String TAG = "PestBookAdapter";

    private Context context;
    private LayoutInflater inflater;
    private List<Pest> datas;
    private OnItemClickListener listener;

    public PestBookAdapter(Context context, List<Pest> datas) {
        this.context = context;
        this.datas = datas;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_pest_book, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.pestName.setText(datas.get(position).getName());
        holder.pestCategory.setText(datas.get(position).getCategory());
        holder.pestFamily.setText(datas.get(position).getFamily());
        holder.pestFeature.setText(datas.get(position).getFeature());
        if (datas.get(position).getImportance() == 5) {
            holder.importance.setText("重要");
        } else {
            holder.importance.setText("");
        }
        switch (datas.get(position).getCategory()){
            case "甲虫类":
                holder.typeIcon.setImageResource(R.mipmap.book_bettle);
                break;
            case "蛾类":
                holder.typeIcon.setImageResource(R.mipmap.book_moth);
                break;
            case "螨类":
                holder.typeIcon.setImageResource(R.mipmap.book_mite);
                break;
        }
        String path = "file:///android_asset/"+datas.get(position).getPictureurl();
        Glide.with(context).load(path)
                .placeholder(R.mipmap.default_select_image).into(holder.pestIcon);
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    public interface OnItemClickListener{
        void onItemClick(View view,int pos);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.pest_name)
        TextView pestName;
        @BindView(R.id.pest_category)
        TextView pestCategory;
        @BindView(R.id.pest_family)
        TextView pestFamily;
        @BindView(R.id.pest_feature)
        TextView pestFeature;
        @BindView(R.id.pest_type_icon)
        ImageView typeIcon;
        @BindView(R.id.pest_importance)
        TextView importance;
        @BindView(R.id.pest_icon)
        ImageView pestIcon;

        public ViewHolder(final View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null){
                        listener.onItemClick(v,getAdapterPosition());
                    }
                }
            });
            ButterKnife.bind(this, itemView);
        }
    }
}
