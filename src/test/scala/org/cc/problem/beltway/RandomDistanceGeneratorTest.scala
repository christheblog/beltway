package org.cc.problem.beltway

import org.junit._

class RandomDistanceGeneratorTest {
  @Test
  def testRandomGeneration() {
    val N = 20000
    assert((1 to N).toList.map { _ => 
      BeltwayGenerator.generate(4,3,25) }.filter { l => l.min < 3 || l.max > 25}.isEmpty
    )
  }
}