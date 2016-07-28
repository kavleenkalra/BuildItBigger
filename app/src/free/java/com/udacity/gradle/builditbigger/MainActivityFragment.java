package com.udacity.gradle.builditbigger;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.android.myandroidlib.JokeTellingActivity;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

/**
 * Created by kakalra on 7/24/2016.
 */
public class MainActivityFragment extends Fragment implements OnJokeReceivedListener
{
    ProgressBar spinner;
    InterstitialAd interstitialAd;
    String myJoke;

    public MainActivityFragment()
    {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        interstitialAd=new InterstitialAd(getActivity());
        interstitialAd.setAdUnitId(getString(R.string.interstitial_ad_id));
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                startJokeActivity();
            }
        });

        requestNewInterstitial();

        Button button=(Button)root.findViewById(R.id.joke_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                fetchJoke();
            }
        });

        spinner=(ProgressBar)root.findViewById(R.id.progressBar);

        AdView mAdView = (AdView) root.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);
        return root;
    }

    private void requestNewInterstitial()
    {
        AdRequest adRequest=new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        interstitialAd.loadAd(adRequest);
    }

    @Override
    public void onJokeReceived(String joke)
    {
        spinner.setVisibility(View.INVISIBLE);
        myJoke=joke;
        if(interstitialAd!=null && interstitialAd.isLoaded())
            interstitialAd.show();
        else
        {
            startJokeActivity();
        }
    }

    public void startJokeActivity()
    {
        Intent intent=new Intent(getActivity(),JokeTellingActivity.class).putExtra(JokeTellingActivity.JOKE_INTENT,myJoke);
        startActivity(intent);
    }

    public void fetchJoke()
    {
        spinner.setVisibility(View.VISIBLE);
        new EndPointsAsyncTask().execute(this);
    }
}
