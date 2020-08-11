
/*
 * BASIC INFORMATION
 ********************************************************/

name := "ubirch-elasticsearch-binary-utils"
version := "3.3.2"
description := "Elasticsearch client using the binary TransportClient"
organization := "com.ubirch.util"
homepage := Some(url("http://ubirch.com"))
scalaVersion := "2.11.12"
scalacOptions ++= Seq(
  "-feature"
)
scmInfo := Some(ScmInfo(
  url("https://github.com/ubirch/ubirch-elasticsearch-binary-utils"),
  "https://github.com/ubirch/ubirch-elasticsearch-binary-utils.git"
))

/*
 * CREDENTIALS
 ********************************************************/

(sys.env.get("CLOUDREPO_USER"), sys.env.get("CLOUDREPO_PW")) match {
  case (Some(username), Some(password)) =>
    println("USERNAME and/or PASSWORD found.")
    credentials += Credentials("ubirch.mycloudrepo.io", "ubirch.mycloudrepo.io", username, password)
  case _ =>
    println("USERNAME and/or PASSWORD is taken from /.sbt/.credentials")
    credentials += Credentials(Path.userHome / ".sbt" / ".credentials")
}


/*
 * RESOLVER
 ********************************************************/

val resolverUbirchUtils = "cloudrepo.io" at "https://ubirch.mycloudrepo.io/repositories/ubirch-utils-mvn"
val resolverElasticsearch = "elasticsearch-releases" at "https://artifacts.elastic.co/maven"

resolvers ++= Seq(
  Resolver.sonatypeRepo("releases"),
  Resolver.sonatypeRepo("snapshots"),
  resolverUbirchUtils,
  resolverElasticsearch)


/*
 * PUBLISHING
 ********************************************************/


publishTo := Some(resolverUbirchUtils)
publishMavenStyle := true


/*
 * DEPENDENCIES
 ********************************************************/


//version
val elasticsearchV = "6.8.10"
val json4sV = "3.6.0"

//groups
val ubirchUtilGroup = "com.ubirch.util"

// Ubirch dependencies
val ubirchUtilConfig = ubirchUtilGroup %% "ubirch-config-utils" % "0.2.4"
val ubirchUtilDeepCheckModel = ubirchUtilGroup %% "ubirch-deep-check-utils" % "0.4.1"
val ubirchUtilJson = ubirchUtilGroup %% "ubirch-json-utils" % "0.5.2"
val ubirchUtilUuid = ubirchUtilGroup %% "ubirch-uuid-utils" % "0.1.4"

// External dependencies
val scalaTest = "org.scalatest" %% "scalatest" % "3.0.5"
val elasticSearch = "org.elasticsearch" % "elasticsearch" % elasticsearchV
val elasticSearchTransport = "org.elasticsearch.client" % "transport" % elasticsearchV
val elasticsearchXPack = "org.elasticsearch.client" % "x-pack-transport" % elasticsearchV
val luceneCore = "org.apache.lucene" % "lucene-core" % "7.7.1"
val json4sJackson = "org.json4s" %% "json4s-jackson" % json4sV
val json4sCore = "org.json4s" %% "json4s-core" % json4sV
val json4sExt = "org.json4s" %% "json4s-ext" % json4sV
//logging
val scalaLogging = "com.typesafe.scala-logging" %% "scala-logging" % "3.7.2"
val slf4j = "org.slf4j" % "slf4j-api" % "1.7.21"
val logbackClassic = "ch.qos.logback" % "logback-classic" % "1.1.7"
val log4jApi = "org.apache.logging.log4j" % "log4j-api" % "2.8.2"
val log4jCore = "org.apache.logging.log4j" % "log4j-core" % "2.8.2"
val log4jToSlf4j = "org.apache.logging.log4j" % "log4j-to-slf4j" % "2.7"

val json4sBase = Seq(
  json4sCore,
  json4sJackson,
  json4sExt
)

lazy val logging = Seq(
  scalaLogging,
  slf4j,
  logbackClassic
)
lazy val depLog4jToSlf4j = Seq(
  log4jApi,
  log4jToSlf4j
)


libraryDependencies ++= Seq(
  elasticSearch,
  elasticSearchTransport,
  elasticsearchXPack,
  luceneCore,
  ubirchUtilConfig,
  ubirchUtilDeepCheckModel,
  ubirchUtilJson,
  ubirchUtilUuid,
  scalaTest % "test"
) ++ json4sBase ++ logging ++ depLog4jToSlf4j