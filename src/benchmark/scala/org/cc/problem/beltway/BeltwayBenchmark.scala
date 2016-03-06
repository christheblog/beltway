package org.cc.problem.beltway

import org.junit._

import BeltwayGenerator._
import Beltway._

class BeltwayBenchmark {

  // Point constraints : to take advantage of what we know of the points
  // These constraints are useful when selecting possible candidates for the next distance, to help cutting branches in the tree
  val NoConstraint = (_: Int) => true // we don't know anything about the distances
  val KnownNumbersConstraint = (x: Int) => x <= 1000 // we know that distances are between 1 and N
  val Distances = List(618, 441, 897, 825, 592, 19, 461, 690, 400, 496, 101, 693, 320, 647, 216, 724, 177,234,512,345)
//  val Distances = BeltwayGenerator.generate(30,1,250)
  
  @Test
  def benchmark5NodesNoConstraints() {
    benchmark(Distances.take(5),None)
  }
  
  @Test
  def benchmark5NodesWithConstraints() {
    benchmark(Distances.take(5),Some(KnownNumbersConstraint))
  }
  
  @Test
  def benchmark10NodesNoConstraints() {
    benchmark(Distances.take(10),None)
  }
  
  @Test
  def benchmark10NodesWithConstraints() {
    benchmark(Distances.take(10),Some(KnownNumbersConstraint))
  }

  @Test
  def benchmark15NodesNoConstraints() {
    benchmark(Distances.take(15),None)
  }
  
  @Test
  def benchmark15NodesWithConstraints() {
    benchmark(Distances.take(15),Some(KnownNumbersConstraint))
  }

  @Test
  def benchmarkAllNodesNoConstraints() {
    benchmark(Distances,None)
  }

  private[this] def benchmark(distances: List[Int],constraint: Option[Int => Boolean]) {
    println(s"Running problem for distances : ${distances} ...")
    val rotated = rotate(distances)
    val pwdistances = pairwiseDistances(distances)
    // Solving problem
    val start = System.currentTimeMillis
    val solutions = solve(pwdistances, constraint.getOrElse(NoConstraint))
    val end = System.currentTimeMillis
    // Checking found solutions
    println(s"Solved ${distances.size} points in ${(end - start) / 1000} seconds - ${constraint.map {_ => "with constraints"}.getOrElse("without constraints")}")
    println
  }
  
}