package com.awu.ordermenu.activity.app;

import android.app.Application;
import android.content.Context;

/**
 * Created by awu on 2015-10-13.
 */
public class OrderApplication extends Application {
    /**
     * application context.
     */
    private static Context mContext;

    @Override
    public void onCreate(){
        super.onCreate();
        mContext = getApplicationContext();
    }

    /**
     * get application context.
     *
     * @author awu
     * created at 2015-10-13 13:25
     * @return application context.
     */
    public static Context AppContext(){
        return mContext;
    }
}
