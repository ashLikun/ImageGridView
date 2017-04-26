package com.hbung.imageshow.test;

import com.hbung.imageshow.ImageGridBean;

/**
 * 作者　　: 李坤
 * 创建时间:2017/4/26 0026　14:58
 * 邮箱　　：496546144@qq.com
 * <p>
 * 功能介绍：
 */

public class ShowData implements ImageGridBean {
    String path;

    public ShowData(String path) {
        this.path = path;
    }

    @Override
    public String getShowPath() {
        return path;
    }
}
