import {Vue} from '~/node_modules/nuxt-property-decorator'
import marked from 'marked'
import Prism from 'prismjs'

Vue.filter('marked',(src:string)=>marked(src,{
    highlight:(code, lang) => {
        if(!lang) return code
        return Prism.highlight(code,Prism.languages[lang]?Prism.languages[lang]:Prism.languages['markup'])
    }
}))
