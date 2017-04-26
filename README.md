# **Frame**
表格添加图片
![image](/pic/device-2017-04-26-190045.png)

### 1.用法
    使用前，对于Android Studio的用户，可以选择添加:
    
	compile 'com.github.ashLikun.ImageGridView:0.0.1'


    datas.add(new ShowData("http://img02.tooopen.com/images/20140504/sy_60294738471.jpg"));
    datas.add(new ShowData("http://pic.58pic.com/58pic/16/62/63/97m58PICyWM_1024.jpg"));
    datas.add(new ShowData("http://pic78.huitu.com/res/20160604/1029007_20160604114552332126_1.jpg"));
    datas.add(new ShowData("http://img05.tooopen.com/images/20150531/tooopen_sy_127457023651.jpg"));
    imageShowView = (ImagesShowView) findViewById(R.id.imageShowView);
    imageShowView.initData(datas);
    imageShowView.setImageShowLoader(new ImageShowViewLoader() {
        @Override
        public void displayImage(Context context, String path, ImageView imageView) {
            GlideUtils.show(imageView, path);
        }
    });
    imageShowView.setListener(new ImageShowListener<ShowData>() {

        @Override
        public void addOnClickListener(int remainNum) {
            if (index >= images.length) index = 0;
            imageShowView.addData(new ShowData(images[index++]));
        }
    });
    imageShowView.show();


