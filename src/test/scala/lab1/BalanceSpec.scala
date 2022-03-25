package lab1

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers

class BalanceSpec extends AnyFreeSpec with Matchers {

  private def bal(s: String) = Lab1.balance(s.toList)

  "'(a (0? b) max (/ 2 z))' balance" in {
    bal("(a (0? b) max (/ 2 z))") shouldBe true
  }

  "'Bla bla (bla ...' balance" in {
    bal("Bla bla (bla (bla) bla).\n(Bla bla blabla bla)") shouldBe true
  }

  "'())(' no balance" in {
    bal("())(") shouldBe true
  }
}