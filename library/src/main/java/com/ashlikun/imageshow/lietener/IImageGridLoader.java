package com.ashlikun.imageshow.lietener;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.view.View;

/**
 * 作者　　: 李坤
 * 创建时间:2017/4/26 0026　12:57
 * 邮箱　　：496546144@qq.com
 * <p>
 * 功能介绍：把显示图片的view构建与显示抽离出来由使用者实现
 * {@link ImageGridViewLoader}
 *
 * @see ImageGridRootLoader
 */

public interface IImageGridLoader<SHOW extends View, ROOT extends View> {
    void displayImage(Context context, String path, SHOW imageView);

    void displayImage(Context context, @DrawableRes Integer resId, SHOW imageView);

    /**
     * 作者　　: 李坤
     * 创建时间: 2017/4/26 0026 13:14
     * <p>
     * 方法功能：创建显示的Item的IMG
     * 实现这个方法就不需要实现createViewRoot
     */
    SHOW createShowView(Context context);

    /**
     * 作者　　: 李坤
     * 创建时间: 2017/4/26 0026 13:14
     * <p>
     * 方法功能：创建整个显示的Item
     * item的id必须是指定的Id
     * 实现这个方法就不需要实现createShowView
     */

    ROOT createViewRoot(Context context);
}
