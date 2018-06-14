package com.vanniktech.kotlinoncodequalitytools

class Foo {
  fun foo(map: Array<IntArray>) {
    for (i in 0 until map.size) {
      for (j in 0 until map[i].size) {
        map[i][i] += 1
      }
    }
  }
}
