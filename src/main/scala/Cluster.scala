

class Cluster (x: Float, y: Float, val id: Int) {

  //Variables

  var m_points: List[Point2D] = List()
  private var m_couleur: String = _
  private var m_moyenne: Point2D = new Point2D(x, y)

  //Getters

  def getId(): Int = this.id

  def getCouleur: String= this.m_couleur

  def getPoints: List[Point2D] = this.m_points

  def getMoyenne: Point2D = this.m_moyenne

  //Setters

  def setCouleur (a_color: String): Unit = {this.m_couleur = a_color}

  def setPoints (pointsList: List[Point2D]) = this.m_points = pointsList

  def setMoyenne (newAverage: Point2D) = this.m_moyenne = newAverage

  //Methodes

  def reset() = {
    this.m_points = List()
  }


  def updateAverage(): Unit = {
    val xs = this.m_points.map( point => point.X )
    val ys = this.m_points.map( point => point.Y )
    val x = xs.sum / xs.length
    val y = ys.sum / xs.length
    this.m_moyenne = new Point2D(x, y)
  }

  //Fonction toString
  
  override def toString: String = s"Cluster(${this.m_moyenne.X}, ${this.m_moyenne.Y})"

}