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

public class PXInApp extends CordovaPlugin implements PXInapp.PaymentCallback {
  @Override
  public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
    JSONObject options;
    try {
      options = args.getJSONObject(0);
    } catch (JSONException e) {
      callbackContext.error("Error encountered: " + e.getMessage());
      return false;
    }

    if ("setup".equals(action)) {
      int result = this.setup(options.getString("id"), options.getInt("mode"), options.getBoolean("test"));

      if (result == PXInapp.RESULT_SUCCESS) {
        PluginResult pluginResult = new PluginResult(PluginResult.Status.OK, result);
        callbackContext.sendPluginResult(pluginResult);
        return true;
      } else if (result == PXInapp.RESULT_FAILED) {
        PluginResult pluginResult = new PluginResult(PluginResult.Status.ERROR, "Failed to initialise PXINAPP: " + Integer.toString(result));
        callbackContext.sendPluginResult(pluginResult);
        return false;
      } else {
        PluginResult pluginResult = new PluginResult(PluginResult.Status.ERROR, result);
        callbackContext.sendPluginResult(pluginResult);
        return false;
      }
    } else if ("getProduct".equals(action)) {
      int productId = options.getInt("productId");
      String product = this.fetchProduct(productId);

      if (null == product) {
        PluginResult pluginResult = new PluginResult(PluginResult.Status.ERROR, "No product found for id: " + Integer.toString(productId));
        callbackContext.sendPluginResult(pluginResult);
        return false;
      }

      PluginResult pluginResult = new PluginResult(PluginResult.Status.OK, product);
      callbackContext.sendPluginResult(pluginResult);
      return true;
    }

    return false; // Returning false results in a "MethodNotFound" error.
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
    PXInapp.resume();
    ;
  }

  /**
   * Called when the activity will be destroyed.
   */
  @Override
  public void onDestroy() {
    super.onDestroy();
    PXInapp.destroy();
  }

  /**
   * Called when permissions have been accepted or rejected
   * 
   * @param requestCode
   * @param permissions
   * @param grantResults
   */
  @Override
  public void onRequestPermissionResult(int requestCode, String permissions[], int[] grantResults) {
    PXInapp.requestpermissionsresult();
  }

  private int setup(String pxId, int mode, Boolean testMode) {
    Context context = this.cordova.getActivity();

    int uiModeEnum = PXInapp.UI_MODE_HYBRID;
    switch (mode) {
    case 0:
    default:
      uiModeEnum = PXInapp.UI_MODE_HYBRID;
      break;
    case 1:
      uiModeEnum = PXInapp.UI_MODE_GAME;
      break;
    case 2:
      uiModeEnum = PXInapp.UI_MODE_SDK;
      break;
    }

    // int result = PXInapp.create(context, pxId, uiModeEnum, testMode);
    int result = PXInapp.create(context, "A024805525452923568167231278837943681DC0739", PXInapp.UI_MODE_SDK, true);
    PXInapp.setPaymentCallback(this);
    //This one is needed if the ui mode isn't (!!!)  UI_MODE_SDK
    // PXInapp.setProductDialogCallback(context);
    return result;
  }

  @Override
  public void onPayment( PXInappProduct product, int result ) {
    
  }

  private String fetchProduct(int productId) {
    PXInappProduct product = PXInapp.getInappProduct(productId);
    
    return product.priceString;
  }
}
