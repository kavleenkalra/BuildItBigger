package com.udacity.gradle.builditbigger;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.android.myandroidlib.JokeTellingActivity;

/**
 * Created by kakalra on 7/24/2016.
 */
public class MainActivityFragment extends Fragment implements OnJokeReceivedListener
{
    ProgressBar spinner;

    public MainActivityFragment()
    {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        Button button=(Button)root.findViewById(R.id.joke_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                startJokeActivity();
            }
        });
        spinner=(ProgressBar)root.findViewById(R.id.progressBar);
        return root;
    }

    @Override
    public void onJokeReceived(String joke)
    {
        spinner.setVisibility(View.INVISIBLE);
        Intent intent=new Intent(getActivity(),JokeTellingActivity.class).putExtra(JokeTellingActivity.JOKE_INTENT,joke);
        startActivity(intent);
    }

    public void startJokeActivity()
    {
        spinner.setVisibility(View.VISIBLE);
        new EndPointsAsyncTask().execute(this);
    }
}
