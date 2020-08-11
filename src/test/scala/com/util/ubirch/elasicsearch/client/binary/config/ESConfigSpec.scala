package com.util.ubirch.elasicsearch.client.binary.config

import com.ubirch.util.elasticsearch.client.binary.config.{ESConfig, HostUri}
import org.scalatest.{FeatureSpec, Matchers}

/**
  * author: cvandrei
  * since: 2017-04-13
  */
class ESConfigSpec extends FeatureSpec
  with Matchers {

  feature("hosts()") {

    scenario("read list of hosts from config") {

      // test
      val hosts = ESConfig.hosts

      // verify
      val expected = Set(
        HostUri("localhost", 9300),
        HostUri("localhost", 9301)
      )
      hosts shouldBe expected

    }

  }

  feature("settings()") {

    scenario("read settings") {

      // test
      val settings = ESConfig.settings

      // verify
      settings("cluster.name") shouldBe "my-test-cluster"
      settings("xpack.security.user") shouldBe "transport_client_user:changeme"

    }

  }

}
