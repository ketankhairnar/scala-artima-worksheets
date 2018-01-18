// Scala has only a handful of built-in control structures.
// The only control structures are if, while, for, try, match, and function calls.

//Notes
// 1.  almost all of Scala's control structures result in some value


// 1 IF Expressions
//--------------------------------------
val args = Array[String]()
val filename =
if (!args.isEmpty) args(0)
else "default.txt"
// if has two branches. If args is not empty, the initial element,
// args(0), is chosen; otherwise, the default value is chosen

// 2 While loop
//--------------------------------------
def gcdLoop(x: Long, y: Long): Long = {
  var a = x
  var b = y
  while (a != 0) {
    val temp = a
    a = b % a
    b = temp
  }
  b
}
// While loop has a condition and a body, and the body is
// executed over and over as long as the condition holds true

//var line = ""
//do {
//  line = scala.io.StdIn.readLine()
//  println("Read: " + line)
//} while (line != "")

// Note : The while and do-while constructs are called "loops," not expressions,
// because they don't result in an
// interesting value. The type of the result is Unit

// Because the while loop results in no value, it is often left
// out of pure functional languages.

// scala still uses it for below reason
// For example, if you want to code an algorithm that repeats
// a process until some condition changes, a while loop can express
// it directly while the functional alternative, which likely uses recursion,
// may be less obvious to some readers of the code.

// compare below with earlier gcdLoop
def gcd(x: Long, y: Long): Long =
  if (y == 0) x else gcd(y, x % y)

// 3 For expressions
//--------------------------------------
//

//3.1  Iteration through collections

val filesHere = (new java.io.File(".")).listFiles

for (file <- filesHere)
  println(file)

//3.2  Filtering
for (file <- filesHere if file.getName.endsWith(".scala"))
  println(file)

// One can include more filters if needed
for (
  file <- filesHere
  if file.isFile
  if file.getName.endsWith(".scala")
) println(file)


// 3.3 Nested Iteration
def fileLines(file: java.io.File) =
  scala.io.Source.fromFile(file).getLines().toList

def grep(pattern: String) =
  for (
    file <- filesHere
    if file.getName.endsWith(".scala");
    line <- fileLines(file)
    if line.trim.matches(pattern)
  ) println(file + ": " + line.trim)

grep(".*gcd.*")

// 3.4 Mid-stream variable bindings
def grepUpdated(pattern: String) =
  for {
    file <- filesHere
    if file.getName.endsWith(".scala")
    line <- fileLines(file)
    trimmed = line.trim
    if trimmed.matches(pattern)
  } println(file + ": " + trimmed)

grepUpdated(".*gcd.*")

// 3.5 producing new collection

def scalaFiles =
  for {
    file <- filesHere
    if file.getName.endsWith(".scala")
  } yield file

// Be careful, by the way, where you place the yield keyword. The syntax of a for-yield expression is like this:
// for clauses yield body
// below code example is wrong one

// for (file <- filesHere if file.getName.endsWith(".scala")) {
// yield file  // Syntax error!
// }

// complete example with all use cases discussed earlier as below
// Transforming an Array[File] to Array[Int] with a for.
val forLineLengths =
  for {
    file <- filesHere
    if file.getName.endsWith(".scala")
    line <- fileLines(file)
    trimmed = line.trim
    if trimmed.matches(".*for.*")
  } yield trimmed.length

// Throwing Exceptions

var n = 54

val half =
  if (n % 2 == 0)
    n / 2
  else
    throw new RuntimeException("n must be even")

// Technically, an exception throw has type Nothing.
// One branch of an if computes a value, while the other throws an exception
// and computes Nothing. The type of the whole if expression is then the type of
// that branch which does compute something.

// Catching Exceptions

import java.io.FileReader
import java.io.FileNotFoundException
import java.io.IOException

try {
  val f = new FileReader("input.txt")
  // Use and close file
} catch {
  case ex: FileNotFoundException => // Handle missing file
  case ex: IOException => // Handle other I/O error
}
// In this example, if the exception is of type FileNotFoundException, the first clause
// will execute. If it is of type IOException, the second clause will execute.
// If the exception is of neither type, the try-catch will terminate and the exception will propagate further.

// Unlike java in scala we don't have to throw checked exceptions


// The finally clause
import java.io.FileReader

val file = new FileReader("input.txt")
try {
  // Use the file
} finally {
  file.close()  // Be sure to close the file
}

// You can wrap an expression with a finally clause if you want to cause some code
// to execute no matter how the expression terminates.