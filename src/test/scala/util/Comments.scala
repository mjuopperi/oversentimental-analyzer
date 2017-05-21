package util

import java.time.Instant

import reddit.Comment


object Comments {
  def toComment(body: String) = Comment("t1_cmntid1", body, Instant.now.getEpochSecond)
}
