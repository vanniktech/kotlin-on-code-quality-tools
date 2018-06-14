package com.vanniktech.kotlinoncodequalitytools

interface Configuration {
  /**
   * Returns a [Float] with the given [name] from the configuration
   */
  fun getFloat(name: String): Float
}
