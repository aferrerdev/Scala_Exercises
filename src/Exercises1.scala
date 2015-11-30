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
  println("\n Exercise 4 list_between:")
  lista = List(0,1,2,4,5,240)

  def list_between(begin:Int, end:Int, list:List[Int]):List[Int] = list match {
      case Nil => Nil
      case front :: list => {
          if (front > begin && front < end)
              front :: list_between(begin, end, list)
          else
              list_between(begin, end, list)
      }
  }
  list_between(0,4,lista).foreach(println)


  // Modify exercise 4 in order to only print the multiple of 2.

  println("\n Exercise 4 Modified:")
  lista = List(0,1,2,4,5,240)

  def list_between2(begin:Int, end:Int, list:List[Int]):List[Int] = list match {
    case Nil => Nil
    case front :: list => {
      if (front > begin && front < end)
        front :: list_between(begin, end, list).filter(x => x%2 == 0)
      else
        list_between(begin, end, list).filter(x => x%2 == 0)
    }
  }
  list_between2(0,5,lista).foreach(println) // .filter(x => x%2 == 0).foreach(println)


  /* Implement "digit" function that receives an integer and return a list of digits.
     You do by acumulative and stack recursion. */
  // Stack Recursion
  // ex. digit 3645 --> [3,6,4,5]
  println("\n Exercise 5 digit() with Stack Recursion:")
  def digit_stck(number:Int):List[Int] = number match{
    case 0 => Nil
    case x =>{
      x % 10 ::  digit_stck(x / 10)
    }
  }
  digit_stck(3645).foreach(println)

  // Accumulate
  println("\n Exercise 5 digit() with Accumulate Recursion:")
  def digit_accumulate(lista:List[Int], number:Int):List[Int] = number match{
    case 0 => lista
    case x =>{
      digit_accumulate(x % 10 :: lista, x / 10)
    }
  }
  lista = List();
  digit_accumulate(lista, 3645).foreach(println)


  /* Implement "add_div" function that return the addition of all its divisors but excluding himself.
     You do by cumulative and stack recursion.*/

  // Stack recursion.
  println("\n Exercise 6 add_div() with Stack Recursion:")
  def add_div_stack(divisor:Int, number:Int):Int = divisor match{
    case 0 => 0
    case x =>{
      if(number % x == 0 && number != x)
        x + add_div_stack(x-1, number)
      else
        add_div_stack(x-1, number)
    }
  }
  println(add_div_stack(2,8))

  // Acumulative recursion.
  println("\n Exercise 6 add_div() with Accumulate Recursion:")
  def add_div_acc(suma:Int, divisor:Int, number:Int):Int = divisor match{
    case 0 => suma
    case x =>{
        add_div_acc((
          if(number % x == 0 && number != x)
            x + suma
          else
            suma), x-1, number)
    }
  }
  var suma = 0;
  println(add_div_acc(suma,2,8))


  // Implement function "power" that receive one integer and the number that we want to raise.
  // It returns the result of applying the operation.
  // You have to make this function by stack recursion.
  // ex.   Main> power 3 4
  // 81 :: Integer
  def power(number:Int, toRise:Int):Int = toRise match{
      case 0 => 1
      case x => number * power(number,x-1)
  }
  println("\n Exercise 7 power()")
  println(power(2,4))


  // Implement a central function that receives a list by parameter and return one structure with three elements:
  // The first is the first element of the list.
  // The second is a list whose elements are lower than first element.
  // The third is a list whose elements are higher than fist element.
  // Use cumulative recursion. The elements has to keep the initial order.
  // ex.   Main> central "362741"
  // ('3',"21","674") :: (Char,[Char],[Char])


  def central(lista:List[Char]):(Char,List[Char],List[Char]) = lista match{
      case Nil => (0,Nil,Nil)
      case x :: xs =>{
          (x,Nil,Nil)
      }
  }




  // Implement "hexadecimal" function that receivse an integer and change to it corresponding hexadecimal number.
  // (number in base 16 in the form of string).
  // You can use 'hexa x' function. From decimal number it return the string with his corresponding decimal number.
  // Use cumulative recursion.
  // ex. Main > hexadecimal 200
  //            "c8" :: [Char]
  // ex. Main > hexadecimal 0
  //            "0" :: [Char]




  // Implement "decimal" function that receives a String in form of hexadecimal number.
  // and return an integer with his corresponding decimal number.
  // You can use 'deci x' function. From one string it return his decimal number associated.
  // Use stack recursion.




  // CLUE:  You can use mirror function for change the order of String
  // ex. Main > decimal "3a2"
  //            930 :: Integer



  // Implement "prime" function that receives one integer and return a boolean that indicate if the number is prime or not.
  // ex. Main> prime 127
  //     True :: Bool




  // Implement "factorize" function that receives one integer number ans return a list with his ordered factoring.
  // (the list of minimum prime numbers that it can factorize).
  // ej. Main> factorize 8
  //     [2,2,2] :: [Integer]
  // ej. Main> factorize 100
  //     [2,2,5,5] :: [Integer]




  // Implement expand_pow_2" that receives a integer list with at least one element.
  // If size of the list isn't equal to a power of 2 (0,1,2,4,8,16,...). It expand the list adding 0 until
  // it has power of 2 size. -- You can use "length" function of the system. (2 points)

  //  ex. Main> expand_pow_2 [1,2,3,4]
  //          [1,2,3,4] :: [Integer]
  //  ex. Main> expand_pow_2 [1,2,3,4,5]
  //          [1,2,3,4,5,0,0,0] :: [Integer]




  // Implement "order" function that receives a list of strings and return a list with all elements order alphabetically.
  // ex. Main> order_alph ["enjoy","this","day","until","one","exam","ruined","it"]
  //            [ "day", "enjoy", "exam", "it", " one", "ruined", "this","until"] :: [[Char]]



  // Create "fact_stack" and "fact_cum" functions that receives an integer and return the result of apply factorial function.
  // The first is in stack recursion and the second is in cumulative recursion.
  // ej. Main> fact_stack 4
  //      24 :: Integer
  //  ej. Main> fact_cum 6
  //      720 :: Integer




  // Create "inverse_center" function that receives a String and return a new string that the first and
  // last letter keep in its position but the rest was reversed.
  // ex. Main> inverse_center "haskell"
  //      "hleksal" :: [Char]
  // ex. Main> inverse_center "exam"
  //      "eaxm" :: [Char]




  // Implement "binary" function that receive one integer and it convert the integer in its corresponding binary number
  // (number in base 2 as String)
  // You can use 'char x' function that receive one number end return its character.
  // Use cumulative recursion.
  // ex. Main> binary 12
  //     "1100" :: [Char]



  // Implement "decimal" function that receives an String, in form of binary number, and return an integer with its corresponding decimal number.
  // You can use 'int x' function that receives a character and return its associated name.
  // Use stack recursion
  // ex. Main> decimal "0"
  //    0 :: Integer
  // ex. Main> decimal "101011"
  //     43 :: Integer




  /* Implement "addition_pow2" thar receives one number and return the same number in form of power of 2.
   For simplify, we suppose that the value of higher power is 9
    -- ex. Main> addition_pow2 0
    --     "0" :: [Char]
    -- ex. Main> addition_pow2 9
    --     "2^3 + 2^0" :: [Char]
    -- ex. Main> addition_pow2 13
    --     "2^3 + 2^2 + 2^0" :: [Char]*/




  /* Implement "next_prime" function that receives one integer and return the next prime number.
    -- If we introduce one prime number, the result will be the next prime number, never the same number.

    -- ex. Main> next_prime 127
    --     131 :: Integer */




  /* Implement "order_letters" that receives one string and return other string with letters ordering alphabetically, removing blank space.

    -- ex. Main> order_letter "The algorithm of order in Haskell is very important"
    -- "aaadeeeefghhhiiiiklllmmnnooooprrrrrssttttvy" :: [Char]*/









}
