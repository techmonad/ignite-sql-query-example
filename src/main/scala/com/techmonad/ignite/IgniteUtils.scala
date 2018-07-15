package com.techmonad.ignite

import org.apache.ignite.{Ignite, Ignition}

object IgniteUtils {

  val CONFIG = "config/example-ignite.xml"
  val CACHE_NAME = "testCache"

  def setupServerAndData: Ignite = {
    //Starting Ignite.
    Ignition.setClientMode(true)
    Ignition.start(CONFIG)
  }


}
