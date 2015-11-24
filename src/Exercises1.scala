/**
 * Created by Alex on 24/11/2015.
 * Exercises using recursion.
 */
object Exercises1 extends scala.App
{
    // Write a stack recursive function lower3(list) that adds all numbers whose values are lower than 3 and they appear
    // in one integer list.
    var sum = 0;
    var lista = List(0,1,2,4,5,6)

    def lower3(list:List[Int]):Int = list match {
        case Nil => 0
        case x :: list => if(x<3) x + lower3(list) else lower3(list)
    }
    println("\n Exercise 1:")
    println(lower3(lista))

    // Write a tail recursive function that calculates the higher element of integer list.


    // Create list_integers(n, list) function. It return one list with integers including between 0 and n.
    def list_integers(n:Int, list:List[Int]):List[Int] = list match {
        case Nil => Nil
        case x :: list => if(x > 0 && x < n) x::list_integers(n,list) else list_integers(n,list)
    }
    println("\n Exercise 2:")
    var list = list_integers(6,lista)
    list.foreach(println)


    // Create list_between(begin, end, listX) function. It return one list that contain integers between begin
    // and end of list listX.
}
