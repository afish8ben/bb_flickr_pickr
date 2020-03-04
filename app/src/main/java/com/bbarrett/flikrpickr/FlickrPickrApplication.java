package com.bbarrett.flikrpickr;

import android.app.Application;

import timber.log.Timber;

/**
 * @author barrett on 3/2/20
 */
public class FlickrPickrApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        
        Timber.plant(new Timber.DebugTree());
    }

}
