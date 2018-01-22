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



