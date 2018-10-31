package com.skx.tomike.service;

import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;

import com.skx.tomike.IMyAidlInterface;

public class LocalService extends Service {

    private MyBinder binder;
    private MyServiceConnection conn;
    private PendingIntent pIntent;

    public LocalService() {
    }


    @Override
    public void onCreate() {
        super.onCreate();
        if (binder == null) {
            binder = new MyBinder();
        }
        conn = new MyServiceConnection();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // 链接远程服务
        this.bindService(new Intent(this, RemoteService.class), conn, Context.BIND_IMPORTANT);
        // 提高服务优先级，避免过多呗杀掉
//        Notification notification = new Notification(R.drawable.icon_beijing, "安全服务启动中", 3000);
//        pIntent = PendingIntent.getService(this, 0, intent, 0);
////        notification.
//        startForeground(startId, notification);
        return START_STICKY;
    }


    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    class MyBinder extends IMyAidlInterface.Stub {

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }
    }

    class MyServiceConnection implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            // 说明远程服务挂了
            LocalService.this.startService(new Intent(LocalService.this, RemoteService.class));
            // 链接远程服务
            LocalService.this.bindService(new Intent(LocalService.this, RemoteService.class), conn, Context.BIND_IMPORTANT);
        }
    }
}
