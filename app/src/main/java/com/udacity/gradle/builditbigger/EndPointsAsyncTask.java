package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.google.sampleapp.backend.myApi.MyApi;

import java.io.IOException;

/**
 * Created by kakalra on 7/20/2016.
 */
public class EndPointsAsyncTask extends AsyncTask<OnJokeReceivedListener,Void,String>
{
    private static MyApi myApiService=null;
    private OnJokeReceivedListener listener;


    @Override
    protected String doInBackground(OnJokeReceivedListener... params)
    {
        if (myApiService==null)
        {
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl("https://builditbigger-1378.appspot.com/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });

            myApiService = builder.build();
        }

        listener=params[0];

        try
        {
            return myApiService.tellAJoke().execute().getData();
        }
        catch (IOException e)
        {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result)
    {
        listener.onJokeReceived(result);
    }
}
