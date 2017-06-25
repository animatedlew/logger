package com.animatedlew

import com.typesafe.scalalogging._

trait LazyLogging { val logger = Logger[LazyLogging] }

object Main extends App with LazyLogging {
    logger.info("This is an info log.")
    logger.debug("This is a debug log.")
    logger.error("This is an error log.")
    println("End of logs: see /var/log/cota/helix/debug.log")
}