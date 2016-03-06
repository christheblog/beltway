package org.cc.problem.beltway

import org.junit._

class RotateProblemTest {

  import BeltwayGenerator._
  
  @Test
  def rotateWhenFirst() {
    val distances = List(1,4,3,5,2)
    assert(rotate(distances)==distances)
  }

  @Test
  def rotateWhenLast() {
    val distances = List(4,3,5,2,1)
    assert(rotate(distances)==List(1,4,3,5,2))
  }

  @Test
  def rotateWhenInMiddle() {
    val distances = List(4,3,1,2,5)
    assert(rotate(distances)==List(1,2,5,4,3))
  }
  
}