# cordova-plugin-pxinapp
Cordova plugin for PXInApp SDK

## Supported platforms

Android only

## Installation

```bash
cordova plugin add cordova-plugin-pxinapp --save
```


## Usage


### Config and setup
Pixtel will supply a special bin config file that contain the products for your app. This bin file needs to be placed in the root folder of your cordova project. This plugin will automaticly place it in the correct folder of the resulting APK.

After that you can initialize with 3 parameters:
```javascript
window.pxInApp.setup(
    '321587236bv587936b50234go2w438h',  //The account ID you recveived from Pixtel
    2,                                              // The UI mode, 0 = game ui, 2 = sdk UI, 1 = hybrid
    true                                            //Boolean for test mode
 ).then((result) => {
    document.getElementById('data-box').innerHTML = result;
    console.log('Success!', result)
}).catch((e) => {
    document.getElementById('data-box').innerHTML = 'Error: ' + e;

    console.log('error!', e)
});
```
the setup function returns a Promise that will be resolved if the PXInapp library is correctly initialized

### List products

The API is very straight forward. You should already know the product ID's at forehand because they've been discussed with Pixtel before you got the bin config file.
After that you can just fetch the product like so:

```javascript
window.pxInApp.fetchProduct(1) //Fetch product with id 1
    .then(JSON.parse)
    .then(function (result) {
        document.getElementById('data-box').innerHTML = result;
        console.log('fetch success!');

        console.log(result);
    })
    .catch(function (error) {
        document.getElementById('data-box').innerHTML = 'Error: ' + error;
        console.log('fetch error!');

        console.log(error);
    });
```

On success, an object will be returned including, but not limited to, the following properties:
* price_amount  Formatted string amount
* description   Product description

### Make a purchase

The following function allows you to buy the specified product and will return a promise similar to fetchProduct

```javascript
window.pxInApp.buyProduct(1)
    .then(JSON.parse)
    .then(function (result) {
        document.getElementById('data-box').innerHTML = result;
        console.log('buy success!');
        console.log(result);
    })
    .catch(function (error) {
        document.getElementById('data-box').innerHTML = 'Error: ' + error;
        console.log('buy error!');

        console.log(error);
    });
```
