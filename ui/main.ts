/*
import {app, BrowserWindow} from 'electron'
import {Builder, Nuxt} from 'nuxt'
import installExtension, {VUEJS_DEVTOOLS} from 'electron-devtools-installer'
// import {enableLiveReload} from 'electron-compile'
import * as http from 'http'

let config=require('./nuxt.config')
const isDevMode = config.dev
let window: Electron.BrowserWindow
const createWindow = async () => {
    window = new BrowserWindow({
        darkTheme: true,
        width: 1024,
        height: 768,
    })

    if (isDevMode) {
        // enableLiveReload()

        const nuxt=new Nuxt(config)
        const builder=new Builder(nuxt)
        const server=http.createServer(nuxt.render)
        builder.build().catch(err => {
            console.error(err) // eslint-disable-line no-console
            process.exit(1)
        })
        server.listen()
        const _NUXT_URL=`http://${server.address()}`

        await installExtension(VUEJS_DEVTOOLS)
        const pollServer = () => {
            http.get(_NUXT_URL, (res) => {
                if (res.statusCode === 200) { window.loadURL(_NUXT_URL) } else { setTimeout(pollServer, 300) }
            }).on('error', pollServer)
        }
        pollServer()
        window.webContents.openDevTools({mode: 'bottom'})
    }else{
        window.loadURL(`file://${__dirname}/index.html`)
    }

    window.on('closed', () => {
        window = null
    })
}

app.on('ready', createWindow)

app.on('window-all-closed', () => {
    // On macOS it is common for applications and their menu bar
    // to stay active until the user quits explicitly with Cmd + Q
    if (process.platform !== 'darwin') {
        app.quit()
    }
})

app.on('activate', () => {
    // On macOS it's common to re-create a window in the app when the
    // dock icon is clicked and there are no other windows open.
    if (window === null) {
        createWindow()
    }
})
*/
const http=require('http')
const {Builder, Nuxt} =require('nuxt')

let config = require('./nuxt.config.js')
config.rootDir = __dirname // for electron-builder
// Init Nuxt.js
const nuxt = new Nuxt(config)
const builder = new Builder(nuxt)
const server = http.createServer(nuxt.render)

const isDev = config.dev ? true : process.env.NODE_ENV === 'DEV' ? true : false
if (isDev) {
    builder.build().catch(err => {
        console.error(err) // eslint-disable-line no-console
        process.exit(1)
    })
}
// Listen the server
server.listen()
const _NUXT_URL_ = `http://localhost:${server.address().port}`
console.log(`Nuxt working on ${_NUXT_URL_}`)
console.log(server.address(), isDev)

/*
** Electron
*/
let win = null // Current window
const electron = require('electron')
const path = require('path')
const app = electron.app
const newWin = () => {
    let win = new electron.BrowserWindow({
        icon: path.join(__dirname, 'static/v.png'),
    })
    // win.maximize()
    win.on('closed', () => win = null)
    if (isDev) {
        // Install vue dev tool and open chrome dev tools
        const {default: installExtension, VUEJS_DEVTOOLS} = require('electron-devtools-installer')
        installExtension(VUEJS_DEVTOOLS.id).then(name => {
            console.log(`Added Extension:  ${name}`)
            win.webContents.openDevTools()
        }).catch(err => console.log('An error occurred: ', err))
        // Wait for nuxt to build
        const pollServer = () => {
            http.get(_NUXT_URL_, (res) => {
                if (res.statusCode === 200) {
                    win.loadURL(_NUXT_URL_)
                } else {
                    setTimeout(pollServer, 300)
                }
            }).on('error', pollServer)
        }
        pollServer()
    } else {
        return win.loadURL(_NUXT_URL_)
    }
}
app.on('ready', newWin)
app.on('window-all-closed', () => app.quit())
app.on('activate', () => win === null && newWin())
