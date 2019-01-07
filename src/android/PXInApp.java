package com.orangegames.cordova.plugin;

// SFR's PXInApp native SDK
import fr.pixtel.pxinapp.PXInapp;
import fr.pixtel.pxinapp.PXInappProduct;
import android.content.Context;

// Cordova-required packages
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PXInApp extends CordovaPlugin {
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) {
          try {
            JSONObject options = args.getJSONObject(0);
          } catch (JSONException e) {
            callbackContext.error("Error encountered: " + e.getMessage());
            return false;
          }

        if ("setup".equals(action)) {
            PluginResult pluginResult = new PluginResult(PluginResult.Status.OK);
            callbackContext.sendPluginResult(pluginResult);
            return true;
        }

        return false;  // Returning false results in a "MethodNotFound" error.
    }

    private void setup(String pxId, Enum mode, Boolean testMode) {
         Context context = this.cordova.getActivity().getApplicationContext();

         int result = PXInapp.create(context, "A02482422916654648792726404931357531443A1360803" , PXInapp.UI_MODE_HYBRID, true);
    }
}
