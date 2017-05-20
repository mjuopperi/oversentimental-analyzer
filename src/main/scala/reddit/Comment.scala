package reddit
import play.api.libs.json._
import play.api.libs.json.Reads._
import play.api.libs.functional.syntax._


case class Comment(id: String, body: String, created: Long)

object Comment {
  implicit val commentReads: Reads[Comment] = (
    (JsPath \ "name").read[String] and
    (JsPath \ "body").read[String] and
    (JsPath \ "created_utc").read[Long]
  )(Comment.apply _)

  def parse(json: JsValue) = json.validate[Comment].asOpt

  def fromData(json: JsValue): List[Comment] = {
    val comments = for {
      commentList <- (json \ "data" \ "children").asOpt[List[JsValue]]
      commentData = commentList.flatMap(comment => (comment \ "data").toOption)
    } yield commentData.flatMap(parse)
    comments.getOrElse(Nil)
  }
}
