/**
 * Created by Alex on 24/11/2015.
 * Exercises using recursion.
 */
object Exercises1 extends scala.App
{
    // =================================================================================================================
    // Write a stack recursive function lower3(list) that adds all numbers whose values are lower than 3 and they appear
    // in one integer list.
    var lista = List(0,1,2,4,5,6)

    // Funcion de pila con pattermatching
    def lower3_pila_patter(list:List[Int]):Int = list match {
        case Nil => 0
        case x :: list => if(x<3) x + lower3_pila_patter(list) else lower3_pila_patter(list)
    }
    println("\n Exercise 1:")
    println(lower3_pila_patter(lista))

    // =================================================================================================================
    // Tail recursive function = funcion recursiva acumulativa
    // Write a tail recursive function that calculates the higher element of integer list.
    def maximoLista(max:Int, list:List[Int]):Int = list match{
        case Nil => max
        case x :: list => if(x > max) maximoLista(x,list) else maximoLista(max,list)
    }

    var max = 0;
    lista = List(0,1,2,4,5,240)
    println("\n Exercise 2: maximoLista")
    println(maximoLista(max, lista))


    // =================================================================================================================
    // Create list_integers(n, list) function. It return one list with integers including between 0 and n.
    def list_integers(n:Int, list:List[Int]):List[Int] = list match {
        case Nil => Nil
        case x :: list => if(x > 0 && x < n) x::list_integers(n,list) else list_integers(n,list)
    }
    println("\n Exercise 3 list integers between 0 and n:")
    var list = list_integers(6,lista)
    list.foreach(println)

    // =================================================================================================================
    // Create list_between(begin, end, listX) function. It return one list that contain integers between begin
    // and end of list listX.
}
