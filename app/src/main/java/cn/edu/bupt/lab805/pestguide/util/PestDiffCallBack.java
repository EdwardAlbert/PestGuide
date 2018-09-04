package cn.edu.bupt.lab805.pestguide.util;

import android.support.v7.util.DiffUtil;

import java.util.List;

import cn.edu.bupt.lab805.pestguide.entity.Pest;

/**
 * Created by zby on 2018/9/4.
 */

public class PestDiffCallBack extends DiffUtil.Callback {

    private List<Pest> mOldDatas, mNewDatas;

    public PestDiffCallBack(List<Pest> mOldDatas, List<Pest> mNewDatas) {
        this.mOldDatas = mOldDatas;
        this.mNewDatas = mNewDatas;
    }

    @Override
    public int getOldListSize() {
        return mOldDatas == null ? 0 : mOldDatas.size();
    }

    @Override
    public int getNewListSize() {
        return mNewDatas == null ? 0 : mNewDatas.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return mOldDatas.get(oldItemPosition).getId().equals(mNewDatas.get(newItemPosition).getId());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return true;
    }
}
