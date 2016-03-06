package org.cc.problem.beltway


import org.junit._

class DropFirstTest {
  
  import Beltway._
  
  @Test
  def testDropFirstWhenFirst() {
    val l = List(1,2,3,4,5,6,7,8,9,7,8,9,10)
    assert(dropFirst(l,List(1))==Some(l.tail.reverse))
  }

  @Test
  def testDropFirstWhenLast() {
    val l = List(1,2,3,4,5,6,7,8,9,7,8,9,10)
    assert(dropFirst(l,List(10))==Some(l.init.reverse))
  }
  
  @Test
  def testDropFirstWhenInTheMiddle() {
    val l = List(1,2,3,4,5,6,7,8,9,7,8,9,10)
    assert(dropFirst(l,List(8))==Some(List(1,2,3,4,5,6,7,9,7,8,9,10).reverse))
    assert(dropFirst(dropFirst(l,List(8)).get,List(8))==Some(List(1,2,3,4,5,6,7,9,7,9,10)))
  }
  
  @Test
  def testDropFirstWhenNotInList() {
    val l = List(1,2,3,4,5,6,7,8,9,7,8,9,10)
    assert(dropFirst(l,List(11)).isEmpty)
  }
  
  @Test
  def testMultipleDropFirstWhenNoneInList() {
    val l = List(1,2,3,4,5,6,7,8,9,7,8,9,10)
    assert(dropFirst(l,List(11,12,13)).isEmpty)
  }
    
  @Test
  def testMultipleDropFirstWhenAllNotInList() {
    val l = List(1,2,3,4,5,6,7,8,9,7,8,9,10)
    assert(dropFirst(l,List(1,8,10,11)).isEmpty)
  }

  @Test
  def testMultipleDropFirstWhenAllInList() {
    val l = List(1,2,3,4,5,6,7,8,9,7,8,9,10)
    val res = dropFirst(l,List(1,8,10))
    assert(res.map { _.reverse } == Some(List(2,3,4,5,6,7,9,7,8,9)))
  }
  
}