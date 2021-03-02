name := "Kmeans"

version := "0.1"

scalaVersion := "2.13.5"

//libraryDependencies += "com.github.haifengl" %% "smile-scala" % "2.6.0"
libraryDependencies ++= Seq(
  "com.github.haifengl" % "smile-core" % "2.1.0",
  "com.github.haifengl" %% "smile-scala" % "2.1.0"
)
