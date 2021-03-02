import scala.collection.mutable.ListBuffer
import scala.math.pow

class Point2D(private val x: Float, private val y: Float) {

  //Variables

  private var m_cluster: Cluster = _

  //Getters

  def X: Float = this.x

  def Y: Float = this.y

  def cluster: Cluster = this.m_cluster

  def getCoordinates = Array(x.toDouble, y.toDouble)

  //Setters

  def distance (point: Point2D) = {
    pow( this.x - point.x, 2 ) + pow(this.y - point.y, 2)
  }

  //Methods

  def myClusterAndDistanceF(clustersAndDistances: List[(Cluster, Double)]) = {
    val distances = new ListBuffer[Double]()
    clustersAndDistances.foreach(distances += _._2)

    val distancemin = distances.reduce((a, b) => a min b)

    val cluster = clustersAndDistances.filter(_._2 == distancemin)

    ((cluster(0)._1), distancemin)

  }


  def affectTo(clusters: List[Cluster]) = {
    val myClustersAndDistances = clusters.map(cluster => { ( cluster, this.distance(cluster.getMoyenne) ) } )
    val myClusterAndDistance = myClusterAndDistanceF(myClustersAndDistances)

    this.m_cluster = myClusterAndDistance._1
    this.m_cluster.m_points = this :: this.m_cluster.m_points
  }


  //Fonction toString

  override def toString: String = s"Point2D($x, $y)"

}

