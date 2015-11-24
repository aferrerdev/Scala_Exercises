import scala.collection.mutable.ListBuffer

/**
 * Created by Alex on 10/11/2015.
 */
object RunFileSystem extends scala.App{
  val root: Directory = new Directory("root")
  val home: Directory = new Directory("home")
  val tap: Directory = new Directory("tap")
  val f1: File = new File("f1", 1234)
  val f2: File = new File("f2", 3445)
  val f3: File = new File("f3", 6688)
  val f4: File = new File("lp", 99999)
  root.addChild(home)
  root.addChild(f1)
  home.addChild(tap)
  home.addChild(f2)
  tap.addChild(f3)
  root.addChild(f4)



  println("-------------")

  println("Root SIZE:" + root.size)
  println("Home SIZE:" + home.size)
  println("TAP SIZE:" + tap.size)

}

// Definicio dels objectes AComponent, File i Directory
trait AComponent{
  def size:Int
}

/**
 * File Object.
 * @param name
 * @param amount
 */
class File(val name:String, val amount:Int) extends AComponent
{
  override def size = amount
}

/**
 * Directory Object.
 * @param name
 */
class Directory(val name:String) extends AComponent{
  private var children:ListBuffer[AComponent]=new ListBuffer[AComponent]()

  def addChild(child:AComponent): Unit ={
      children+=child
  }
  def removeChild(child:AComponent):Unit={
      children-=child
  }
  override def size={
      children.map(_.size).sum
  }
}