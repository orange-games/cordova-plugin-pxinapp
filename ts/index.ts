class PXInApp {
    constructor() {
        if (!window.hasOwnProperty('plugins')) {
            (window as any).plugins = {};
        }

        (window as any).plugins.pxInapp = new PXInApp();
        (window as any).enums.UI_MODE = UIMode;
    }

    public setup(id: any, mode: UIMode, test: any): Promise<void> {
        var options: any = {};
        options.id = id;
        options.mode = mode;
        options.test = test || false;

        return new Promise((resolve, reject) => {
            cordova.exec(function (result) {
                resolve(result)
            }, function (error) {
                reject(error);
            }, 'PXInApp', 'setup', [options]);
        })
    }
}

new PXInApp();
