import smile.read


object Test {
  def main(args: Array[String]) = {
  val iris = read.arff("data.arff")


    val y = iris("class").toIntArray


    val irisData = iris.select(2, 3).toArray.toList.map(_.toList)
    val points = irisData.map(value => new Point2D(value(0).toFloat, value(1).toFloat))

    val kmeans = new KMeans(3, points)

    kmeans.visualizeTraining(y);

  }

}
