package com.dchllc;

import android.app.Activity;
import android.os.Bundle;

public class SplashScreen extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        PortfolioService.getInstance(this).getJsonObject("version", null, new VersionServiceResponseListener(this));
    }
}
