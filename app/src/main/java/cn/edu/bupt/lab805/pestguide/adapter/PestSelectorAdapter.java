package cn.edu.bupt.lab805.pestguide.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.edu.bupt.lab805.pestguide.R;
import cn.edu.bupt.lab805.pestguide.bean.RealInsects;


/**
 * Created by zby on 2017/4/10.
 */

public class PestSelectorAdapter extends RecyclerView.Adapter<PestSelectorAdapter.PestSelectorVH> {

    public Context mContext;
    public LayoutInflater mInflater;
    public List<RealInsects> mDatas;

    public PestSelectorAdapter(Context mContext, List<RealInsects> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
        mInflater = LayoutInflater.from(mContext);
    }

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }


    @Override
    public PestSelectorVH onCreateViewHolder(ViewGroup parent, int viewType) {
        PestSelectorVH holder = new PestSelectorVH(mInflater.inflate(R.layout.item_rv_pest_selector, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final PestSelectorVH holder, final int position) {
        holder.tvPestName.setText(mDatas.get(position).getKind());
        if (mDatas.get(position).getNum() != null) {
            holder.etPestNumber.setText(mDatas.get(position).getNum().toString());
        }
        holder.etPestNumber.setTag(position);
        final TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().equals("")) {
                    mDatas.get(position).setNum(0);
                } else {
                    mDatas.get(position).setNum(Integer.parseInt(s.toString()));
                }
            }
        };
        //给etPestNumber设置焦点监听器
        holder.etPestNumber.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                holder.etPestNumber.addTextChangedListener(watcher);
            } else {
                holder.etPestNumber.removeTextChangedListener(watcher);
            }
        });
        // 如果设置了回调，则设置点击事件
        if (mOnItemClickLitener != null) {
            holder.itemView.setOnClickListener(v -> {
                int pos = holder.getLayoutPosition();
                mOnItemClickLitener.onItemClick(holder.itemView, pos);
            });

            holder.itemView.setOnLongClickListener(v -> {
                int pos = holder.getLayoutPosition();
                mOnItemClickLitener.onItemLongClick(holder.itemView, pos);
                return true;
            });
        }
    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    public void addData() {
        RealInsects bean = new RealInsects();
//        mDatas.add(0, bean);
//        notifyItemInserted(0);
        mDatas.add(bean);
        notifyItemInserted(mDatas.size());
    }

    public void editName(int pos, String kind) {
        if (pos >= 0 && mDatas.size() > pos) {
            mDatas.get(pos).setKind(kind);
            notifyItemChanged(pos);
        }
    }

    public void removeData(int pos) {
        if (pos >= 0 && mDatas.size() > pos) {
            mDatas.remove(pos);
            notifyItemRemoved(pos);
        }
    }

    class PestSelectorVH extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_item_pest_selector)
        TextView tvPestName;
        @BindView(R.id.et_item_pest_selector)
        EditText etPestNumber;

        public PestSelectorVH(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

}
