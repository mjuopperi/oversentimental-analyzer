package util

import play.api.libs.json.Json
import scala.io.Source

object ResourceReader {

  private def readResource(filename: String) = Source.fromResource(filename)

  def readJsonResource(filename: String) = Json.parse(readResource(filename).mkString)

}
