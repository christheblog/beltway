package org.cc.problem.beltway


object BeltwayApp extends App {
  
  import BeltwayGenerator._
  import Beltway._
  
  // Example
  // val distances = List(618, 441, 897, 825, 592, 19, 461, 690, 400, 496, 101, 693, 320, 647, 216, 724, 177)
  val distances = List(3,4,12,5)
  
  // Randomly generated n pairwise distances, between 1 and 1000
  // Warning : Solving a problem with a big value for n can be very long. Algorithm is exponential.
  //val distances = generate(n=35,min=1,max=1000)
  
  println(s"Running problem for distances : ${distances} ...")
  
  val rotated = rotate(distances)
  val pwdistances = pairwiseDistances(distances)
  // Solving problem
  val start = System.currentTimeMillis
  
  // Point constraints : to take advantage of what we know about the points
  // These constraints are useful when selecting possible candidates for the next distance, 
  // to help cutting branches in the tree
  val NoConstraint = (_: Int)=> true // we don't know anything about the distances
  val KnownNumbersConstraint = (x: Int)=> x <= 1000 // we know that distances are between 1 and N
  val solutions = solve(pwdistances,KnownNumbersConstraint)
  val end = System.currentTimeMillis
  
  // Checking found solutions
  val (satisfying,nonSatisfying) = solutions.partition { s => pairwiseDistances(s)==pwdistances }
  
  // Printing report
  println(s"Initial problem : ${distances} with perimeter ${distances.sum}")
  println(s"Rotated problem with min distance first : ${rotated}")
  println(s"Defined with ${pwdistances.size} pair-wise distances : ${pwdistances}")
  println(s"\nSolved in ${(end-start)/1000} seconds.")
  
  // FIXME : Sometimes, some returned solutions don't satisfy all the pairwise distances due to imperfection of the algorithm
  // They need to be eliminated after
  println(s"Found ${satisfying.size} satisfying solutions (and ${nonSatisfying.size} non-satisfying solutions)")
  satisfying.foreach { s => println(s.reverse) }

}