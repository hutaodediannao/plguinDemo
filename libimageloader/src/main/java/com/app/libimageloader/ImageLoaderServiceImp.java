package com.app.libimageloader;

import android.content.Context;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.app.libbase.ImageLoad.ImageLoadService;

@Route(path = "/imageLoad/imageLoad_service")
public class ImageLoaderServiceImp implements ImageLoadService {
    @Override
    public void displayImage(ImageView iv, String imageUrl) {
        ImageLoadManager.getInstance().displayImageForCircle(iv, imageUrl);
    }

    @Override
    public void init(Context context) {

    }
}
