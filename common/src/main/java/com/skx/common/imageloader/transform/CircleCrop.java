package com.skx.common.imageloader.transform;

import android.graphics.Bitmap;

import androidx.annotation.NonNull;

import java.security.MessageDigest;

/**
 * 作者：shiguotao
 * 日期：2018/11/7 11:51 AM
 * 描述：圆形转换 （这里并没有什么特别的处理，只是做了自定义的Transformation 接口实现）
 */
public final class CircleCrop implements TransformAdapter {
    private static final int VERSION = 1;
    private static final String ID = "com.skx.common.imageloader.transform." + VERSION;
    private static final byte[] ID_BYTES = ID.getBytes(CHARSET);

    @Override
    public boolean equals(Object o) {
        return o instanceof CircleCrop;
    }

    @Override
    public Bitmap transform(@NonNull Bitmap toTransform, int outWidth, int outHeight) {
        return null;
    }

    @Override
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
        messageDigest.update(ID_BYTES);
    }

    @Override
    public int hashCode() {
        return ID.hashCode();
    }
}
