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
import cn.edu.bupt.lab805.pestguide.bean.RealInsects;

/**
 * Created by zby on 2017/4/19.
 */

public class UploadRVAdapter extends RecyclerView.Adapter<UploadRVAdapter.UploadVH> {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<RealInsects> mDatas;

    public void setDatas(List<RealInsects> mDatas) {
        this.mDatas = mDatas;
        notifyDataSetChanged();
    }

    public UploadRVAdapter(Context mContext, List<RealInsects> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
        mInflater = LayoutInflater.from(mContext);
    }

    public int getPestListSize() {
        return mDatas == null ? 0 : mDatas.size();
    }

    @Override
    public UploadVH onCreateViewHolder(ViewGroup parent, int viewType) {
        UploadVH holder = new UploadVH(mInflater.inflate(R.layout.item_rv_upload, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(UploadVH holder, int position) {
        if (mDatas.get(position).getNum() != null) {
            holder.tvPestNumber.setText(mDatas.get(position).getNum().toString());
        }
        holder.tvPestName.setText(mDatas.get(position).getKind());
    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }


    class UploadVH extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_kind_item_upload)
        TextView tvPestName;
        @BindView(R.id.tv_num_item_upload)
        TextView tvPestNumber;

        public UploadVH(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
