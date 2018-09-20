package cn.edu.bupt.lab805.pestguide.util;

import android.support.v7.util.DiffUtil;

import java.util.List;

import cn.edu.bupt.lab805.pestguide.bean.UploadData;

/**
 * Created by zby on 2018/9/19.
 */

public class UploadDiffCallback extends DiffUtil.Callback {

    private List<UploadData> mOldDatas, mNewDatas;

    public UploadDiffCallback(List<UploadData> mOldDatas, List<UploadData> mNewDatas) {
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
