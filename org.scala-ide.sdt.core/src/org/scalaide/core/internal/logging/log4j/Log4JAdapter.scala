package org.scalaide.core.internal.logging.log4j

import org.scalaide.logging.Logger
import org.apache.log4j.{Logger => Log4JLogger}

private[log4j] object Log4JAdapter {
  def apply(name: String): Logger = {
    val logger = Log4JLogger.getLogger(name)
    new Log4JAdapter(logger)
  }
}

/** A thin wrapper around Log4J Logger. */
private class Log4JAdapter private (logger: Log4JLogger) extends Logger {

  // Mind that each method's implementation checks if the corresponding log's level
  // is enabled to avoid the cost of constructing the {{{message}}}. And that is the
  // reason for passing {{{message}}} by-name.

  def trace(message: => Any): Unit = {
    if(logger.isTraceEnabled) logger.trace(message)
  }

  def trace(message: => Any, t: Throwable): Unit = {
    if(logger.isTraceEnabled) logger.trace(message, t)
  }

  def info(message: => Any): Unit = {
    if(logger.isInfoEnabled) logger.info(message)
  }

  def info(message: => Any, t: Throwable): Unit = {
    if(logger.isInfoEnabled) logger.info(message, t)
  }

  def debug(message: => Any): Unit = {
    if(logger.isDebugEnabled) logger.debug(message)
  }

  def debug(message: => Any, t: Throwable): Unit = {
    if(logger.isDebugEnabled) logger.debug(message, t)
  }

  def warn(message: => Any): Unit = {
    if(logger.isEnabledFor(org.apache.log4j.Level.WARN)) logger.warn(message)
  }

  def warn(message: => Any, t: Throwable): Unit = {
    if(logger.isEnabledFor(org.apache.log4j.Level.WARN)) logger.warn(message, t)
  }

  def error(message: => Any): Unit = {
    if(logger.isEnabledFor(org.apache.log4j.Level.ERROR)) logger.error(message)
  }

  def error(message: => Any, t: Throwable): Unit = {
    if(logger.isEnabledFor(org.apache.log4j.Level.ERROR)) logger.error(message, t)
  }

  def fatal(message: => Any): Unit = {
    if(logger.isEnabledFor(org.apache.log4j.Level.FATAL)) logger.fatal(message)
  }

  def fatal(message: => Any, t: Throwable): Unit = {
    if(logger.isEnabledFor(org.apache.log4j.Level.FATAL)) logger.fatal(message, t)
  }
}
