// Goal : aspects of object-oriented programming in Scala:
// ------------------------------------------------
// class parameters and constructors, methods and operators,
// private members, overriding, checking preconditions, overloading,
// and self references.

// 1. Specs of rational number
// A rational number is a number that can be expressed as a ratio n/d,
// where n and d are integers, except that d cannot
// be zero. n is called the numerator and d the denominator.
// e.g. 2/3, 2/1, 223/121

// 2. construction of Rational
class Rational0(n: Int, d: Int)

// Advantages of Immutable objects
// ------------------------------------------------
// 1. no state change over time; hence easy to reason about
// 2. easy to pass to methods as it cannot be changed
// 3. threadsafe
// 4. safe hashtable keys

// Disadvantages of immutable objects
// ------------------------------------------------
// 1. large object graph to be passed

class Rational1(n:Int, d: Int) {
  // any code you place in the class body,
  // which isn't part of a field or a method definition,
  // into the primary constructor
  println("Created " + n + "/" + d)
}

val half = new Rational1(1,2)

// Implement toString
// ------------------------------------------------
class Rational2(n:Int, d: Int) {
  println("Created " + n + "/" + d)
  // overridden toString implementation
  override def toString: String =  n + "/" + d
}
// Note : toString output here
val halfv2 = new Rational2(1,2)

// Checking preconditions
// ------------------------------------------------

class Rational3(n:Int, d: Int) {
  // -->
  // uncomment below line
  // require(d != 0)
  println("Created " + n + "/" + d)
  // overridden toString implementation
  override def toString: String =  n + "/" + d
}
// require precondition before object is created
// check below after you uncomment require precondition in class definition
val divByZero = new Rational3(1,0)

// Adding Fields
// ------------------------------------------------
// first attempt whereas we use class variables
class Rational4(n:Int, d: Int) {
  require(d != 0)
  println("Created " + n + "/" + d)
  override def toString: String =  n + "/" + d
  def add(that:Rational4) : Rational4 = {
    //--->
    // uncomment below line..not compilable why?
    // one can not access that's n and d as they are class variables
    //new Rational4(n * that.d + that.n * d, d * that.d)
    null
  }
}

// second attempt where we define instance val
class Rational5(n: Int, d: Int) {
  require(d != 0)
  val numer: Int = n
  val denom: Int = d
  override def toString = numer + "/" + denom
  def add(that: Rational5): Rational5 =
    new Rational5(
      numer * that.denom + that.numer * denom,
      denom * that.denom
    )
}
val oneHalf = new Rational5(1, 2)
val twoThird = new Rational5(2, 3)
oneHalf  add twoThird

// Note that one can access numer and denom now which was not possible earlier
val r = new Rational5(1, 2)
r.denom
r.numer

// Self references
// ------------------------------------------------
class Rational6(n: Int, d: Int) {
  require(d != 0)
  val numer: Int = n
  val denom: Int = d
  override def toString = numer + "/" + denom
  def add(that: Rational6): Rational6 =
    new Rational6(
      numer * that.denom + that.numer * denom,
      denom * that.denom
    )

  // Note that usage of `this` is optional here
  def lessThan(that: Rational6) =
    this.numer * that.denom < that.numer * this.denom

  // whereas usage of `this` is needed in else clause
  // if clause usage of `this` is still optional
  def max(that: Rational6) =
    if (this.lessThan(that)) that else this

}

// Auxillary Constructor
// Self references
// ------------------------------------------------
class Rational7(n: Int, d: Int) {
  require(d != 0)
  val numer: Int = n
  val denom: Int = d

  // Auxillary constructor
  def this(n:Int) = this(n,1)

  override def toString = numer + "/" + denom
  def add(that: Rational7): Rational7 =
    new Rational7(
      numer * that.denom + that.numer * denom,
      denom * that.denom
    )

  // Note that usage of `this` is optional here
  def lessThan(that: Rational7) =
    this.numer * that.denom < that.numer * this.denom

  // whereas usage of `this` is needed in else clause
  // if clause usage of `this` is still optional
  def max(that: Rational7) =
    if (this.lessThan(that)) that else this

}
val y = new Rational7(3)

// Notes :
// Primary constructor is the entry point to class
// auxillary constructor either invokes primary constructor or
// another auxillary constructor as its first action
// In a Scala class, only the primary constructor can invoke a superclass constructor
// In java any constructor can make call to superclass constructor

// Private Fields and Methods
// ------------------------------------------------

new Rational7(66, 42)
// above should be 11/7

// Note : Below code adds private field and method which can not be accessed
// from outside
class Rational8(n: Int, d: Int) {

  require(d != 0)

  // next 3 lines are called as initializer code and gets
  // executed as part of the constructor
  private val g = gcd(n.abs, d.abs)
  val numer = n / g
  val denom = d / g

  def this(n: Int) = this(n, 1)

  def add(that: Rational8): Rational8 =
    new Rational8(
      numer * that.denom + that.numer * denom,
      denom * that.denom
    )

  override def toString = numer + "/" + denom

  private def gcd(a: Int, b: Int): Int =
    if (b == 0) a else gcd(b, a % b)
}

new Rational8(66, 42) // -->  gives 11/7 now

// Operator

val oneHalfNew = new Rational8(1, 2)
val twoThirdNew = new Rational8(2, 3)
// we should be able to do below
// oneHalfNew + twoThirdNew
oneHalfNew  add twoThirdNew

// Notes : + and * are operator methods implemented
class Rational9(n: Int, d: Int) {

  require(d != 0)

  private val g = gcd(n.abs, d.abs)
  val numer = n / g
  val denom = d / g

  def this(n: Int) = this(n, 1)

  def + (that: Rational9): Rational9 =
    new Rational9(
      numer * that.denom + that.numer * denom,
      denom * that.denom
    )

  def * (that: Rational9): Rational9 =
    new Rational9(numer * that.numer, denom * that.denom)

  override def toString = numer + "/" + denom

  private def gcd(a: Int, b: Int): Int =
    if (b == 0) a else gcd(b, a % b)
}

val oneHalf2 = new Rational9(1, 2)
val twoThird2 = new Rational9(2, 3)
oneHalf2 + twoThird2
oneHalf2 * twoThird2
// Note that below maintains operator precedence as well
oneHalf2 + twoThird2*twoThird2

