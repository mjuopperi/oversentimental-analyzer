package reddit

import org.scalatest.{Matchers, WordSpec}
import util.ResourceReader


class CommentParsingSpec extends WordSpec with Matchers {

  private lazy val commentJson = ResourceReader.readJsonResource("comment.json")
  private lazy val commentsJson = ResourceReader.readJsonResource("comments.json")

  "Comment parser" should {
    "parse single comment" in {
      val expectedComment = Comment("t1_cmntid1", "Comment 1 body.", 1495275131)

      Comment.parse(commentJson) should be(Some(expectedComment))
    }

    "parse json result to Comments" in {
      val expectedComments = (1 to 5).map(i =>
        Comment(s"t1_cmntid$i", s"Comment $i body.", 1495275130 + i))

      val comments = Comment.fromData(commentsJson)
      comments should have length 5
      comments should contain allElementsOf expectedComments
    }
  }
}
