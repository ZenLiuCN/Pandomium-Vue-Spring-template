var http = require('http');
var _a = require('nuxt'), Builder = _a.Builder, Nuxt = _a.Nuxt;
var config = require('./nuxt.config.js');
config.rootDir = __dirname;
var nuxt = new Nuxt(config);
var builder = new Builder(nuxt);
var server = http.createServer(nuxt.render);
var isDev = config.dev ? true : process.env.NODE_ENV === 'DEV' ? true : false;
if (isDev) {
    builder.build().catch(function (err) {
        console.error(err);
        process.exit(1);
    });
}
server.listen();
var _NUXT_URL_ = "http://localhost:" + server.address().port;
console.log("Nuxt working on " + _NUXT_URL_);
console.log(server.address(), isDev);
var win = null;
var electron = require('electron');
var path = require('path');
var app = electron.app;
var newWin = function () {
    var win = new electron.BrowserWindow({
        icon: path.join(__dirname, 'static/v.png'),
    });
    win.on('closed', function () { return win = null; });
    if (isDev) {
        var _a = require('electron-devtools-installer'), installExtension = _a.default, VUEJS_DEVTOOLS = _a.VUEJS_DEVTOOLS;
        installExtension(VUEJS_DEVTOOLS.id).then(function (name) {
            console.log("Added Extension:  " + name);
            win.webContents.openDevTools();
        }).catch(function (err) { return console.log('An error occurred: ', err); });
        var pollServer_1 = function () {
            http.get(_NUXT_URL_, function (res) {
                if (res.statusCode === 200) {
                    win.loadURL(_NUXT_URL_);
                }
                else {
                    setTimeout(pollServer_1, 300);
                }
            }).on('error', pollServer_1);
        };
        pollServer_1();
    }
    else {
        return win.loadURL(_NUXT_URL_);
    }
};
app.on('ready', newWin);
app.on('window-all-closed', function () { return app.quit(); });
app.on('activate', function () { return win === null && newWin(); });
