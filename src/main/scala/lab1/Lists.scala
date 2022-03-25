package lab1

object Lists {
  def sum(xs: List[Int]): Int = {
    if (xs.isEmpty) {
      0
    }
    else {
      xs.head + sum(xs.tail)
    }
  }

  def max(xs: List[Int]): Int = {
    if (xs.isEmpty) {
      throw new java.util.NoSuchElementException
    }
    else {
      val head = xs.head
      val tail = xs.tail
      if (tail.isEmpty) {
        head
      }
      else {
        val maxTail = max(tail)
        if (head > maxTail) {
          head
        }
        else {
          maxTail
        }
      }
    }
  }
}
