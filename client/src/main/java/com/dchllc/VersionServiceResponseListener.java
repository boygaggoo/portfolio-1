package com.dchllc;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import org.json.JSONObject;

public class VersionServiceResponseListener extends ServiceResponseListener {

    public VersionServiceResponseListener(SplashScreen splashScreen) {
        super(splashScreen);
    }

    @Override
    public void onResponse(JSONObject response) {
        Log.i(getTag(), "Received " + response.toString());

        String version = getString(response, "version");
        if (version.equals(PortfolioService.API_VERSION)) {
            context.startActivity(new Intent(context, HelloAndroidActivity.class));
            context.finish();
            return;
        }

        new AlertDialog.Builder(context)
                .setTitle(R.string.outdated_title)
                .setMessage(R.string.outdated_message)
                .setCancelable(false)
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        context.finish();
                    }
                })
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + context.getPackageName())));
                    }
                })
                .show();
    }

}
