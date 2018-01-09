class ChecksumAccumulator {
  private var sum = 0

  // to make any member public you dont have to specify anything unlike java
  var member = 99

  // remember method parameters are val by default
  def add(b: Byte): Unit = {
    // hence below line wont compile
    // b = 1
    sum += b
  }

  def checksum(): Int = {
    return ~(sum & 0xFF) + 1
  }
}
val acc = new ChecksumAccumulator
val csa = new ChecksumAccumulator

// below won't compile as sum is private variable
// acc.sum
acc.member

// A method that returns
// A method that is executed only for its side effects is known as a procedure.
acc.add(4)

// semicolon inference
// Line ending is treated as semicolon unless
// 1. last word not legal e.g. period or infix operator
// 2. next line begins with word that cannot start a statement
// 3. the line ends inside parentheses or brackets




// When a singleton object shares the same name with a class,
// it is called that class's companion object.
// You must define both the class and its companion object
// in the same source file.
// The class is called the companion class of the singleton object.
// A class and its companion object can access each other's private members.

// In file ChecksumAccumulator.scala
import scala.collection.mutable
object ChecksumAccumulator {

  private val cache = mutable.Map.empty[String, Int]

  def calculate(s: String): Int =
    if (cache.contains(s))
      cache(s)
    else {
      val acc = new ChecksumAccumulator
      for (c <- s)
        acc.add(c.toByte)
      val cs = acc.checksum()
      cache += (s -> cs)
      cs
    }
}

ChecksumAccumulator.calculate("Every value is an object.")

// One difference between classes and singleton objects is that singleton objects cannot take parameters,
// whereas classes can. Because you can't instantiate a singleton object with the new keyword,
// you have no way to pass parameters to it.
// Each singleton object is implemented as an instance of a synthetic class referenced from a static variable,
// so they have the same initialization semantics as Java statics.
// In particular, a singleton object is initialized the first time some code accesses it.


// A singleton object that does not share the same name with a companion class is called a standalone object.
// example below

// In file Summer.scala
import ChecksumAccumulator.calculate

object Summer {
  def main(args: Array[String]) = {
    for (arg <- args)
      println(arg + ": " + calculate(arg))
  }
}

// App Trait - this allows one to write code directly instead of having main method

import ChecksumAccumulator.calculate

object FallWinterSpringSummer extends App {

  for (season <- List("fall", "winter", "spring"))
    println(season + ": " + calculate(season))
}
