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
           int result = this.setup(
             options.getString("id"), 
             options.getInt("mode"), 
             options.getBoolean("test"));

            if (result == PXInapp.RESULT_SUCCESS) {
              PluginResult pluginResult = new PluginResult(PluginResult.Status.OK);
              callbackContext.sendPluginResult(pluginResult);
              return true;
            } 
        }

        return false;  // Returning false results in a "MethodNotFound" error.
    }
    
    /**
     * Called when the system is about to start resuming a previous activity.
     *
     * @param multitasking Flag indicating if multitasking is turned on for app.
     */
    @Override
    public void onPause(boolean multitasking) {
        super.onPause(multitasking);
        PXInapp.pause();
    }

    /**
     * Called when the activity will start interacting with the user.
     *
     * @param multitasking Flag indicating if multitasking is turned on for app.
     */
    @Override
    public void onResume(boolean multitasking) {
        super.onResume(multitasking);
        PXInapp.resume();;
    }

    /**
     * Called when the activity will be destroyed.
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        PXInapp.destroy();
    }

    @Override
    public void onRequestPermissionsResult( int requestCode, String permissions[], int[] grantResults ) {
        PXInapp.requestpermissionsresult();
    }

    private int setup(String pxId, Enum mode, Boolean testMode) {
         Context context = this.cordova.getActivity().getApplicationContext();

         Enum uiModeEnum;
        switch (mode) {
          case 0:
            uiModeEnum = PXInapp.UI_MODE_HYBRID;
            break;
          case 1:
            uiModeEnum = PXInapp.UI_MODE_GAME;
            break;
          case 2:
            uiModeEnum = PXInapp.UI_MODE_SDK;
            break;
        }


         return PXInapp.create(context, pxId , uiModeEnum, testMode);
    }
}
