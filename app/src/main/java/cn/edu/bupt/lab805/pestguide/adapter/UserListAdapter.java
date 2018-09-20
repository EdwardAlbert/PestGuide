package cn.edu.bupt.lab805.pestguide.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.edu.bupt.lab805.pestguide.R;
import cn.edu.bupt.lab805.pestguide.entity.User;
import cn.edu.bupt.lab805.pestguide.util.StringUtils;

/**
 * Created by zby on 2018/9/20.
 */

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ViewHolder> {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<User> datas;

    public UserListAdapter(Context mContext, List<User> datas) {
        this.mContext = mContext;
        this.datas = datas;
        mInflater = LayoutInflater.from(mContext);
    }

    public void setDatas(List<User> datas) {
        this.datas = datas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder = new ViewHolder(mInflater.inflate(R.layout.item_user_list, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvUsername.setText(StringUtils.whichString(datas.get(position).getUsername()));
        holder.tvRealname.setText(StringUtils.whichString(datas.get(position).getRealname()));
        holder.tvTitle.setText(StringUtils.whichString(datas.get(position).getTitle()));
        holder.tvMobile.setText(StringUtils.whichString(datas.get(position).getMobile()));
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_username)
        TextView tvUsername;
        @BindView(R.id.tv_realname)
        TextView tvRealname;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_mobile)
        TextView tvMobile;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
