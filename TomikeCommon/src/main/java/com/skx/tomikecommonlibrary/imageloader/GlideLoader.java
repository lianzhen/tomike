package com.skx.tomikecommonlibrary.imageloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import java.io.File;
import java.lang.reflect.ParameterizedType;


/**
 * 作者：shiguotao
 * 日期：2018/10/15 下午3:54
 * 描述：
 */
class GlideLoader implements ILoader {

    private RequestOptions options;
    private Context mContext;
    private Object source;

//    private Class<E> transcodeClass;


    public void init(Context context) {
        this.mContext = context;
    }

    @Override
    public <T> void load(T t) {
        source = t;
    }

    @Override
    public void apply(LoadOptions loadOptions) {
        if (loadOptions == null) {
            loadOptions = LoadOptions.getDefaultLoadOptions();
        }
        options = new RequestOptions();

        if (loadOptions.isShowPlaceholder()) {
            if (loadOptions.getPlaceholderResId() > 0) {
                options = options.placeholder(loadOptions.getPlaceholderResId());
            } else if (loadOptions.getPlaceholderDrawable() != null) {
                options = options.placeholder(loadOptions.getPlaceholderDrawable());
            }
        }

        if (loadOptions.getErrorResId() > 0) {
            options = options.error(loadOptions.getErrorResId());
        } else if (loadOptions.getErrorDrawable() != null) {
            options = options.error(loadOptions.getErrorDrawable());
        }

        if (loadOptions.getFallbackResId() > 0) {
            options = options.fallback(loadOptions.getFallbackResId());
        } else if (loadOptions.getFallbackDrawable() != null) {
            options = options.fallback(loadOptions.getFallbackDrawable());
        }
        //                .transforms(new CenterCrop(), new RoundedCorners(120))
//                .transform(new BitmapTransformation() {
//                    @Override
//                    protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth, int outHeight) {
//                        return null;
//                    }
//
//                    @Override
//                    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
//                    }
//                })
//                .circleCrop()
//                .sizeMultiplier(0.20f);
        ;

//        transcodeClass = (Class<E>) loadOptions.getSourceType();

//        暂时不对外开放这个缓存配置
//        switch (loadOptions.getPriority()) {
//            case HIGH:
//                options.priority(com.bumptech.glide.Priority.HIGH);
//                break;
//            case LOW:
//                options.priority(com.bumptech.glide.Priority.LOW);
//                break;
//            default:
//                options.priority(com.bumptech.glide.Priority.NORMAL);
//                break;
//        }

//        暂时不对外开放这个缓存配置
//        switch (loadOptions.getDiskCacheStrategy()) {
//            case ALL:
//                options.diskCacheStrategy(com.bumptech.glide.load.engine.DiskCacheStrategy.ALL);
//                break;
//            case NONE:
//                options.diskCacheStrategy(com.bumptech.glide.load.engine.DiskCacheStrategy.NONE);
//                break;
//            case DATA:
//                options.diskCacheStrategy(com.bumptech.glide.load.engine.DiskCacheStrategy.DATA);
//                break;
//            case RESOURCE:
//                options.diskCacheStrategy(com.bumptech.glide.load.engine.DiskCacheStrategy.RESOURCE);
//                break;
//            case AUTOMATIC:
//                options.diskCacheStrategy(com.bumptech.glide.load.engine.DiskCacheStrategy.RESOURCE);
//                break;
//        }
    }


    @SuppressWarnings("unchecked")
    @Override
    public <E, T extends Target<E>> T into(final T target) {
        RequestBuilder<?> drawableRequestBuilder;

        ParameterizedType type = (ParameterizedType) target.getClass().getGenericSuperclass();
        Class<E> z = (Class) type.getActualTypeArguments()[0];

        // 输出类型
        switch (z.getSimpleName()) {
            case "Bitmap":
                drawableRequestBuilder = Glide.with(mContext).asBitmap().transition(BitmapTransitionOptions.withCrossFade());
                break;
            case "File":
                drawableRequestBuilder = Glide.with(mContext).asFile();
                break;
            default:
                drawableRequestBuilder = Glide.with(mContext).asDrawable().transition(DrawableTransitionOptions.withCrossFade());
                break;
        }

        // 加载类型
        if (source instanceof String) {
            drawableRequestBuilder = drawableRequestBuilder.load((String) source);
        } else if (source instanceof Uri) {
            drawableRequestBuilder = drawableRequestBuilder.load((Uri) source);
        } else if (source instanceof Drawable) {
            drawableRequestBuilder = drawableRequestBuilder.load((Drawable) source);
        } else if (source instanceof Bitmap) {
            drawableRequestBuilder = drawableRequestBuilder.load((Bitmap) source);
        } else if (source instanceof Integer) {
            drawableRequestBuilder = drawableRequestBuilder.load((Integer) source);
        } else if (source instanceof File) {
            drawableRequestBuilder = drawableRequestBuilder.load((File) source);
        } else if (source instanceof byte[]) {
            drawableRequestBuilder = drawableRequestBuilder.load((byte[]) source);
        } else {
            drawableRequestBuilder = drawableRequestBuilder.load(source);
        }

        drawableRequestBuilder.apply(options).into(new SimpleTarget() {
            @Override
            public void onResourceReady(@NonNull Object resource, @Nullable Transition transition) {
                target.onResourceReady((E) resource);
            }
        });
        return target;
    }

    @Override
    public <T extends ImageView> void into(T target) {
        Glide.with(mContext).load(source).transition(DrawableTransitionOptions.withCrossFade()).apply(options).into(target);
    }

    @Override
    public void resume() {
        Glide.with(mContext).resumeRequests();
    }

    /**
     * 取消正在进行的任何负载，但不清除已完成负载的资源。
     */
    @Override
    public void pause() {
        Glide.with(mContext).pauseRequests();
    }

    @Override
    public void cancel() {
    }

    @Override
    public void release() {

    }
}
