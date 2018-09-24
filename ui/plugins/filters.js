import { Vue } from '~/node_modules/nuxt-property-decorator';
import marked from 'marked';
import Prism from 'prismjs';
Vue.filter('marked', function (src) { return marked(src, {
    highlight: function (code, lang) {
        if (!lang)
            return code;
        return Prism.highlight(code, Prism.languages[lang] ? Prism.languages[lang] : Prism.languages['markup']);
    }
}); });
