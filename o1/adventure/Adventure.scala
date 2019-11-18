package o1.adventure


/** The class `Adventure` represents text adventure games. An adventure consists of a player and
  * a number of areas that make up the game world. It provides methods for playing the game one
  * turn at a time and for checking the state of the game.
  *
  * N.B. This version of the class has a lot of "hard-coded" information which pertain to a very
  * specific adventure game that involves a small trip through a twisted forest. All newly created
  * instances of class `Adventure` are identical to each other. To create other kinds of adventure
  * games, you will need to modify or replace the source code of this class. */
class Adventure {

  /** The title of the adventure game. */
  val title = ???

  private val Oster        = new Area("Oster", "")
  private val Kyiv         = new Area("Kyiv", "")
  private val Chernihiv    = new Area("Chernihiv", "")
  private val Zhytomyr     = new Area("Zhytomyr", "")
  private val Ovruch       = new Area("Ovruch", "")
  private val Zvenyhorod   = new Area("Zvenyhorod", "")
  private val Vinnytsia    = new Area("Vinnytsia", "")
  private val Bratslav     = new Area("Bratslav", "")
  private val Letychiv     = new Area("Letychiv", "")
  private val Kamianets    = new Area("Kamianets", "")
  private val Chervonohrad = new Area("Chervonohrad", "")
  private val Lutsk        = new Area("Lutsk", "")
  private val Volodymyr    = new Area("Volodymyr", "")
  private val Busk         = new Area("Busk", "")
  private val Belz         = new Area("Belz", "")
  private val Halych       = new Area("Halych", "")
  private val Lviv         = new Area("Lviv", "")
  private val Zhydachiv    = new Area("Zhydachiv", "")
  private val Stryi        = new Area("Stryi", "")
  private val Sambir       = new Area("Sambir", "")
  private val Kolomyia     = new Area("Kolomyia", "")
  private val Drohobych    = new Area("Drohobych", "")
  private val destination = ???

         Oster.setNeighbors(Vector("north" -> Chernihiv,     "west" -> Kyiv))
          Kyiv.setNeighbors(Vector("northeast" -> Chernihiv, "east" -> Oster,             "southwest" -> Zvenyhorod, "west" -> Zhytomyr))
     Chernihiv.setNeighbors(Vector("south" -> Oster,         "southwest" -> Kyiv))
      Zhytomyr.setNeighbors(Vector("north" -> Ovruch,        "east" -> Kyiv,              "south" -> Zvenyhorod,     "southwest" -> Vinnytsia, "west" -> Kremenets, "northwest" -> Lutsk))
        Ovruch.setNeighbors(Vector("south" -> Zhytomyr,      "southwest" -> Lutsk))
    Zvenyhorod.setNeighbors(Vector("north" -> Zhytomyr,      "northeast" -> Kyiv,         "southwest" -> Bratslav,   "west" -> Vinnytsia))
     Vinnytsia.setNeighbors(Vector("northeast" -> Zhytomyr,  "east" -> Zvenyhorod,        "south" -> Bratslav,       "southwest" -> Letychiv,  "northwest" -> Kremenets))
      Bratslav.setNeighbors(Vector("north" -> Vinnytsia,     "northeast" -> Zvenyhorod,   "northwest" -> Letychiv))
      Letychiv.setNeighbors(Vector("north" -> Kremenets,     "northeast" -> Vinnytsia,    "southeast" -> Bratslav,   "west" -> Kamianets))
     Kamianets.setNeighbors(Vector("northeast" -> Kremenets, "east" -> Letychiv,          "west" -> Chervonohrad))
  Chervonohrad.setNeighbors(Vector("east" -> Kamianets,      "southwest" -> Kolomyia,     "west" -> Halych))
         Lutsk.setNeighbors(Vector("northeast" -> Ovruch,    "southeast" -> Zhytomyr,     "south" -> Kremenets,      "southwest" -> Volodymyr))
     Volodymyr.setNeighbors(Vector("northeast" -> Lutsk,     "southeast" -> Kremenets,    "south" -> Busk,           "southwest" -> Belz))
          Busk.setNeighbors(Vector("north" -> Volodymyr,     "south" -> Halych,           "southwest" -> Lviv,       "west" -> Belz))
          Belz.setNeighbors(Vector("northeast" -> Volodymyr, "east" -> Busk,              "south" -> Lviv))
        Halych.setNeighbors(Vector("north" -> Busk,          "east" -> Chervonohrad,      "south" -> Kolomyia,       "southwest" -> Zhydachiv, "west" -> Lviv))
          Lviv.setNeighbors(Vector("north" -> Belz,          "northeast" -> Busk,         "east" -> Halych,          "southeast" -> Zhydachiv, "south" -> Stryi, "southwest" -> Sambir))
     Zhydachiv.setNeighbors(Vector("northeast" -> Halych,    "southeast" -> Kolomyia,     "southwest" -> Drohobych,  "west" -> Stryi,          "northwest" -> Lviv))
         Stryi.setNeighbors(Vector("north" -> Lviv,          "east" -> Zhydachiv,         "south" -> Drohobych,      "west" -> Sambir))
        Sambir.setNeighbors(Vector("northeast" -> Lviv,      "east" -> Stryi,             "southeast" -> Drohobych))
      Kolomyia.setNeighbors(Vector("north" -> Halych,        "northeast" -> Chervonohrad, "west" -> Drohobych,       "northwest" -> Zhydachiv))
     Drohobych.setNeighbors(Vector("north" -> Stryi,         "northeast" -> Zhydachiv,    "east" -> Kolomyia,        "northwest" -> Sambir))

  // place these two items in clearing and southForest, respectively
  clearing addItem new Item("battery", "It's a small battery cell. Looks new.")
  southForest addItem new Item("remote", "It's the remote control for your TV.\nWhat it was doing in the forest, you have no idea.\nProblem is, there's no battery.")

  /** The character that the player controls in the game. */
  val player = new Player(???)

  /** The number of turns that have passed since the start of the game. */
  var turnCount = 0
  /** The maximum number of turns that this adventure game allows before time runs out. */
  val timeLimit = 40


  /** Determines if the adventure is complete, that is, if the player has won. */
  def isComplete = (this.player.location == this.destination) && player.has("battery") && player.has("remote")

  /** Determines whether the player has won, lost, or quit, thereby ending the game. */
  def isOver = this.isComplete || this.player.hasQuit || this.turnCount == this.timeLimit

  /** Returns a message that is to be displayed to the player at the beginning of the game. */
  def welcomeMessage = "You are lost in the woods. Find your way back home.\n\nBetter hurry, 'cause Scalatut elämät is on real soon now. And you can't miss Scalkkarit, right?"


  /** Returns a message that is to be displayed to the player at the end of the game. The message
    * will be different depending on whether or not the player has completed their quest. */
  def goodbyeMessage = {
    if (this.isComplete)
      "Home at last... and phew, just in time! Well done!"
    else if (this.turnCount == this.timeLimit)
      "Oh no! Time's up. Starved of entertainment, you collapse and weep like a child.\nGame over!"
    else  // game over due to player quitting
      "Quitter!"
  }


  /** Plays a turn by executing the given in-game command, such as "go west". Returns a textual
    * report of what happened, or an error message if the command was unknown. In the latter
    * case, no turns elapse. */
  def playTurn(command: String) = {
    val action = new Action(command)
    val outcomeReport = action.execute(this.player)
    if (outcomeReport.isDefined) {
      this.turnCount += 1
    }
    outcomeReport.getOrElse("Unknown command: \"" + command + "\".")
  }


}

