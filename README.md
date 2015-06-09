# sbt-jscs [![Circle CI](https://circleci.com/gh/hindsightsoftware/sbt-jscs/tree/master.svg?style=svg)](https://circleci.com/gh/hindsightsoftware/sbt-jscs/tree/master)  [ ![Download](https://api.bintray.com/packages/hindsightsoftware/sbt-plugins/sbt-jscs/images/download.svg) ](https://bintray.com/hindsightsoftware/sbt-plugins/sbt-jscs/_latestVersion)

A SBT plugin for enforcing your JavaScript style guide using the code style linter [JSCS](http://jscs.info). Implemented as a
[sbt-web](https://github.com/sbt/sbt-web) plugin and is aware of the asset pipeline and uses the default
location of assets.

To use this plugin use the addSbtPlugin command within your project's `plugins.sbt` file:

    resolvers += Resolver.url("hindsightsoftware-sbt-plugin-releases",
          url("http://dl.bintray.com/hindsightsoftware/sbt-plugins"))(
          Resolver.ivyStylePatterns)

    addSbtPlugin("com.hindsightsoftware.sbt" % "sbt-jscs" % "version")


Your project's build file also needs to enable sbt-web plugins. For example with build.sbt:

    lazy val root = (project in file(".")).enablePlugins(SbtWeb)
    
By default linting occurs as part of your project's `jscs` task. Both `src/main/assets/**/*.js` and `src/test/assets/**/*.js` 
sources are linted.

Linting options are controlled by JSCS `.jscsrc` configuration file located in the project root. See [JSCS Overview](http://jscs.info/overview.html)
for all available configuration options. An example `.jscsrc` configuration file to support Facebooks React JS would look like

    {
        "esprima" : "esprima-fb",
        "fileExtensions": [".js", ".jsx"]
    }


&copy; [Hindsight Software Ltd](http://hindsightsoftware.com), 2015