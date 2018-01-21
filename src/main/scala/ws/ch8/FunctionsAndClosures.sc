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
