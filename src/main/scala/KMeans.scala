

class KMeans(private val k: Int, private val points: List[Point2D], maxIter: Int = 100) {

  //Variables

  private var clusters: List[Cluster] = _


  //Methods

  this.generateClusters()

  private def generateClusters() = {
    val xs = points.map( point => point.X.toInt)
    val ys = points.map( point => point.Y.toInt)
    val minX = xs.min
    val maxX = xs.max
    val minY = ys.min
    val maxY = ys.max
    this.clusters = (1 to this.k).map(index => new Cluster(Utils.randFloat(minX, maxX), Utils.randFloat(minY, maxY), index)).toList

  }

  def affect() = {
    points.map( point => point.affectTo(this.clusters) )
  }

  def resetPointsOfCluster () = {
    this.clusters.map( cluster => cluster.reset() )
  }

  def updateClustersAverage() = {
    this.clusters.map( cluster => cluster.updateAverage() )
  }

  def cost = this.points.map(point => point.distance(point.cluster.getMoyenne)).sum

  def run() = {
    val i = 0
    this.affect()
    var previousCost = cost
    do  {
      previousCost = cost
      this.affect()
      this.updateClustersAverage()
      this.resetPointsOfCluster()
      println(cost)
      i+=1
    } while (i < maxIter && previousCost - cost != 0)
    cost
  }

}