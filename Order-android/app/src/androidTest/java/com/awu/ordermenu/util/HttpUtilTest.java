package com.awu.ordermenu.util;

import android.test.AndroidTestCase;

import java.util.concurrent.CountDownLatch;

/**
 * Created by awu on 2015-10-14.
 */
public class HttpUtilTest extends AndroidTestCase {
    final CountDownLatch signal = new CountDownLatch(1);
    public void testSendRequest(){
        HttpUtil.sendHttpRequest("http://www.baidu.com", new HttpUtil.HttpCallbackListener() {
            @Override
            public void onFinish(String response) {
                assertNotNull(response);
                signal.countDown();
            }

            @Override
            public void onError(Exception e) {
                fail(e.getMessage());
                signal.countDown();;
            }
        });
        try {
            signal.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
