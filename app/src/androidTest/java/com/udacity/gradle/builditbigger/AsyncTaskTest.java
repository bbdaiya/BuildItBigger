package com.udacity.gradle.builditbigger;

import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeUnit;

/**
 * Created by bbdaiya on 18-Dec-16.
 */
@RunWith(AndroidJUnit4.class)
public class AsyncTaskTest {
    @Test
    public void test() throws Exception{
        try {
            MainActivity mainActivity = new MainActivity();
            EndPointsAsyncTask endpointsAsyncTask = new EndPointsAsyncTask();
            endpointsAsyncTask.execute(mainActivity);
            String result = endpointsAsyncTask.get(30, TimeUnit.SECONDS);
            assertNotNull(result);
            assertTrue(result.length() > 0);
        } catch (Exception e){
            Log.e("EndpointsAsyncTaskTest", "test: Timed out");
        }
    }
}
