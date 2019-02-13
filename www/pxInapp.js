var UIMode;
(function (UIMode) {
    UIMode[UIMode["UI_MODE_HYBRID"] = 0] = "UI_MODE_HYBRID";
    UIMode[UIMode["UI_MODE_GAME"] = 1] = "UI_MODE_GAME";
    UIMode[UIMode["UI_MODE_SDK"] = 2] = "UI_MODE_SDK";
})(UIMode || (UIMode = {}));
var PXInApp = /** @class */ (function () {
    function PXInApp() {
        //
    }
    PXInApp.prototype.setup = function (id, mode, test) {
        return new Promise(function (resolve, reject) {
            cordova.exec(function (result) {
                resolve(result);
            }, function (error) {
                reject(error);
            }, 'PXInApp', 'setup', [{
                    id: id,
                    mode: mode,
                    test: test || false
                }]);
        });
    };
    PXInApp.prototype.fetchProduct = function (productId) {
        return new Promise(function (resolve, reject) {
            cordova.exec(function (result) {
                resolve(result);
            }, function (error) {
                reject(error);
            }, 'PXInApp', 'getProduct', [{
                    productId: productId
                }]);
        });
    };
    PXInApp.prototype.buyProduct = function (productId) {
        return new Promise(function (resolve, reject) {
            cordova.exec(function (result) {
                resolve(result);
            }, function (error) {
                reject(error);
            }, 'PXInApp', 'buyProduct', [{
                    productId: productId
                }]);
        });
    };
    return PXInApp;
}());
window.pxInApp = new PXInApp();
