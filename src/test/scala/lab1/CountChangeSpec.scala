package lab1

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers

class CountChangeSpec extends AnyFreeSpec with Matchers {

  import Lab1.countChange

  "example" in {
    countChange(4, List(1, 2)) shouldBe  3
  }

  "sorted counts" in {
    countChange(300, List(5, 10, 20, 50, 100, 200, 500)) shouldBe 1022
  }

  "without needed count" in {
    countChange(301, List(5, 10, 20, 50, 100, 200, 500)) shouldBe 0
  }

  "unsorted counts" in {
    countChange(300, List(500, 5, 50, 100, 20, 200, 10)) shouldBe 1022
  }

  "without couns" in {
    countChange(300, List()) shouldBe 0
  }

  "without money" in {
    countChange(0, List(500, 5, 50, 100, 20, 200, 10)) shouldBe 1
  }
}