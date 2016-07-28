package com.example;

import java.util.Random;

public class JokeSupply
{
    private String jokes[];
    private Random rand;

    public JokeSupply()
    {
        jokes=new String[5];
        jokes[0]="Why do Java developers wear glasses? Because they can't C#";
        jokes[1]="There are 10 types of people in the world: those who understand binary, and those who don't.";
        jokes[2]="How many programmers does it take to change a light bulb? None. It's a hardware problem.";
        jokes[3]="A SEO couple had twins. For the first time they were happy with duplicate content.";
        jokes[4]="Why was the JavaScript developer sad? Because he didn't Node how to Express himself";
        rand=new Random();
    }

    public String getAJoke()
    {
        int n=rand.nextInt(jokes.length);
        return jokes[n];
    }
}
