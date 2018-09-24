module.exports = function () {
  // Add .ts extension for store, middleware and more
  this.nuxt.options.extensions.push('styl')
  // Extend build
  this.extendBuild(config => {
    const stylusloader = {
      loader: 'stylus-loader',
      options: {
        appendTsSuffixTo: [/\.styl$/],
        sourceMap: false
      }
    }
    // Add TypeScript loader
    config.module.rules.push(
      Object.assign(
        {
          test: /\.styl$/
        },
        stylusloader
      )
    )
    // Add TypeScript loader for vue files
    for (let rule of config.module.rules) {
      if (rule.loader === 'vue-loader') {
        rule.options.loaders.styl = stylusloader
      }
    }
    // Add .ts extension in webpack resolve
    if (
      config.resolve.extensions.indexOf('.styl') ===
      -1
    ) {
      config.resolve.extensions.push('.styl')
    }
  })
}
