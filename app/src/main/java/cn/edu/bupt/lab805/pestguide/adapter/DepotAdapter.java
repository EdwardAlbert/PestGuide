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
import cn.edu.bupt.lab805.pestguide.entity.Depot;

/**
 * Created by zby on 2018/4/24.
 */

public class DepotAdapter extends RecyclerView.Adapter<DepotAdapter.ViewHolder> {

    private static final String TAG = "DepotAdapter";

    private Context context;
    private LayoutInflater inflater;
    private List<Depot> datas;
    private OnItemClickListener listener;

    public DepotAdapter(Context context, List<Depot> datas) {
        this.context = context;
        this.datas = datas;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder = new ViewHolder(inflater.inflate(R.layout.item_depot, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.lcmc.setText(datas.get(position).getBinname());
        holder.depottype.setText(datas.get(position).getTypebin());
        holder.capacity.setText(datas.get(position).getCapacity().toString());
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

        @BindView(R.id.tv_lcmc)
        TextView lcmc;
        @BindView(R.id.tv_depottype)
        TextView depottype;
        @BindView(R.id.tv_capacity)
        TextView capacity;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null){
                        listener.onItemClick(v,getAdapterPosition());
                    }
                }
            });
        }
    }
}
