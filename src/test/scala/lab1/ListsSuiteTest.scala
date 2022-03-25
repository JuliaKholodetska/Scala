package lab1

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers

 class ListsSuiteTest  extends AnyFreeSpec with Matchers  {

 "one plus one is two"in {(assert(1 + 1 == 2))}

 "one plus one is three?" in {
   assert(1 + 1 == 2) // This assertion fails! Go ahead and fix it.
 }

 "details why one plus one is not three" in{
   assert(1 + 1 === 2) // Fix me, please!
 }

 "intNotZero throws an exception if its argument is 0" in {
   intercept[IllegalArgumentException] {
     intNotZero(0)
   }
 }

 def intNotZero(x: Int): Int = {
   if (x == 0) throw new IllegalArgumentException("zero is not allowed")
   else x
 }

 import Lists._

 "sum of a few numbers" in {
   assert(sum(List(1,2,0)) === 3)
 }

 "max of a few numbers" in {
   assert(max(List(3, 7, 2)) === 7)
 }

 "sum of a few numbers with negative numbers" in {
   assert(sum(List(3, 9, -4, -1)) === 7)
 }

 "max of a few numbers with negative numbers" in {
   assert(max(List(3, 7, 2, -4)) === 7)
 }

 "sum of an empty List" in {
   assert(sum(List()) === 0)
 }

 "max throws an exception if its argument is empty" in {
   intercept[NoSuchElementException] {
     max(List())
   }
 }

 "max of a 1 element List" in{
   assert(max(List(4)) === 4)
 }

}
