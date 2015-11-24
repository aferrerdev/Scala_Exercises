/**
 * Created by Alex on 24/11/2015.
 * Exercises using recursion.
 */
object Exercises1 extends scala.App
{
   // Write a stack recursive function lower3(list) that adds all numbers whose values are lower than 3 and they appear
   // in one integer list.
    var sum = 0;
    var lista = List(1,2,4,5,6)

    def lower3(xs:List[Int]):Int = xs match {
        case Nil => 0
        case x :: xs => if(x<3) x + lower3(xs) else lower3(xs)
    }
    println(lower3(lista))


}
