package org.cc.problem.beltway

import org.junit._

class PairwiseDistanceGenerator {
  
  @Test
  def test4PointsPairwiseDistances() {
    val distances = List(2,3,5,3)
    assert(BeltwayGenerator.pairwiseDistances(distances)==List(2, 3, 3, 5, 5, 5, 8, 8, 8, 10, 10, 11))
  }
  
  @Test
  def test5PointsPairwiseDistances() {
    val distances = List(2,3,5,3,1)
    assert(BeltwayGenerator.pairwiseDistances(distances)==List(1, 2, 3, 3, 3, 4, 5, 5, 6, 6, 8, 8, 9, 9, 10, 11, 11, 11, 12, 13))
  }

  @Test
  def test40PointsPairwiseDistances() {
    import BeltwayGenerator._
    assert(pairwiseDistances(generate(40,1,100)).size == 40 *39)
  }

  
}