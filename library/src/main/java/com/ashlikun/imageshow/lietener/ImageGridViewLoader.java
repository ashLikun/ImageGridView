package com.ashlikun.imageshow.lietener;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.view.View;
import android.widget.ImageView;

/**
 * 作者　　: 李坤
 * 创建时间:2017/4/26 0026　14:38
 * 邮箱　　：496546144@qq.com
 * <p>
 * 功能介绍：
 *
 * @see IImageGridLoader 的再次封装，createViewRoot
 */

public abstract class ImageGridViewLoader
        implements IImageGridLoader<ImageView, View> {

    @Override
    public void displayImage(Context context, @DrawableRes Integer resId, ImageView imageView) {
        imageView.setImageResource(resId);
    }

    //不需要实现
    @Override
    public View createViewRoot(Context context) {
        return null;
    }

    @Override
    public ImageView createShowView(Context context) {
        ImageView iv = new ImageView(context);
        iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return iv;
    }
}
