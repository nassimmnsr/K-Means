import scala.util.Random


object Utils {

  def randFloat(start: Int, end: Int)= {
    val rand = new Random()
    val gap = end - start
    rand.nextFloat() * gap + start
  }

}
