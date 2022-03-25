package lab2.courseraExercise

object sessionFactorial extends App{
  def factorial(n: Int): Int = {
    def loop(acc: Int, n:Int): Int =
      if (n == 0) acc
      else loop(acc * n, n - 1)
    loop(1, n)
  }

  System.out.println(factorial(4))
}

