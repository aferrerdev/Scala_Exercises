/**
 * Created by Alex on 01/12/2015.
 */
object Exercises2 extends scala.App{
  /* ------------------------------------------------------------------------------------------------------------------
  -- Implement one function in high level named filter that accept two parameters:
  -- The first is a function with one polymorphic parameter that return one boolean value.
  -- The second is one polymorphic list
  -- The filter function return one list forming by elements of th original list
  -- The elements of this list has to accomplish the predicate established by function (first parameter)
  -- Example: filter (isLessThan 3) [1,7,2,9,67,3];*/
  println("\n LLista 2 Exercicis: \n Exercici 1: implementar una funcion en alto orden llamada filter.")
  def filter[T](f:(T => Boolean), lista:List[T]):List[T] = lista match {
      case Nil => Nil
      case x :: lista => {
          if(f(x))
            x::filter(f,lista)
          else
            filter(f,lista)
      }
  }
  var lista = List(1,7,2,9,67,3)
  // Definim la funcio que volem filtrar a la llista:
  def isLessThan3(x:Int):Boolean ={
    if(x < 3)
      true
    else
      false
  }
  // Li passem la funci� creada.
  var lista_filtrada = filter(isLessThan3, lista)
  println(lista_filtrada)
  // Podem definir la funcio a comprovar en el filter dins de la crida a filter.
  lista_filtrada = filter((x:Int) => if(x < 3) true else false, lista)
  println(lista_filtrada)


/* ------------------------------------------------------------------------------------------------------------------
  -- Implement ZipWith function
  --zipWith:: (a -> b -> c) -> [a] -> [b] -> [c]
  --addition x y = x+y
  --Example: zipWith addition [1,2,3] [4,5,6]
  --[5,7,9]*/
  println("\n Exercici 2: implementar zipwith function.")
  def addition(x:Int, y:Int):Int ={
    x+y
  }
  //list1.map(x=>addition())
  def zipWith(f:((Int,Int) => Int), list1:List[Int], list2:List[Int]):List[Int] = list1 match {
      case Nil => Nil
      case x :: list1 =>{
        addition(x,list2.head)::zipWith(f,list1,list2.tail)
      }
  }
  var list1 = List(1,2,3)
  var list2 = List(4,5,6)
  var lista_final = zipWith(addition,list1,list2)
  lista_final.foreach(println)

/* ------------------------------------------------------------------------------------------------------------------
  -- Create 'divide' function that receive one list 'xs' and a condition 'f' and return (ys, zs).  Where
  -- 'ys' = first elements that accomplish condition
  -- 'zs' = rest of elements
  --Example: divide (<3) [1..5]
  --([1,2],[3,4,5]).*/
  println("\n Exercici 3: divide(xs,function) function.")
  def divide(lista:List[Int],f:(Int=>Boolean)):(List[Int],List[Int]) = lista match {
    case Nil => (Nil, Nil)
    case x :: xs => {
        if(f(x) == true)
          (x::divide(xs,f)._1,divide(xs,f)._2)
        else
          (divide(xs,f)._1,x::divide(xs,f)._2)
    }
  }
  var result = divide(List(1,2,3,4,5),isLessThan3)
  println(result)

/* ------------------------------------------------------------------------------------------------------------------
  -- Implement "foldr_filter" that calculate one operation on all elements of the list that accomplish a specific condition
  -- foldr_filter has the form:  <foldr_filter  condition  operation initial_value  list>
  -- ej. foldr_filter (>4) (+) 0 [3,6,4,5] --> 11
  -- It add 5 and 6*/
  println("\n Exercici 4: fold_filter function.")
  def major4(number:Int):Boolean = if(number > 4) true else false
  def sum(x:Int,y:Int):Int = x + y
  def foldr_filter(f:(Int => Boolean),op:((Int,Int)=>Int), initial:Int, lista:List[Int]):Int = lista match {
    case Nil => initial
    case x :: xs =>{
        if(f(x))
            foldr_filter(f,op,initial+x,xs)
        else
            foldr_filter(f,op,initial,xs)
    }
  }
  println(foldr_filter(major4,sum,0,List(3,6,4,5)));

//-- EXERCISES MAP, FILTER, FOLDR:
/* ------------------------------------------------------------------------------------------------------------------
  -- Create "count_car" function that contains like parameter one character and one list of strings and
  -- return the number of times that appear this character in string but that begin by this character.
  -- All strings are in lower case. Do that using MAP, FILTER AND FOLDR.
  -- ej . Main> count_car 'e' ["el","examen","esta","especialmente","escogido","entre","los","mas","elementales"]
  -- 15 :: Integer*/
  println("\n Exercici 5: MAP/FILTER/FOLDR count_character function")
  // MAP
  def count_car_map(caracter:Char, lista:List[String]):Int = lista match {
      case Nil => 0
      case x :: xs =>{
          x.count(_ == caracter) + count_car_map(caracter,xs)
      }
  }
  val listaStrings:List[String] = List("el","examen","esta","especialmente","escogido","entre","los","mas","elementales")

