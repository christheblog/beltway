package org.cc.problem.beltway

import scala.annotation.tailrec

object Beltway {
  
  type Solution = List[Int]
  type Solutions = List[Solution]
  
  // Drop helper function : drop first encountered values from list with a single pass on the list
  // Note : List maybe returned reversed or not ! we do that to save O(n) each time
  def dropFirst[T](l: List[T],values: Seq[T]): Option[List[T]] = {
    
    // Drops one value from the list. Result is reversed
    def dropOne[T](l: List[T], value: T): Option[List[T]] = {
      @tailrec def loop(todo: List[T],res: List[T],found: Boolean = false): Option[List[T]] = todo match {
        case Nil => if(found) Some(res) else None
        case x :: xs => if(x==value) loop(xs,res,true) else loop(xs, x :: res,found) 
      }
      loop(l,Nil)
    }
    
    @tailrec
    def loop[T](todo: List[T], toDrop: List[T], res: List[T]): Option[List[T]] = (todo,toDrop) match {
      case (Nil,Nil) => Some(res)
      case (Nil,_) => None
      case (x :: xs, Nil) => loop(xs,Nil,x :: res)
      case (x :: xs, ds) => dropOne(ds,x) match {
        case Some(newds) => loop(xs,newds,res)
        case _ => loop(xs,ds,x :: res)
      }
    }
    
    loop(l,values.toList,Nil)
  }
  
  // Compute the distances on a circle from the pairwise distances
  // Require at least n > 1 elements
  def solve(pwDistances: List[Int], 
            pointConstraint: Int => Boolean = (_) => true): Solutions = {
    
    val distances = pwDistances.sorted // ensure sorted pairwise distances
    val distanceSet = pwDistances.toSet // distances as a set for fast query
    val perimeter = distances.head + distances.last // first and last elt should give the circle perimeter
    val solutionSize = ((1+math.sqrt(1+4*distances.size))/2).toInt // solved quadratic equation for number of points on the circle
    
    // Compute the list of next candidates - ie possible next distance to explore according to the remaining constraints
    def nextCandidates(n: Int, distances: List[Int], pointConstraint: Int => Boolean, cumulatedDistance: Int): List[Int] = {
      val set = distances.toSet
      val candidates = (set.map { _ + n} intersect set)
      candidates.filter { p => (p - n + cumulatedDistance <= perimeter) && pointConstraint(p-n) }.toList.map { _ - n}
    }
    
    // Check the partial built solution is matching some of the constraints
    def checkConstraints(partial: Solution) = partial match {
      case x :: y :: _ => distanceSet(partial.init.sum) && distanceSet(partial.tail.sum)
      case _ => true
    }
    
    // Recursive function to find solutions
    def find(check: List[Int], currentSolution: Solution, total: Int, res: Solutions): Solutions = (currentSolution,total) match {
      case (cs,t) if t==perimeter && cs.size==solutionSize => cs :: res // Ideally we should check the remaining constraints here ...
      case (cs,t) if t >= perimeter || !checkConstraints(cs) => res // Eliminating current solution. Obvious constraints not satisfied here
      case (cs,t) =>
        // Parallel version of the computation. Could be turned off here
        res ::: nextCandidates(cs.head,check,pointConstraint,total).par.flatMap { 
          case newCurrent =>
            val constraintsToDrop = cs.scanLeft(newCurrent) { _ + _ }
            dropFirst(check,constraintsToDrop) match {
              case Some(newcheck) => find(newcheck, newCurrent :: cs, newCurrent + total, Nil)
              case None => Nil
            }
        }.toList
    }
    
    // Initiate recursive calls
    // Starting with the minimum distance - we are sure it is one of the non-aggregated distance
    // We also can eliminate the last constraint that is the complementary distance of the first distance
    find(distances.tail ++ List(perimeter),List(distances.head),distances.head,Nil)
  }
}