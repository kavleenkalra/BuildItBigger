package com.udacity.gradle.builditbigger;

import android.app.Application;
import android.test.ApplicationTestCase;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by kakalra on 7/23/2016.
 */
public class JokeTestCase extends ApplicationTestCase<Application> implements OnJokeReceivedListener
{
    String joke;
    CountDownLatch mSignal;

    public JokeTestCase()
    {
        super(Application.class);
    }

    public void testJokeRetrieval()
    {
        try
        {
            mSignal=new CountDownLatch(1);
            new EndPointsAsyncTask().execute(this);
            mSignal.await(10,TimeUnit.SECONDS);
            assertTrue(joke!=null && joke.length()>0);
            assertNotNull("Joke is not null",joke);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
            fail("The joke retrieval encountered an exception");
        }
    }

    @Override
    public void onJokeReceived(String joke)
    {
        this.joke=joke;
        mSignal.countDown();
    }
}
