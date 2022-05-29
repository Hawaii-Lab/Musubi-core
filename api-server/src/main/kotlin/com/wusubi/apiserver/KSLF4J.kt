package com.wusubi.apiserver

import org.slf4j.Logger
import org.slf4j.LoggerFactory

abstract class KSLF4J {
    val log: Logger = LoggerFactory.getLogger(this.javaClass)
}
