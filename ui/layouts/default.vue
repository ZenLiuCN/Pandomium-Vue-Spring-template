<template>
    <v-app dark clock>
        <v-navigation-drawer
                :mini-variant.sync="miniVariant"
                :clipped="clipped"
                v-model="drawer"
                fixed
                app
        >
            <v-list>
                <v-list-tile
                        router
                        :to="item.to"
                        :key="i"
                        v-for="(item, i) in items"
                        exact
                >
                    <v-list-tile-action>
                        <v-icon v-html="item.icon"></v-icon>
                    </v-list-tile-action>
                    <v-list-tile-content>
                        <v-list-tile-title v-text="item.title"></v-list-tile-title>
                    </v-list-tile-content>
                </v-list-tile>
            </v-list>
        </v-navigation-drawer>
        <v-toolbar fixed app :clipped-left="true"
                   style="-webkit-user-select:none; -moz-user-select:none; -ms-user-select:none; user-select:none; ">
            <v-icon small class="mdi-rotate-180" style="margin-left: -1.5em;margin-top: -2.5em;cursor: move">
                mdi-resize-bottom-right
            </v-icon>
            <v-tooltip bottom>
                <v-toolbar-side-icon @click="drawer = !drawer" slot="activator"></v-toolbar-side-icon>
                <span>{{drawer?'隐藏':'显示'}}菜单</span>
            </v-tooltip>
            <v-tooltip v-if="drawer" bottom>
                <v-btn icon @click.stop="miniVariant = !miniVariant" slot="activator">
                    <v-icon v-html="miniVariant ? 'mdi-chevron-right' : 'mdi-chevron-left'"></v-icon>
                </v-btn>
                <span>{{miniVariant?'展开':'折叠'}}菜单</span>
            </v-tooltip>
            <v-toolbar-title v-text="title"></v-toolbar-title>
            <v-spacer></v-spacer>
            <v-spacer></v-spacer>
            <v-tooltip bottom>
                <v-icon style="cursor: move" slot="activator">mdi-arrow-all</v-icon>
                <span>移动窗口</span>
            </v-tooltip>

            <v-tooltip bottom>
                <v-btn small icon @click="minWin" slot="activator">
                    <v-icon small>mdi-window-minimize</v-icon>
                </v-btn>
                <span>最小化</span>
            </v-tooltip>
            <v-tooltip bottom>
                <v-btn small icon @click="maxWin" slot="activator">
                    <v-icon small>{{ismax?'mdi-window-restore':'mdi-window-maximize'}}</v-icon>
                </v-btn>
                <span>{{ismax?'还原':'最大化'}}</span>
            </v-tooltip>
            <v-tooltip bottom>
                <v-btn small icon @click="clsWin" slot="activator">
                    <v-icon small>mdi-window-close</v-icon>
                </v-btn>
                <span>关闭</span>
            </v-tooltip>
        </v-toolbar>
        <v-content>
            <v-container>
                <nuxt/>
            </v-container>
        </v-content>
        <v-footer :fixed="fixed" app
                  style="-webkit-user-select:none; -moz-user-select:none; -ms-user-select:none; user-select:none; padding-left: 2em">
            <span>&copy; 2017</span>
            <v-spacer></v-spacer>
        </v-footer>
    </v-app>
</template>

<script lang="ts">
    import {Component, Vue, Watch} from 'nuxt-property-decorator'

    @Component({
        name: 'defaultLayout',
    })
    export default class Default extends Vue {
        ismax: Boolean = false
        clipped = true
        drawer = true
        fixed = true
        items = [
            {icon: 'mdi-home', title: 'Welcome', to: '/'},
            {icon: 'mdi-apps', title: 'Inspire', to: '/inspire'},
        ]
        miniVariant = true
        title = 'JCEF'

        minWin() {
            this.$axios.get('http://127.0.0.1:65432/win/min')
        }

        maxWin() {
            if (this.ismax) {
                this.$axios.get('http://127.0.0.1:65432/win/reset')
                this.ismax = false
            } else {
                this.$axios.get('http://127.0.0.1:65432/win/max')
                this.ismax = true
            }

        }

        clsWin() {
            this.$axios.get('http://127.0.0.1:65432/win/close')
        }

        @Watch('miniVariant')
        autominVariant(status) {
            if (status === false) {
                setTimeout(() => {
                    this.miniVariant = true
                    console.log('miniVariant', this.miniVariant)
                }, 5000)
            }
        }

    }
</script>
