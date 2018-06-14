package com.vanniktech.kotlinoncodequalitytools

class BrilliantClass  ( val bar: Bar,
 val number: Int) {

      class Bar(

     private val value : Int
      )

  override fun toString( ) : String =  "BrilliantClass(bar=$bar)"
}
