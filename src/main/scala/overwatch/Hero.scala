package overwatch

object Hero extends Enumeration {
  type Hero = Value

  val ANA         = Value("Ana")
  val BASTION     = Value("Bastion")
  val DVA         = Value("Dva")
  val GENJI       = Value("Genji")
  val HANZO       = Value("Hanzo")
  val JUNKRAT     = Value("Junkrat")
  val LÚCIO       = Value("Lúcio")
  val MCCREE      = Value("Mccree")
  val MEI         = Value("Mei")
  val MERCY       = Value("Mercy")
  val PHARAH      = Value("Pharah")
  val REAPER      = Value("Reaper")
  val REINHARDT   = Value("Reinhardt")
  val ROADHOG     = Value("Roadhog")
  val SOLDIER76   = Value("Soldier76")
  val SOMBRA      = Value("Sombra")
  val SYMMETRA    = Value("Symmetra")
  val TORBJÖRN    = Value("Torbjörn")
  val TRACER      = Value("Tracer")
  val WIDOWMAKER  = Value("Widowmaker")
  val WINSTON     = Value("Winston")
  val ZARYA       = Value("Zarya")
  val ZENYATTA    = Value("Zenyatta")
}

object HeroAliases {
  import Hero._
  lazy val heroAliases = Map(
    (ANA,         List("ana")),
    (BASTION,     List("bastion", "basti")),
    (DVA,         List("dva", "d.va", "diva")),
    (GENJI,       List("genji")),
    (HANZO,       List("hanzo")),
    (JUNKRAT,     List("junkrat", "junk", "junker", "rat")),
    (LÚCIO,       List("lúcio", "lucio")),
    (MCCREE,      List("mccree", "mc cree")),
    (MEI,         List("mei")),
    (MERCY,       List("mercy")),
    (PHARAH,      List("pharah", "phara")),
    (REAPER,      List("reaper")),
    (REINHARDT,   List("reinhardt", "rein")),
    (ROADHOG,     List("roadhog", "hog", "pudge")),
    (SOLDIER76,   List("soldier76", "soldier: 76", "soldier 76", "soldier", "76")),
    (SOMBRA,      List("sombra")),
    (SYMMETRA,    List("symmetra")),
    (TORBJÖRN,    List("torbjörn", "torbjorn", "toblerone")),
    (TRACER,      List("tracer")),
    (WIDOWMAKER,  List("widowmaker", "widow")),
    (WINSTON,     List("winston")),
    (ZARYA,       List("zarya")),
    (ZENYATTA,    List("zenyatta", "zeny", "zen"))
  )
}
