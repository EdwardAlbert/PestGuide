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
import cn.edu.bupt.lab805.pestguide.bean.UploadData;
import cn.edu.bupt.lab805.pestguide.util.StringUtils;

/**
 * Created by zby on 2018/9/18.
 */

public class HistoryUploadAdapter extends RecyclerView.Adapter<HistoryUploadAdapter.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    private List<UploadData> datas;
    private OnItemClickListener listener;

    public HistoryUploadAdapter(Context context, List<UploadData> datas) {
        this.context = context;
        this.datas = datas;
        inflater = LayoutInflater.from(context);
    }

    public void setDatas(List<UploadData> datas) {
        this.datas = datas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_upload_history, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvId.setText(String.valueOf(datas.get(position).getId()));
        holder.tvTime.setText(datas.get(position).getCollecttime());
        holder.tvDevice.setText(StringUtils.whichString(datas.get(position).getSource(),"硬件设备"));
        if (datas.get(position).getTgrainbin() != null) {
            holder.tvBinname.setText(datas.get(position).getTgrainbin().getBinname());
        }
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int pos);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_binname)
        TextView tvBinname;
        @BindView(R.id.tv_device)
        TextView tvDevice;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_upload_id)
        TextView tvId;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onItemClick(v, getAdapterPosition());
                }
            });
        }
    }
}
