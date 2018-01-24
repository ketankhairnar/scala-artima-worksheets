// Methods
//--------------------------------------
// This is similar to what we do in Java
import scala.io.Source

object LongLines {

  def processFile(filename: String, width: Int) = {
    val source = Source.fromFile(filename)
    for (line <- source.getLines())
      processLine(filename, width, line)
  }

  private def processLine(filename: String,
                          width: Int, line: String) = {

    if (line.length > width)
      println(filename + ": " + line.trim)
  }
}
// Local Functions
//--------------------------------------
// Too many methods will pollute namespace
// easy solution - private methods similar to Java
// second option - functions inside functions or methods
// called as local functions

def processFile(filename: String, width: Int) = {

  def processLine(filename: String,
                  width: Int, line: String) = {

    if (line.length > width)
      println(filename + ": " + line.trim)
  }

  val source = Source.fromFile(filename)
  for (line <- source.getLines()) {
    processLine(filename, width, line)
  }
}

// FIRST-CLASS FUNCTIONS
//--------------------------------------

// example of function literal
(x: Int) => x + 1
var increase = (x: Int) => x + 1
increase(10)

// function literal with block

increase = (x: Int) => {
  println("We")
  println("are")
  println("here!")
  x + 1
}

increase(11)

val someNumbers = List(-11, -10, -5, 0, 5, 10)

// see how foreach accepts literal function
someNumbers.foreach((x: Int) => println(x))

// filter also accepts literal function with boolean outcome
someNumbers.filter((x: Int) => x > 0)

// short form on function literals
// 1. remove redundant brackets
someNumbers.filter(x => x > 0)
// using placeholder
someNumbers.filter(_ > 0)
// partially applied functions
someNumbers.foreach(println _)
// Thus, the underscore in this case is not a placeholder for a single parameter.
// It is a placeholder for an entire parameter list.

// When you use an underscore in this way, you are writing a partially applied function.


// below is full function with all arguments
def sum(a: Int, b: Int, c: Int) = a + b + c
sum(1,2,3)

val a = sum _

// Given above code, the Scala compiler instantiates a function value that takes the three integer
// parameters missing from the partially applied function expression,
// sum _, and assigns a reference to that new function value to the variable a.
// When you apply three arguments to this new function value,
// it will turn around and invoke sum, passing in those same three arguments:

a(1, 2, 3)

// you can also express a partially applied function by supplying only some of
// the required arguments.

val b = sum(1, _: Int, 3)

b(2)

// If you are writing a partially applied function expression in which you leave off
// all parameters, such as println _ or sum _, you can express it more concisely by leaving off the underscore if a
// function is required at that point in the code.

someNumbers.foreach(println _)
// could be written as
someNumbers.foreach(println)


// CLOSURES
//--------------------------------------


var more = 1

val addMore = (x: Int) => x + more

addMore(11)

// The function addMore is a closure because it does not only depend on its parameters,
// but also on a variable that is declared outside of its own lexical scope (namely more).

// now if we change value of more to something else

more = 35

addMore(11)

//Scala's closures capture variables themselves, not the value to which variables refer.
// As the previous example shows, the closure created for (x: Int) => x + more
// sees the change to more made outside the closure.
//
// The same is true in the opposite direction. Changes made by a closure to a captured
// variable are visible outside the closure.

val someNumbers1 = List(-11, -10, -5, 0, 5, 10)

var sum2 = 0

someNumbers1.foreach(sum2 +=  _)

sum2

// Above example uses a roundabout way to sum the numbers in a List.
// Variable sum is in a surrounding scope from the function literal sum += _,
// which adds numbers to sum. Even though it is the closure modifying sum at runtime,
// the resulting total, -11, is still visible outside the closure.


// Each time below function is called it will create a new closure.
// Each closure will access the more variable that was active when the closure was created.
def makeIncreaser(more: Int) = (x: Int) => x + more

val inc1 = makeIncreaser(1)

val inc9999 = makeIncreaser(9999)

inc1(1)

inc9999(1)



