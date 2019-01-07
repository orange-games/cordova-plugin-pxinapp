function PXInApp() {};

PXInApp.prototype.setup = function setup(id, mode, test) {
    var options = {};
    options.id = id;
    options.mode = mode;
    options.test = test || false;
    cordova.exec(function () {
        console.log('success', arguments);
    }, function (error) {
        console.log('error', error)
    }, 'PXInApp', 'setup', [options]);
};

PXInApp.install = function install() {
    if (!window.plugins) {
        window.plugins = {};
    }
    window.plugins.pxInapp = new PXInApp();

    return window.plugins.pxInapp;
};

cordova.addConstructor(PXInApp.install);
