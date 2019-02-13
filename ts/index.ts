class PXInApp {
    constructor() {
        //
    }

    public setup(id: any, mode: UIMode, test: any): Promise<void> {
        return new Promise((resolve, reject) => {
            cordova.exec(function (result) {
                resolve(result)
            }, function (error) {
                reject(error);
            }, 'PXInApp', 'setup', [{
                id: id,
                mode: mode,
                test: test || false
            }]);
        })
    }

    public fetchProduct(productId: number): Promise<any> {
        return new Promise((resolve, reject) => {
            cordova.exec(function (result) {
                resolve(result)
            }, function (error) {
                reject(error);
            }, 'PXInApp', 'getProduct', [{
                productId: productId
            }]);
        })
    }

    public buyProduct(productId: number): Promise<any> {
        return new Promise((resolve, reject) => {
            cordova.exec(function (result) {
                resolve(result)
            }, function (error) {
                reject(error);
            }, 'PXInApp', 'buyProduct', [{
                productId: productId
            }]);
        });
    }
}

(window as any).pxInApp = new PXInApp();
