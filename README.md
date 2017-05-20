# The Beltway Reconstruction Problem

Given a list of unordered pairwise distances between points disposed on a circle, we need to find the original order of the points.


## An example

We have the following "circle" with 4 points on it. Integers are denoting distances between points on the circle.

              -- A -- 
            /         \		
           3           5	
          /             \
          B             D
          \             /
           4          12
            \         /
              -- C -- 	
		        
The input of the problem will be given as a list of pairwise distances (here displayed in order) :
```
AB: 3
AC: 7
AD: 19
BC: 4
BD: 16
BA: 21
CD: 12
CA: 17
CB: 20
DA: 5
DB: 8
DC: 12
```

From the unordered list of pairwise distances, we need to find the original ordering of the points.


## The algorithm

The algorithm implemented here is just an exponential naive solution that tries all possible combinations using some heuristics to remove a number of non-working solutions.

The algorithm accepts a constraints function that allows us to provide some knowledge about the distances.<br>
For instance we can know that the distance between 2 points cannot be more than 1000. 
This reduces dramatically the search space as we can cut a lot of branches of our search tree at an early stage.


## The Code

Code has been written in Scala. I tried to write some reasonably fast code, but my goal was ultimately to have a clean solution working with immutable ```List```.

It includes a problem generator ```BeltwayGenerator``` that help to randomly create distances and compute pairwise distances. 
This is used to feed our solving algorithm in the object ```BeltwayApp```, so we can easily check correctness for bigger problems.


## Notes

As far as I know, there is no polynomial time algorithm to solve this problem. This problem has some important applications in bioinformatics for DNA sequencing ... so if you discover a polynomial algorithm, you are welcome to share it :)

If you are interested, you can have a look at the following article : (http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.20.6172)
 