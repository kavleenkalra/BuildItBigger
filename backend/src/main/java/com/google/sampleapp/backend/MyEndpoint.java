/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.google.sampleapp.backend;

import com.example.JokeSupply;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;


/** An endpoint class we are exposing */
@Api(
  name = "myApi",
  version = "v1",
  namespace = @ApiNamespace(
    ownerDomain = "backend.sampleapp.google.com",
    ownerName = "backend.sampleapp.google.com",
    packagePath=""
  )
)
public class MyEndpoint {

    /** A simple endpoint method that tells a joke */
    @ApiMethod(name="tellAJoke")
    public MyBean tellAJoke()
    {
        MyBean response = new MyBean();
        JokeSupply jokeObj=new JokeSupply();
        response.setData(jokeObj.getAJoke());
        return response;
    }
}
