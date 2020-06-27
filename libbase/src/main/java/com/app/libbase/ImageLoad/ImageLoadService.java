package com.app.libbase.ImageLoad;

import android.widget.ImageView;

import com.alibaba.android.arouter.facade.template.IProvider;

public interface ImageLoadService extends IProvider {

    void displayImage(ImageView iv, String imageUrl);

}