  // MAP personal
  println("Resultado count_car_map: "+ count_car_map('e',listaStrings))

  // MAP Scala
  println("Resultado map Scala: "+ listaStrings.map(palabra => palabra.count(_ == 'e')).sum)

  // Filter Scala
  println("Resultado filter Scala: "+ listaStrings.filter(palabra => if(palabra.contains('e')) true else false))

  // FoldScala
  println("Resultado foldLeft Scala: "+ listaStrings.foldLeft(0) ((number:Int, palabra:String)=> number + palabra.count(_ == 'e')))
  println("Resultado foldRight Scala: "+ listaStrings.foldRight(0) ((palabra:String, number:Int)=> number + palabra.count(_ == 'e')))

/* ------------------------------------------------------------------------------------------------------------------
  -- Create "remove_primes" that receive one number and integer list and
  -- return the result of subtract all prime numbers that appear in list to the number.
  -- Use MAP, FILTER and FOLDR and "factorize" function as follows:
  -- Clue: The prime numbers are numbers whose length factoring is 1.
  -- ex. Main> remove_primes 100 [2,3,4,5,6,7,8,9,10]
  --           83 :: Integer*/


/* ------------------------------------------------------------------------------------------------------------------

  -- Using map, foldr and filter functions, create a "invest_word" that receive a text and
  -- return a new text with all words invested in its center.
  -- Use "invest_center" function.

  -- ex. Main> invest_word ["este","examen","de","haskell","es","muy","facil"]
  --     "etse eemaxn de hleksal es muy fical" :: [Char]

  -- ex. Main> invest_word ["dicen","que","si","tenemos","un","texto","con","las","palabras","separadas","y","cambiamos","el","orden","interno","manteniendo","la","primera","y","ultima","letra","podemos","ser","capaces","de","leerlo","entendiendo","el","mensaje"]
  -- "decin que si tomenes un ttxeo con las parbalas sadarapes y comaibmas el oedrn inretno mdneinetnao la premira y umitla lrtea pomedos ser cecapas de llreeo edneidnetno el mjasnee" :: [Char]*/


/* ------------------------------------------------------------------------------------------------------------------
  -- Using map, filter and foldr functions, create "operate_if" function that receive:
  -- one operation,
  -- initial value for operation,
  -- one condition that depends of two variables,
  -- one initial number for comparison and
  -- one list of binary numbers.
  -- This function operate all of binary numbers of the list if the numbers accomplish the condition.*/
/*
  -- ex. Main> operate_if (+) 0 (>) 3 ["10","11","111","1110"]
  -- Addition of higher numbers than 3
  -- "10101" :: [Char]
  -- "111" + "1110" = "10101" (7+14=21)

  -- ex. Main> operate_if (*) 1 (\x y -> (mod x y) == 0) 2 ["10","11","111","1110"]
  -- Multiply pairs
  -- "11100" :: [Char]
  -- "10" * "1110" = "11100" (2*14=28)*/

//-- EXERCISES: APPLIED PARTIALLY FUNCTIONS
/* ------------------------------------------------------------------------------------------------------------------
// -- Parametrize partly power function in order to calculate cube function*/


/* ------------------------------------------------------------------------------------------------------------------
  -- Test map function with different functions: Increase on one, multiply times 2, ...
  -- Create map_inc function in high level that parametrize partly the map.*/


/* ------------------------------------------------------------------------------------------------------------------
  -- Implement "add_pairs" parametrizing partly foldr_filter in order to that add couple numbers in one list. (Always begin from 0)
  -- ej. add_pairs [2,4,5,6]  --> 12  // It add 2,4 and 6*/


/* ------------------------------------------------------------------------------------------------------------------
  -- Implement "put_zeros" function that receive one number and return one 1 followed by number of 0 that is indicated.
  -- Make it parametrizing partly the function "power".
  -- ex.   Main> put_zeros 4
  --             10000 :: Integer*/


/* ------------------------------------------------------------------------------------------------------------------
  -- Implement "equal_pos" function that receive two lists of integers (with the same number of elements) and return:
  -- those numbers that appear duplicate in the same position
  -- Or one 0 in those positions was different
  -- Make it parametrizing partly the function "power".
  -- Main> equal_pos [2,3,4,5] [2,4,3,5]
  --       [2,0,0,5] :: [Integer]*/


/* ------------------------------------------------------------------------------------------------------------------
  -- Create "count_to" function that receive one list of strings and return the addition of elements 'a'
  -- but only the words that begin with 'a'. Make it parametrizing partly the function "power".
  -- ej. Main> count_to ["un","anillo","para","atraerlos","a","todos","y","atarlos","en","las","tinieblas"]
  --     6 :: Integer */

/* ------------------------------------------------------------------------------------------------------------------
-- Define "less_hundred_primes" parametrizing partly the function "remove_primes" that it remove prime numbers of 100.
-- ej. Main> less_hundred_primes [2,3,4,5,6]
--     90 :: Integer */

}
