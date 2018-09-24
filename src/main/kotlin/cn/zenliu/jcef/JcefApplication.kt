package cn.zenliu.jcef

import org.panda_lang.pandomium.Pandomium
import org.panda_lang.pandomium.settings.PandomiumSettings
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary
import org.springframework.http.HttpMethod
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.toMono
import java.awt.*
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import java.lang.Thread.sleep
import javax.swing.JFrame
import javax.swing.WindowConstants
import kotlin.concurrent.thread
import kotlin.math.abs


@SpringBootApplication
class JcefApplication : JFrame() {
    private val client by lazy {
        Pandomium(PandomiumSettings.getDefaultSettings().apply {
            natives.nativeDirectory = "natives"
            println(natives.nativeDirectory)
        }).let {
            it.initialize()
            it.createClient()
        }
    }
    private lateinit var clientCom: Component
    private val mouseAdapter = object : MouseAdapter() {
        private val win = this@JcefApplication
        private var top = false
        private var down = false
        private var right = false
        private var left = false
        private var drag = false
        private val draggingAnchor: Point = Point(0, 0)
        override fun mousePressed(e: MouseEvent) {
            when {
                (        e.y < 48
                         && (this@JcefApplication.width - 143)
                             .let { (it - 12..it + 12) }
                             .contains(e.x)
                )||(
                   e.y<15 && e.x<15
                   )
                -> {
                    draggingAnchor.x = e.x
                    draggingAnchor.y = e.y
                    drag = true
                }
            }
        }

        override fun mouseReleased(e: MouseEvent) {
            if (drag) drag = false
        }

        override fun mouseDragged(e: MouseEvent) {
            when {
                drag -> {
                    val p = win.getLocation()
                    sleep(2L)
                    win.setLocation(p.x + e.x - draggingAnchor.x, p.y + e.y - draggingAnchor.y)
                }
                top -> {
                    win.setSize(win.width, win.height - e.y);
                    win.setLocation(win.locationOnScreen.x, win.locationOnScreen.y + e.y)
                }
                down -> {
                    win.setSize(win.width, e.y)
                }
                left -> {
                    win.setSize(win.width - e.x, win.height)
                    win.setLocation(win.locationOnScreen.x + e.x, win.locationOnScreen.y)
                }
                right -> {
                    win.setSize(e.x, win.height)
                }
            }
        }

        override fun mouseMoved(e: MouseEvent) {
            when {
                (0..2).contains(e.point.y) -> {
                    win.cursor = Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR)
                    top = true
                    down = false
                    left = false
                    right = false
                }
                abs(e.y - win.height) <= 2 -> {
                    win.cursor = Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR)
                    down = true
                    top = false
                    left = false
                    right = false

                }
                (0..2).contains(e.y) -> {
                    win.cursor = Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR)
                    left = true
                    top = false
                    down = false
                    right = false
                }
                abs(e.x - win.width) <= 2 -> {
                    win.cursor = Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR)
                    right = true
                    top = false
                    down = false
                    left = false
                }
                win.cursor != Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR) -> {
                    win.cursor = Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR)
                    top = false
                    down = false
                    left = false
                    right = false
                }
            }

        }
    }

    init {
        this.setSize(1024  , 786)
        this.setLocationRelativeTo(owner)
        this.isUndecorated = true
        this.clientCom = client.loadURL(url).toAWTComponent()
        clientCom.addMouseListener(mouseAdapter)
        clientCom.addMouseMotionListener(mouseAdapter)
        this.contentPane.add(clientCom)
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE)
        this.title = "JCEF EXAMPLE"
        this.iconImage = this.toolkit.getImage(javaClass.getResource("/static/v.png"))
        this.addWindowListener(object : WindowAdapter() {
            override fun windowClosing(p0: WindowEvent) {
                ctx?.close()
                p0.window.dispose()
                super.windowClosing(p0)
                this@JcefApplication.dispose()
            }
        })
    }

    @Bean
    @Primary
    fun jframe(): JFrame = this

    companion object {
//            val  url="http://127.0.0.1:65432/index.html"
        val url = "http://127.0.0.1:3000"
    }
}

@RestController
@RequestMapping("win")
class WinControl(val jFrame: JFrame) {
    var h: Int = jFrame.height
    var w: Int = jFrame.width
    /**
     * call win/max to max window
     */
    @GetMapping("max")
    fun max() {
        h = jFrame.height
        w = jFrame.width
        jFrame.extendedState = Frame.MAXIMIZED_BOTH

    }

    @GetMapping("min")
    fun min() {
        jFrame.extendedState = Frame.ICONIFIED
    }

    @GetMapping("reset")
    fun restore() {
        jFrame.setSize(w, h)
        jFrame.setLocationRelativeTo(jFrame.owner)
    }

    @GetMapping("close")
    fun close() {
        jFrame.dispose()
        thread(true) {
            sleep(500)
            ctx!!.close()
        }
    }
}
/*
@RestController
@RequestMapping("query")
class QueryController(val client: WebClient) {
    fun doQuery(@RequestBody query: Query) = query.toMono()
        .map {
            client.method(it.method)
        }.map {
            it
                .uri(query.url)
                .headers { query.header.forEach(it::add) }
                .body(query.body.toMono(), Any::class.java)
                .cookies { query.cookies.forEach(it::add) }
                .exchange()
        }.flatMap { it }
}*/

data class Query(
    var url: String = "",
    var method: HttpMethod = HttpMethod.GET,
    var header: Map<String, String> = mapOf(),
    var cookies: Map<String, String> = mapOf(),
    var body: Any = ""
)

var ctx: ConfigurableApplicationContext? = null
fun main(args: Array<String>) {
    System.setProperty("java.awt.headless", "false")
    ctx = SpringApplication.run(JcefApplication::class.java, *args)
    EventQueue.invokeLater {
        ctx!!.getBean(JcefApplication::class.java).isVisible = true
    }
}
