# Template project

## about
This is a template project base on
1. [nuxtjs](https://github.com/nuxt/nuxt.js) for web ui base framework
1. [vuetifyjs](https://github.com/vuetifyjs/vuetify) for web ui framework
1. [Pandomium](https://github.com/dzikoysk/Pandomium) for desktop cef environment
1. [Spring Boot](https://github.com/spring-projects/spring-boot) for inner server

also

1. [jooq](https://github.com/jOOQ/jOOQ) as SQL framework
1. [H2database](https://github.com/h2database/h2database) as default inner database

## what

This project meant to build a framework which base on java(kotlin) as self contained backend,with
CEF as frontend to display Vue based web gui.

## how

1. download [natives](https://pandomium.panda-lang.org/download/natives/)

1. go to `/ui` , run `yarn` or `npm install` to get ui depends

1. back to project root, `gradlew BuildWebUI` to build web files.
1. `gradlew SyncWebUI` to copy web files to spring project
1. `gradlew bootjar` to build SpringBoot bootjar
1. with bootjar**mustly under `/build/libs`** extract natives from step 1. into `/natives`
1. `javaw -jar XXXX.jar` while see what happend~
