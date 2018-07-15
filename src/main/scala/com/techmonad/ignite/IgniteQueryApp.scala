package com.techmonad.ignite

import java.util

import org.apache.ignite.cache.query.SqlFieldsQuery

import scala.collection.JavaConversions._
import scala.util.control.NonFatal

object IgniteQueryApp extends App {

  val ignite = IgniteUtils.setupServerAndData


  while (true) {
    try {
      Thread.sleep(10)
      val cache = ignite.cache[Any, Any](IgniteUtils.CACHE_NAME)
      //Reading saved data from Ignite.

      val total: util.List[util.List[_]] = cache.query(new SqlFieldsQuery(s"SELECT COUNT(*) FROM sensor")).getAll
      total.foreach { row ⇒ println(" total count = " + row.mkString(",")) }
      val oneSecondWindow = System.currentTimeMillis() - 1000L
      println("query time =  " + oneSecondWindow)
      val data = cache.query(new SqlFieldsQuery(s"SELECT id, temperature FROM sensor where time  > $oneSecondWindow")).getAll
      data.foreach { row ⇒ println(row.mkString("[", ", ", "]")) }
      println("total rows " + data.length)
    } catch {
      case NonFatal(th) =>
        th.printStackTrace()
        println("hug.......")
    }
  }


}
