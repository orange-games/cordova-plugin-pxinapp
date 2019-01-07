package orangegames.plugin.cordova.pxInapp;

import fr.pixtel.pxinapp.PXInapp;
import fr.pixtel.pxinapp.PXInappProduct;


public class CordovaPluginPXInapp extends CordovaPlugin {
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if ("setup".equals(action)) {
            this.beep(args.getLong(0));
            callbackContext.success();
            return true;
        }
        return false;  // Returning false results in a "MethodNotFound" error.
    }

    private void setup(String pxId, Enum mode, Boolean testMode) {
        PXInapp.create(this, "A02482422916654648792726404931357531443A1360803" , PXInapp.UI_MODE_HYBRID, true);
    }
}
