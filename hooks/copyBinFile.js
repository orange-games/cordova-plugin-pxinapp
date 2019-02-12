const fs = require('fs');
const path = require('path');
module.exports = function (context) {
    const projectPath = context.opts.projectRoot;

    const srcFileLocation = path.join(projectPath);
    const destFileLocation = path.join(projectPath, 'platforms/android/app/src/main/assets');

    fs.readdir(projectPath, (err, list) => {
        let found = false;
        list.forEach((fileName) => {
            if (fileName.indexOf('pxinapp') !== -1 && path.extname(fileName) === '.bin') {
                found = true;
                fs.createReadStream(path.join(srcFileLocation, fileName))
                    .pipe(fs.createWriteStream(path.join(destFileLocation, fileName)));
            }
        });

        if (!found) {
            console.warn('No pxinapp.bin file found in root of project!');
            process.exit(1);
        }
    });
};
