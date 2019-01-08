var UIMode;
(function (UIMode) {
    UIMode[UIMode["UI_MODE_HYBRID"] = 0] = "UI_MODE_HYBRID";
    UIMode[UIMode["UI_MODE_GAME"] = 1] = "UI_MODE_GAME";
    UIMode[UIMode["UI_MODE_SDK"] = 2] = "UI_MODE_SDK";
})(UIMode || (UIMode = {}));
var PXInApp = /** @class */ (function () {
    function PXInApp() {
        if (!window.hasOwnProperty('plugins')) {
            window.plugins = {};
        }
        window.plugins.pxInapp = new PXInApp();
    }
    PXInApp.prototype.setup = function (id, mode, test) {
        var options = {};
        options.id = id;
        options.mode = mode;
        options.test = test || false;
        return new Promise(function (resolve, reject) {
            cordova.exec(function (result) {
                resolve(result);
            }, function (error) {
                reject(error);
            }, 'PXInApp', 'setup', [options]);
        });
    };
    return PXInApp;
}());
new PXInApp();
