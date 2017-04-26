package com.hbung.imageshow.lietener;

import com.hbung.imageshow.ImageGridBean;

import java.util.List;

/**
 * 作者　　: 李坤
 * 创建时间:2017/4/26 0026　18:17
 * 邮箱　　：496546144@qq.com
 * <p>
 * 功能介绍：VIew的添加事件
 */

public interface IImageGridListener<T extends ImageGridBean> {
    /**
     * 作者　　: 李坤
     * 创建时间: 2017/4/26 0026 13:57
     * <p>
     * 方法功能：
     *
     * @param remainNum 剩余多少可以显示
     */
    public abstract void addOnClickListener(int remainNum);

    public void onDelClickListener(int position, int remainNum);

    /**
     * 作者　　: 李坤
     * 创建时间: 2017/4/26 0026 13:57
     * <p>
     * 方法功能：
     *
     * @param remainNum 剩余多少可以显示
     */

    public void onImgClickListener(List<T> list, int position, int remainNum);
}
