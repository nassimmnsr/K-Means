import java.awt.{Color, GridLayout}
import javax.swing.JFrame
import smile.plot.swing.plot


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
    println(s"clusters $clusters")
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




  //Visualisation
  def visualizeTraining(knownClusters: Array[Int]): Unit = {
    val signs = Array('*','#','Q','*','*')
    val colors =  Array(Color.BLUE, Color.RED, Color.BLACK, Color.CYAN, Color.DARK_GRAY, Color.GRAY, Color.GREEN)
    val frame = new JFrame("Kmeans")
    val values = this.points.map(value => value.getCoordinates).toArray
    val maincanvas = plot(values, knownClusters, signs, colors)
    maincanvas.setTitle("Known Iris clusters")

    frame.setLayout(new GridLayout())
    frame.add(maincanvas)
    frame.setVisible(true)
    frame.setSize(1500, 750)


    val nonTrainedClusters = this.points.map( _ => 0).toArray


    var trainingCanvas = plot(values, nonTrainedClusters, signs, colors)


    trainingCanvas.setTitle("K-means classification developped by Nassim MANSOUR")


    frame.add(trainingCanvas)
    frame.revalidate()
    Thread.sleep(1000)

    var i = 0
    this.affect()
    var previousCost = cost

    do  {

      frame.remove(trainingCanvas)
      previousCost = cost

      this.affect()
      this.updateClustersAverage()
      this.resetPointsOfCluster()

      println(cost)

      val clustersTable = this.points.map(point => point.cluster.getId()).toArray

      trainingCanvas = plot(values, clustersTable, signs, colors)
      trainingCanvas.setTitle("K-means classification by Nassim MANSOUR")
      frame.add(trainingCanvas)
      frame.revalidate()
      Thread.sleep(1000)

      i+=1
    } while (i < maxIter && Utils.~=(previousCost, cost, 0.00000000000001) == false)

    println("DONE")

  }







}