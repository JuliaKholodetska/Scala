package lab3

class Tweet(val user: String, val text: String, val retweets: Int) {
  override def toString: String =
    "User: " + user + "\n" +
      "Text: " + text + " [" + retweets + "]"
}


abstract class TweetSet {

  def filter(p: Tweet => Boolean): TweetSet = filterAcc(p, Empty)

  def filterAcc(p: Tweet => Boolean, acc: TweetSet): TweetSet

  def union(that: TweetSet): TweetSet

  def mostRetweeted: Tweet = {
    this.tail.mostRetweetedAcc(this.head)
  }

  def mostRetweetedAcc(curr: Tweet): Tweet = {
    if (this.isEmpty) curr
    else if (this.head.retweets > curr.retweets) this.tail.mostRetweetedAcc(this.head)
    else this.tail.mostRetweetedAcc(curr)
  }

  def descendingByRetweet: TweetList = {
    if (isEmpty) Nil
    else {
      val max = mostRetweeted
      new Cons(max, remove(max).descendingByRetweet)
    }
  }

  def isEmpty: Boolean
  def head: Tweet
  def tail: TweetSet

  def add(tweet: Tweet): TweetSet

  def remove(tweet: Tweet): TweetSet

  def contains(tweet: Tweet): Boolean

  def foreach(f: Tweet => Unit): Unit
}

object Empty extends TweetSet {

  def filterAcc(p: Tweet => Boolean, acc: TweetSet): TweetSet = acc
  def union(that: TweetSet): TweetSet = that

  def contains(tweet: Tweet): Boolean = false
  def add(tweet: Tweet): TweetSet = new NonEmpty(tweet, Empty, Empty)
  def remove(tweet: Tweet): TweetSet = this
  def foreach(f: Tweet => Unit): Unit = ()

  def head = throw new Exception("there is no head in Empty")
  def tail = throw new Exception("there is no tail in Empty")
  def isEmpty: Boolean = true
}

class NonEmpty(elem: Tweet, left: TweetSet, right: TweetSet) extends TweetSet {

  def filterAcc(p: Tweet => Boolean, acc: TweetSet): TweetSet = {
    val leftright = left.filterAcc(p, right.filterAcc(p, acc))
    if (p(elem)) leftright.add(elem)
    else leftright
  }

  def contains(x: Tweet): Boolean =
    if (x.text < elem.text) left.contains(x)
    else if (elem.text < x.text) right.contains(x)
    else true

  def add(x: Tweet): TweetSet = {
    if (x.text < elem.text) new NonEmpty(elem, left.add(x), right)
    else if (elem.text < x.text) new NonEmpty(elem, left, right.add(x))
    else this
  }

  def union(that: TweetSet): TweetSet = that.filterAcc(twit => true, this)

  def head = if (left.isEmpty) elem else left.head
  def tail = if (left.isEmpty) right else new NonEmpty(elem, left.tail, right)
  def isEmpty: Boolean = false

  def remove(tw: Tweet): TweetSet =
    if (tw.text < elem.text) new NonEmpty(elem, left.remove(tw), right)
    else if (elem.text < tw.text) new NonEmpty(elem, left, right.remove(tw))
    else left.union(right)

  def foreach(f: Tweet => Unit): Unit = {
    f(elem)
    left.foreach(f)
    right.foreach(f)
  }
}

trait TweetList {
  def head: Tweet
  def tail: TweetList
  def isEmpty: Boolean
  def length: Int
  def foreach(f: Tweet => Unit): Unit =
    if (!isEmpty) {
      f(head)
      tail.foreach(f)
    }
}

object Nil extends TweetList {
  def head = throw new java.util.NoSuchElementException("head of EmptyList")
  def tail = throw new java.util.NoSuchElementException("tail of EmptyList")
  def isEmpty = true
  def length = 0
}

class Cons(val head: Tweet, val tail: TweetList) extends TweetList {
  def isEmpty = false
  def length = tail.length + 1
}


object GoogleVsApple {
  val google = List("android", "Android", "galaxy", "Galaxy", "nexus", "Nexus")
  val apple = List("ios", "iOS", "iphone", "iPhone", "ipad", "iPad")

  def filterTweetsByKeysInText(keys: List[String]): TweetSet = {
    TweetLoader.allTweets.filter(t => keys.exists(key => t.text.contains(key)))
  }

  lazy val googleTweets: TweetSet = filterTweetsByKeysInText(google)
  lazy val appleTweets: TweetSet = filterTweetsByKeysInText(apple)

  lazy val trending: TweetList = googleTweets.union(appleTweets).descendingByRetweet
}

object Main {
  def main(args: Array[String]): Unit = {
    GoogleVsApple.trending foreach println
  }
}