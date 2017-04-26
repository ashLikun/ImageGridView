package com.hbung.imageshow.lietener;

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
 * @see IImageGridLoader 的再次封装，不需要createShowView，但是view的id要注意
 */

public abstract class ImageGridRootLoader<ROOT extends View>
        implements IImageGridLoader<ImageView, ROOT> {

    @Override
    public void displayImage(Context context, @DrawableRes Integer resId, ImageView imageView) {
        imageView.setImageResource(resId);
    }

    //不需要实现
    @Override
    public ImageView createShowView(Context context) {
        return null;
    }
}
