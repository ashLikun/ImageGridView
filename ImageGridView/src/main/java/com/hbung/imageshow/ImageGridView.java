package com.hbung.imageshow;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.hbung.imageshow.lietener.IImageGridListener;
import com.hbung.imageshow.lietener.IImageGridLoader;
import com.hbung.imageshow.lietener.ImageGridListener;

import java.util.List;

/**
 * 作者　　: 李坤
 * 创建时间:2017/4/26 0026　9:17
 * 邮箱　　：496546144@qq.com
 * <p>
 * 功能介绍：
 */

public class ImageGridView extends FrameLayout {

    RecyclerView recycleView;
    private ImageGridAdapter adapter;
    private IImageGridLoader iImageShowLoader;
    private IImageGridListener listener;
    private List<ImageGridBean> listDatas;

    private int showItemSpace = 5;//item间距
    private int showMaxNum = 9;//最大个数
    private int showAddImg;//添加的图片
    private int showDelImg;//删除的图片
    private boolean showIsShowDel = true;//是否显示删除按钮
    private boolean showIsShowAnim = true;//是否展示动画
    private int showRowNum = 4;//列数


    public ImageGridView(@NonNull Context context) {
        this(context, null);
    }

    public ImageGridView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public ImageGridView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ImageGridView);
        showItemSpace = a.getDimensionPixelSize(R.styleable.ImageGridView_show_item_space, dp2px(context, showItemSpace));
        showMaxNum = a.getInt(R.styleable.ImageGridView_show_max, showMaxNum);
        showRowNum = a.getInt(R.styleable.ImageGridView_show_row_num, showRowNum);
        showAddImg = a.getResourceId(R.styleable.ImageGridView_show_add_img, R.mipmap.image_show_piceker_add);
        showDelImg = a.getResourceId(R.styleable.ImageGridView_show_del_img, R.mipmap.image_show_piceker_del);
        showIsShowDel = a.getBoolean(R.styleable.ImageGridView_show_is_show_del, true);
        showIsShowAnim = a.getBoolean(R.styleable.ImageGridView_show_is_show_anim, true);
        a.recycle();
        recycleView = new RecyclerView(context);
        addView(recycleView);

    }

    /**
     * 作者　　: 李坤
     * 创建时间: 2017/4/26 0026 12:48
     * <p>
     * 方法功能：实现recycleView的构建
     */

    public void show() {
        adapter = new ImageGridAdapter(getContext(), iImageShowLoader, listener, listDatas);
        if (!showIsShowAnim) {
            recycleView.setItemAnimator(null);
        } else if (recycleView.getItemAnimator() == null) {
            recycleView.setItemAnimator(new DefaultItemAnimator());
        }
        recycleView.setLayoutManager(new GridLayoutManager(getContext(), showRowNum));
        adapter.setItemSpace(showItemSpace);
        adapter.setShowRowNum(showRowNum);
        adapter.setAddImgRes(showAddImg);
        adapter.setDelImgRes(showDelImg);
        adapter.setShowAnim(showIsShowAnim);
        adapter.setmMaxNum(showMaxNum);
        adapter.setShowDel(showIsShowDel);
        recycleView.setAdapter(adapter);
    }


    /**
     * 作者　　: 李坤
     * 创建时间: 2017/4/26 0026 18:23
     * <p>
     * 方法功能：
     *
     * @see ImageGridListener
     * @see IImageGridListener
     */

    public void setListener(IImageGridListener listener) {
        this.listener = listener;
    }

    public void setImageShowLoader(IImageGridLoader iImageShowLoader) {
        this.iImageShowLoader = iImageShowLoader;
    }

    /**
     * dp转px
     *
     * @param context 上下文
     * @param dpValue dp值
     * @return px值
     */
    public int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 作者　　: 李坤
     * 创建时间: 2017/4/26 0026 16:13
     * <p>
     * 方法功能：添加一条数据
     */

    public <T extends ImageGridBean> void addData(T bean) {
        if (bean == null) {
            return;
        }
        this.listDatas.add(bean);
        if (adapter != null) {
            adapter.notifyItemInserted(listDatas.size() - 1);
            if (this.listDatas.size() >= showMaxNum) {
                adapter.notifyItemRemoved(adapter.getItemCount());
            }
        }

    }

    /**
     * 作者　　: 李坤
     * 创建时间: 2017/4/26 0026 16:23
     * <p>
     * 方法功能：批量添加数据
     */
    public <T extends ImageGridBean> void addData(List<T> list) {
        if (list == null) {
            return;
        }
        this.listDatas.addAll(list);

        if (adapter != null)
            adapter.notifyItemRangeInserted(this.listDatas.size() - list.size(), list.size());
        if (this.listDatas.size() >= showMaxNum) {
            adapter.notifyItemRemoved(adapter.getItemCount());
        }
    }

    /**
     * 首次添加数据
     *
     * @param list
     * @param <T>
     */
    public <T extends ImageGridBean> void initData(List<T> list) {
        if (list == null) return;
        this.listDatas = (List<ImageGridBean>) list;

        if (adapter != null)
            adapter.notifyItemRangeInserted(this.listDatas.size() - list.size(), list.size());
        if (this.listDatas.size() >= showMaxNum) {
            adapter.notifyItemRemoved(adapter.getItemCount());
        }
    }
}
