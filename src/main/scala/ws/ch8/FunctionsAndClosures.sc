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