package com.ashlikun.imageshow;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.ashlikun.imageshow.lietener.IImageGridListener;
import com.ashlikun.imageshow.lietener.IImageGridLoader;
import com.ashlikun.imageshow.lietener.ImageGridListener;

import java.util.List;

/**
 * 作者　　: 李坤
 * 创建时间:2017/4/26 0026　12:50
 * 邮箱　　：496546144@qq.com
 * <p>
 * 功能介绍：
 */

public class ImageGridAdapter extends RecyclerView.Adapter<ImageGridAdapter.MyViewHolder> {
    private int mMaxNum;
    private int showRowNum;
    private int itemSpace;
    private int delImgRes;
    private int addImgRes;
    private boolean isShowAnim;
    private boolean isShowDel;

    private Context context;
    private IImageGridLoader showLoader;
    private IImageGridListener listener;
    private List<? extends ImageGridBean> listDatas;

    public ImageGridAdapter(Context context, IImageGridLoader showLoader,
                            IImageGridListener listener, List<? extends ImageGridBean> listDatas) {
        this.context = context;
        this.showLoader = showLoader;
        this.listener = listener;
        this.listDatas = listDatas;
    }

    private int computeItemSize(ViewGroup parent) {
        int res = parent.getMeasuredWidth() / showRowNum;
        return res;
    }


    public void setmMaxNum(int mMaxNum) {
        this.mMaxNum = mMaxNum;
    }

    public void setDelImgRes(int delImgRes) {
        this.delImgRes = delImgRes;
    }

    public void setItemSpace(int itemSpace) {
        this.itemSpace = itemSpace;
    }

    public void setAddImgRes(int addImgRes) {
        this.addImgRes = addImgRes;
    }

    public void setShowAnim(boolean showAnim) {
        isShowAnim = showAnim;
    }

    public void setShowDel(boolean showDel) {
        isShowDel = showDel;
    }

    public void setShowRowNum(int showRowNum) {
        this.showRowNum = showRowNum;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = showLoader.createViewRoot(context);
        boolean isCreateRootItem = true;
        if (view == null) {
            isCreateRootItem = false;
            view = new FrameLayout(context);
        }
        int iconHeight = computeItemSize(parent);
        view.setLayoutParams(new RecyclerView.LayoutParams(iconHeight, iconHeight));
        MyViewHolder holder = new MyViewHolder(view, showLoader, this, itemSpace, isCreateRootItem);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        if (listDatas.size() == 0 || listDatas.size() <= position) {//最后一个
            showLoader.displayImage(context, addImgRes, holder.ivShowView);
        } else {
            ImageGridBean bean = listDatas.get(position);
            showLoader.displayImage(context, bean.getShowPath(), holder.ivShowView);
            if (isShowDel) {
                holder.ivDel.setVisibility(View.VISIBLE);
                if (holder.ivDel instanceof ImageView) {
                    ((ImageView) holder.ivDel).setImageResource(delImgRes);
                } else {
                    holder.ivDel.setBackgroundResource(delImgRes);
                }
            } else {
                holder.ivDel.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return listDatas.size() < mMaxNum ? listDatas.size() + 1 : listDatas.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public View ivShowView;
        public View ivDel;
        ImageGridAdapter listener;


        public MyViewHolder(View itemView, IImageGridLoader showLoader, ImageGridAdapter listener, int itemSpace, boolean isCreateRootItem) {
            super(itemView);
            this.listener = listener;
            if (isCreateRootItem) {
                ivShowView = itemView.findViewById(R.id.iv_image_show_img);
                ivDel = itemView.findViewById(R.id.iv_image_show_del);
                if (ivShowView == null) {
                    new Exception("显示Img的id必须为:iv_image_show_img");
                }
            } else {
                ivShowView = showLoader.createShowView(itemView.getContext());
                FrameLayout.LayoutParams ivShowViewparams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT);
                ivShowViewparams.setMargins(itemSpace / 2, itemSpace / 2, itemSpace / 2, itemSpace / 2);
                ivShowView.setLayoutParams(ivShowViewparams);
                ivDel = new ImageView(itemView.getContext());
                FrameLayout.LayoutParams delParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
                delParams.gravity = Gravity.TOP | Gravity.END;
                ivDel.setPadding(5, 5, 5, 5);
                ivDel.setLayoutParams(delParams);
                ivShowView.setId(R.id.iv_image_show_img);
                ivDel.setId(R.id.iv_image_show_del);
                ((FrameLayout) itemView).addView(ivShowView);
                ((FrameLayout) itemView).addView(ivDel);
            }
            ivDel.setOnClickListener(this);
            if (ivShowView != null) {
                ivShowView.setOnClickListener(this);
            }
        }

        @Override
        public void onClick(View v) {
            int i = v.getId();
            if (i == R.id.iv_image_show_img) {
                listener.onImgClickListener(getLayoutPosition());
            } else if (i == R.id.iv_image_show_del) {
                listener.onDelClickListener(getLayoutPosition());
            }
        }
    }

    public void onDelClickListener(int position) {
        listDatas.remove(position);
        notifyItemRemoved(position);
        if (listener != null && showLoader instanceof ImageGridListener)
            ((ImageGridListener) listener).onDelClickListener(position, mMaxNum - listDatas.size());
    }

    public void onImgClickListener(int position) {
        if (position == listDatas.size()) {
            if (listener != null) {
                listener.addOnClickListener(mMaxNum - position - 1);
            }
        } else {
            if (listener != null && showLoader instanceof ImageGridListener) {
                ((ImageGridListener) listener).onImgClickListener(listDatas, position, mMaxNum > listDatas.size() ? mMaxNum - listDatas.size() - 1 :
                        (listDatas.get(mMaxNum - 1) == null ? 1 : 0));
            }
        }
    }
}
