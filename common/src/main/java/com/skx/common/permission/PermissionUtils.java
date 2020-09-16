package com.skx.common.permission;


import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.core.app.AppOpsManagerCompat;
import androidx.core.content.ContextCompat;

import java.util.Arrays;
import java.util.List;

/**
 * 描述 : 权限工具类
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/9/16 5:19 PM
 */
public class PermissionUtils {


    /**
     * 检查权限
     *
     * @param cx         上下文
     * @param permission 申请的权限
     * @param listener   授权结果回调
     */
    public static void checkPermission(Context cx, String[] permission, PermissionResultListener listener) {

    }


    /**
     * 检查目标权限是否已被授权
     *
     * @param context     上下文
     * @param permissions 需要检查的权限
     * @return true：全部均已授权
     */
    public static boolean hasPermission(@NonNull Context context, @NonNull String... permissions) {
        return hasPermission(context, Arrays.asList(permissions));
    }

    /**
     * 检查目标权限是否已被授权
     *
     * @param context     上下文
     * @param permissions 需要检查的权限
     * @return true：全部均已授权
     */
    public static boolean hasPermission(@NonNull Context context, @NonNull List<String> permissions) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {// 6.0 以下的系统
            return true;
        }
        for (String permission : permissions) {

            // 危险权限检查
            String op = AppOpsManagerCompat.permissionToOp(permission);
            if (TextUtils.isEmpty(op))
                continue;
            int result = AppOpsManagerCompat.noteProxyOp(context, op, context.getPackageName());
            if (result == AppOpsManagerCompat.MODE_IGNORED)
                return false;

            result = ContextCompat.checkSelfPermission(context, permission);
            if (result != PackageManager.PERMISSION_GRANTED){
                return false;
            }

        }
        return true;
    }

}
