package com.example.jian.myaidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

/**
 * Created by 29560 on 2017/11/6.
 */

public class PersonService extends Service {
    public static String names="Tom";
    public PersonBinder mPersonBinder;

    @Override
    public void onCreate() {
        super.onCreate();
        mPersonBinder=new PersonBinder() ;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mPersonBinder;
    }

    public class PersonBinder extends IPerson.Stub {
        @Override
        public String getAllName() throws RemoteException {
            return names;
        }
    }
}
