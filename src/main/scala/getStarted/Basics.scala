package getStarted

object Basics extends App {

  val meaningOfLife: Int = 42

  // Int, Boolean, Char, Double, Float, String
  val aBoolean = false
  val aString = "run"
  val aComposedString = "I" + " " + "love" + " " + "run"
  val anInterpolatedString = s"The meaning of life is $meaningOfLife"

  val anExpression = 2 + 3

  val ifExpression = if (meaningOfLife > 43) 56 else 999
  val chainedIfExpression =
    if (meaningOfLife > 43) 56
    else if (meaningOfLife < 0) -2
    else if (meaningOfLife > 999) 78
    else 0

  val aCodeBlock = {
    val aLocalValue = 67

    aLocalValue + 3
  }

  def myFunction(x: Int, y: String): String = {
    y + " " + x
  }

  def factorial(n: Int): Int =
    if (n <= 1) 1
    else n * factorial(n - 1)

  println("Run") // System.out.println

  def myUnitReturningFunction(): Unit = {
    println("returning Unit")
  }

  val theUnit = ()
}
