package com.ashlikun.imageshow.test;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.ashlikun.glideutils.GlideUtils;
import com.ashlikun.imageshow.ImageGridView;
import com.ashlikun.imageshow.lietener.ImageGridListener;
import com.ashlikun.imageshow.lietener.ImageGridViewLoader;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<ShowData> datas = new ArrayList<>();
    ImageGridView imageShowView;

    private String[] images = new String[]{
            "http://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1493203338583&di=8d7085a38c4a39dcf123d70cb452f560&imgtype=0&src=http%3A%2F%2Fimg27.51tietu.net%2Fpic%2F2017-011500%2F20170115001256mo4qcbhixee164299.jpg",
            "http://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1493203338582&di=5d6f0115a56a84875ea627a82a133d8f&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F13%2F61%2F00%2F61a58PICtPr_1024.jpg"
            , "http://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1493203338582&di=0874a899750ae225c32f5ee46c20b3de&imgtype=0&src=http%3A%2F%2Fd.5857.com%2Fxgs_150428%2Fdesk_005.jpg",
            "http://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1493203338582&di=d338eef8e9dc871d965ee447ed485946&imgtype=0&src=http%3A%2F%2Fwww.zhlzw.com%2FUploadFiles%2FArticle_UploadFiles%2F201204%2F20120412123914329.jpg",
            "http://img1.mm131.com/pic/2330/1.jpg",
            "http://img1.mm131.com/pic/2229/1.jpg",
            "http://img1.mm131.com/pic/2229/2.jpg",
            "http://img1.mm131.com/pic/2229/3.jpg",
            "http://img1.mm131.com/pic/2229/4.jpg",
            "http://img1.mm131.com/pic/2229/5.jpg",
            "http://img1.mm131.com/pic/2229/6.jpg",
            "http://img1.mm131.com/pic/2229/7.jpg",
            "http://img1.mm131.com/pic/2229/8.jpg",
            "http://img1.mm131.com/pic/2229/9.jpg"
    };
    private int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GlideUtils.init(new GlideUtils.OnNeedListener() {
            @Override
            public Application getApplication() {
                return MainActivity.this.getApplication();
            }

            @Override
            public boolean isDebug() {
                return false;
            }

            @Override
            public String getBaseUrl() {
                return "www";
            }
        });
        setContentView(R.layout.activity_main);
        datas.add(new ShowData("http://img02.tooopen.com/images/20140504/sy_60294738471.jpg"));
        datas.add(new ShowData("http://pic.58pic.com/58pic/16/62/63/97m58PICyWM_1024.jpg"));
        datas.add(new ShowData("http://pic78.huitu.com/res/20160604/1029007_20160604114552332126_1.jpg"));
        datas.add(new ShowData("http://img05.tooopen.com/images/20150531/tooopen_sy_127457023651.jpg"));
        imageShowView = (ImageGridView) findViewById(R.id.imageShowView);
        imageShowView.initData(datas);
        imageShowView.setImageShowLoader(new ImageGridViewLoader() {
            @Override
            public void displayImage(Context context, String path, ImageView imageView) {
                GlideUtils.show(imageView, path);
            }
        });
        imageShowView.setListener(new ImageGridListener<ShowData>() {

            @Override
            public void addOnClickListener(int remainNum) {
                if (index >= images.length) index = 0;
                imageShowView.addData(new ShowData(images[index++]));
            }
        });
        imageShowView.show();
    }
}
