/**
 * Created by alexferrerlopez on 12/12/15.
 */
object Reduce {

  def preduce[T](result:T,f:(T,T) => T, xs:List[T]):T = xs match {
    case Nil => result
    case x :: xs => f(x,preduce(result,f,xs))
  }
  def sum (x:Int,y:Int) = x + y

  def main(args: Array[String]) {
    // Normal version
    val sumr = preduce(0,sum,List(1,2,3))
    println(sumr)

    // Curry version
    val result = preduce(0,sum, _:List[Int])
    println (result(List(1,2,3)))

    // With clousure inside
    println(preduce (0,(x:Int,y:Int)=>x+y,List(1,2,3)))

    // EXAMPLES foldRight and foldLeft
    def mult (x:Int,y:Int) = x * y
    println(List(1,2,3,4).foldRight (1) (mult))

    def concatenate (x:String,y:String) = x + y
    println(List("Pat","a","t","a").foldLeft ("Inicial") (concatenate))

  }
}
