package com.dchllc;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class ServiceResponseListener implements Response.Listener<JSONObject>, Response.ErrorListener {
    protected final Activity context;

    public ServiceResponseListener(Activity context) {
        this.context = context;
    }

    protected final String getTag() {
        return getClass().getSimpleName();
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.e(getTag(), "Error encountered", error);
        showDialogAndExit(R.string.error_response_title, R.string.error_response_message);
    }

    protected String getString(JSONObject response, String key) {
        try {
            return response.getString(key);
        } catch (JSONException e) {
            Log.e(getTag(), "Error encountered getting '" + key + "' from " + response, e);
            showDialogAndExit(R.string.error_response_title, R.string.error_response_message);
            return null;
        }
    }

    protected void showDialogAndExit(int title, int message) {
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        context.finish();
                    }
                })
                .show();
    }
}
