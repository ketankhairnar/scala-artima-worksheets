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