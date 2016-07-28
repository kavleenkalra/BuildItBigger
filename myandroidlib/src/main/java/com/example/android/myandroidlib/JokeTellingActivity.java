package com.example.android.myandroidlib;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokeTellingActivity extends AppCompatActivity
{
    public static String JOKE_INTENT="INTENT_JOKE";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_telling);
        Intent intent=getIntent();
        String joke=intent.getStringExtra(JOKE_INTENT);

        TextView jokeTextView=(TextView)findViewById(R.id.joke_display_textview);
        if(jokeTextView!=null)
            jokeTextView.setText(joke);

        if(getSupportActionBar()!=null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
