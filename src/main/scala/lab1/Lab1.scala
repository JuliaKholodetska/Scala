package lab1

object Lab1 {
  /**Pascal triangle*/
  def pascal(column: Int, row: Int): Int = {
    if(column == 0 || row == column) 1
    else pascal(column - 1, row - 1) + pascal(column, row - 1)
  }

  /** Balance )*/
  def balance(chars: List[Char]): Boolean = {
    def balanced(chars: List[Char], open: Int): Boolean = {
      if (chars.isEmpty) open == 0
      else if (chars.head == '(') balanced(chars.tail, open + 1)
      else if (chars.head == ')') open > 0 && balanced(chars.tail, open - 1)
      else balanced(chars.tail, open)
    }
    balanced(chars,0)
  }

  /**Counts*/
  def countChange(money: Int, coins: List[Int]): Int = {
    if (money < 0 || coins.isEmpty) 0
    else if (money == 0) 1
    else countChange(money - coins.head, coins) + countChange(money, coins.tail)
  }
}
