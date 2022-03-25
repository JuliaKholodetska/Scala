package lab2

object Lab2 {

  type Set = Int => Boolean

  def contains(s: Set, elem: Int): Boolean = s(elem)

  def singletonSet(elem: Int): Set = x => elem == x

  def union(s: Set, t: Set): Set = x => contains(s, x) || contains(t, x)

  def intersect(s: Set, t: Set): Set = x => contains(s, x) && contains(t, x)

  def diff(s: Set, t: Set): Set =  x => contains(s, x) && !contains(t, x)

  def filter(s: Set, p: Int => Boolean): Set = x => contains(s, x) && p(x)

  val bound = 1000

  def forall(s: Set, p: Int => Boolean): Boolean = {
    def iter(a: Int): Boolean = {
      if (a > bound) true
      else if (s(a) && !p(a)) false
      else iter(a + 1)
    }
    iter(-bound)
  }

  def exists(s: Set, p: Int => Boolean): Boolean = !forall(s, x => !p(x))

  def map(s: Set, f: Int => Int): Set = x => exists(s, y => f(y) == x)

  def makeString(s: Set): String = {
    (-bound to bound)
      .filter(contains(s, _))
      .mkString("{", ", ", "}")
  }

  def printSet(s: Set): Unit = {
    println(makeString(s))
  }

  def main(args: Array[String]): Unit = {
    println(contains(singletonSet(1), 1))
    printSet(union(singletonSet(1), singletonSet(2)))
  }
}
