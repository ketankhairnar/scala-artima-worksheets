// basic types
// Byte, Short, Int, Long, Char, String, Float, Double, Boolean
// Notes:
//  String --> java.lang
//  whereas others belong to scala. package



// 1. Integer Literals
// ------------------------------------------------
//0x makes it hex
val hex = 0x5
val hex2 = 0xddbabe

// l/L makes it long
val long = 23L
val anotherLong = 131313l

// explicit type specification
val little : Short = 23
val smaller : Byte = 127

// e/E exponent
val big = 1.234
val bigger = 1.2345e1
val biggerStill = 1234.5E12

// f/F forces it to be float
val small = 1.2345F
val littleBigger = 3e5f

val anotherDouble = 3e5


// 2 Character Literals
// ------------------------------------------------
val a = 'A'
val d = '\u0041'

// 3. String Literals
// ------------------------------------------------
val hello = "hello"
val escapes = "\\\"\'"

// multilines with 3 quotes
println("""Welcome to Ultamix 3000.
             Type "HELP" for help.""")
// stripMargin with | for formatting
println("""|Welcome to Ultamix 3000.
   |Type "HELP" for help.""".stripMargin)

// 4 . Symbol literals
// ------------------------------------------------
// A symbol literal is written 'ident, where ident can be any alphanumeric identifier.
// Specifically, the literal 'cymbal will be expanded by the
// compiler to a factory method invocation: Symbol("cymbal")

val  s = 'aSymbol
val nm = s.name
// Note - Symbols are interned

// Here record can be symbol or identifier used internally in the method
def updateRecordByName(r: Symbol, value: Any) = {
  // code goes here
}
updateRecordByName('favoriteAlbum, "OK Computer")

// 5. boolean literals
val bool = true
val fool = false

// String Interpolation
// ------------------------------------------------
val name = "reader"
println(s"Hello, $name!")

s"The answer is ${6 * 7}."

println(raw"No\\\\escape!")

val pi = "Pi"
f"$pi is approximately ${math.Pi}%.8f."

// Operator are methods
// ------------------------------------------------

val sum = 1 +2
val sumMore = 1.+(2)
//overloaded methods for different types
val longSum = 1.+(2L)


// Note: Use any method in operator notation
val str = "Hello, World!"
str indexOf 'o'
str indexOf ('o', 5)

// + and indexOf are used as an *infix* operator

// prefix operator example unary
- 5 // converted to --> 5.unary_-
! false // converted to --> false.unary_!
~0xFF // converted to --> oxFF.unary_~
+ 234 // converted to --> 234.unary_+

//postfix operator example ..again unary
// no args to postfix operator methods
4  toLong

str toLowerCase

// Arithmetic operations

//  relational and logical operations

// bitwise operations

// object equality
// If you want to compare two objects for equality, you can use either == or its inverse !=.

// note -- all types not just primitives
1==2
true == false
List(1,2,3) == List(1,2,3)
// objects of different types

3 ==3.0
4 ==4l
List(2,3,4)== false

List(2,3,4)== null
// Note : First check the left side for null.
// If it is not null, call the equals method. \
// Since equals is a method, the precise comparison
// you get depends on the type of the left-hand argument.
// Since there is an automatic null check, you do not have to do the check yourself

// In java == used for reference equality whereas equals method for actual comparison


// Operator precedence
// ------------------------------------------------
// (all other special characters)
// * / %
// + -
// :
// = !
// < >
// &
// ^
// |
// (all letters)
// (all assignment operators) =,*=,-=,+=
// ------------------------------------------------
// exception
// x *= y + 1 --> x *= (y+1)

// side note
// any method that ends in a `:' character is invoked on its right operand,
// passing in the left operand.
// Methods that end in any other character are the other way around:
// They are invoked on their left operand, passing in the right operand.
// So a * b yields a.*(b), but a ::: b yields b.:::(a).


// Rich Wrappers
// ------------------------------------------------
// Code	                Result
// ------------------------------------------------
// 0 max 5	            5
// 0 min 5	            0
// -2.7 abs	            2.7
// -2.7 round	          -3L
// 1.5 isInfinity	      false
// (1.0 / 0) isInfinity	true
// 4 to 6	              Range(4, 5, 6)
// "bob" capitalize	    "Bob"
// "robert" drop 2	    "bert"
// ------------------------------------------------