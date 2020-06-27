package com.app.libimageloader;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * 图片加载类，与外界唯一通信类
 */
public class ImageLoadManager {


    private ImageLoadManager() {

    }

    private static class ImageLoadManagerSingle {
        private static ImageLoadManager imageLoadManager = new ImageLoadManager();
    }

    public static ImageLoadManager getInstance() {
        return ImageLoadManagerSingle.imageLoadManager;
    }

    /**
     * 为ImageView加载图片
     */
    public void displayImageForView(ImageView imageView, String imgUrl) {
        Glide.with(imageView.getContext())
                .asBitmap()
                .load(imgUrl)
                .apply(initOptios())
                .transition(BitmapTransitionOptions.withCrossFade())
                .into(imageView);
    }

    @SuppressLint("CheckResult")
    private RequestOptions initOptios() {
        RequestOptions options = new RequestOptions();
        options.placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .skipMemoryCache(false)
                .priority(Priority.NORMAL);
        return options;
    }

    /**
     * 加载圆形图片
     *
     * @param imageView
     * @param imgUrl
     */
    public void displayImageForCircle(final ImageView imageView, String imgUrl) {
        Glide.with(imageView.getContext())
                .asBitmap()
                .load(imgUrl)
                .apply(initOptios())
                .into(new BitmapImageViewTarget(imageView) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable drawable = RoundedBitmapDrawableFactory
                                .create(imageView.getResources(), resource);
                        drawable.setCircular(true);
                        imageView.setImageDrawable(drawable);
                    }
                });
    }

    /**
     * 模糊处理
     * 设置一个viewGoup的背景图
     */
    public void displayImageForViewGroup(final ViewGroup group, String imgUrl) {
        Glide.with(group.getContext())
                .asBitmap()
                .load(imgUrl)
                .apply(initOptios())
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull final Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        Observable.just(resource)
                                .map(new Function<Bitmap, Drawable>() {
                                    @Override
                                    public Drawable apply(Bitmap bitmap) throws Exception {
                                        Drawable drawable = new BitmapDrawable(Utils.doBlur(resource, 100, true));
                                        return drawable;
                                    }
                                })
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Consumer<Drawable>() {
                                    @Override
                                    public void accept(Drawable drawable) throws Exception {
                                        group.setBackground(drawable);
                                    }
                                });
                    }
                });
    }




}
