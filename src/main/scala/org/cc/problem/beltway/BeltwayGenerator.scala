package org.cc.problem.beltway

object BeltwayGenerator {
  
  def generate(n: Int = 10, min: Int =1, max: Int = 100) = 
    (1 to n).toList.map { _ => scala.util.Random.nextInt(max - min + 1) + min }
  
  // Compute pairwise distances from a list of distances
  def pairwiseDistances(distances: List[Int]) = distances match {
    case Nil => Nil
    case x :: Nil => distances
    case _ => {
      val first = distances.init.scanLeft(0) { _ + _ }.tail
      val others = for(i <- 1 until distances.size) yield {
        (distances.drop(i) ++ distances.take(i-1)).scanLeft(0) { _ + _ }.tail
      }
      (first :: others.toList).flatten.sorted
    }
  }
  
  // Rotate a list of distances to have the min first
  def rotate(distances : List[Int]) = {
    val min = distances.min
    (distances.indexOf(min),distances.size) match { 
      case (0,_) => distances
      case (i,size) if i==size -1 => min :: distances.init
      case (i,_) => (distances.drop(i) ++ distances.take(i))
    }
  }
  
}