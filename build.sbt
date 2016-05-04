organization	:= "de.heikoseeberger"
name			:= "scala-train"
version			:= "2.0.0"

scalaVersion	:= "2.11.8"
scalacOptions	:= List(
  "-unchecked",
  "-deprecation",
  "-target:jvm-1.8"
)

initialCommands := "import de.heikoseeberger.scalatrain._"

import scalariform.formatter.preferences._
SbtScalariform.autoImport.preferences := SbtScalariform.autoImport.preferences.value
  .setPreference(AlignSingleLineCaseStatements, true)
  .setPreference(AlignSingleLineCaseStatements.MaxArrowIndent, 100)
  .setPreference(DoubleIndentClassDeclaration, true)

/*
  fullClasspath in Compile in console += Attributed.blank(file("C:/Program Files/Java/jdk1.8.0_92/lib/tools.jar"))
 */