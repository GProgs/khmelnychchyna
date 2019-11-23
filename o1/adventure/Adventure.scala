package o1.adventure

import java.text.SimpleDateFormat
import java.util.{Calendar, GregorianCalendar}

/** The class `Adventure` represents text adventure games. An adventure consists of a player and
  * a number of areas that make up the game world.*/
class Adventure {

  /** The title of the adventure game. */
  val title = "Khmelnychchyna"

  private val Oster        = new Area("Oster", "")
  private val cChyhyryn    = new Area("City of Chyhyryn", "")
  private val cNikopol     = new Area("City of Nikopol", "")
  private val Kyiv         = new Area("Kyiv", "")
  private val cKyiv        = new Area("City of Kyiv", "")
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
  private val Kremenets    = new Area("Kremenets", "")
  private val destination = cNikopol

         Oster.setNeighbors(Vector("north" -> Chernihiv,     "west" -> Kyiv,              "southeast" -> cNikopol,   "inside" -> cChyhyryn))
     cChyhyryn.setNeighbors(Vector("around" -> Oster))
      cNikopol.setNeighbors(Vector("northwest" -> Oster))
          Kyiv.setNeighbors(Vector("northeast" -> Chernihiv, "east" -> Oster,             "southwest" -> Zvenyhorod, "west" -> Zhytomyr,       "inside" -> cKyiv))
         cKyiv.setNeighbors(Vector("around" -> Kyiv))
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
     Kremenets.setNeighbors(Vector("north" -> Lutsk,         "east" -> Zhytomyr,          "southeast" -> Vinnytsia,  "south" -> Letychiv,     "southwest" -> Kamianets))

  // items
  cKyiv addItem new Item("map", "A map of the Zaporozhian Sich. It is a bit worn out, but not too much.")
  Vinnytsia addItem new Item("sword", "A sword in excellent condition. It's only been used for practice.")

  /** The character that the player controls in the game. */
  val player = new Player(Oster)

  /** The number of turns that have passed since the start of the game. */
  var turnCount = 0
  /** The maximum number of turns that this adventure game allows before time runs out. */
  val timeLimit = 170 // beginning: 01.11.1647 6AM; end: 25.01.1648 6AM
  val calendar: GregorianCalendar = new GregorianCalendar(1647, 10, 1, 6, 0) // calendar
  val format: SimpleDateFormat = new SimpleDateFormat("dd.MM.yyyy 'at' HH:mm")

  /** Determines if the adventure is complete, that is, if the player has won. */
  def isComplete = (this.player.location == this.destination) && (turnCount == timeLimit) && player.has("sword") && player.has("map")

  /** Determines whether the player has won, lost, or quit, thereby ending the game. */
  def isOver = this.isComplete || this.player.hasQuit || this.turnCount == this.timeLimit || this.player.isStarved

  /** Returns a message that is to be displayed to the player at the beginning of the game. */
  def welcomeMessage = "A game about the Khmelnytsky Uprising.\n\nThe player, who is Bohdan Khmelnytsky,\nmust amass enough allies to successfully begin the uprising by 25 January 1648."


  /** Returns a message that is to be displayed to the player at the end of the game. The message
    * will be different depending on whether or not the player has completed their quest. */
  def goodbyeMessage = {
    if (this.isComplete)
      "With enough supporters all around Ukraine, you enter the Zaporozhian Sich and begin the uprising.\nAs a result, the Cossack Hetmanate is founded.\nYou won!"
    else if (this.turnCount == this.timeLimit)
      "Oh no! Time's up. You didn't manage to start the uprising.\nGame over!"
   else if (this.player.isStarved)
      "You have starved. Should have eaten while you had the chance..."
    else  // game over due to player quitting
      "Quitter!"
  }


  /** Plays a turn by executing the given in-game command, such as "go west". Returns a textual
    * report of what happened, or an error message if the command was unknown. In the latter
    * case, no turns elapse. */
  def playTurn(command: String) = {
    val action = new Action(command)
    val outcomeReport = action.execute(this.player)
    if (outcomeReport.isDefined && outcomeReport!= Some(this.player help) && outcomeReport != Some(this.player self)) {
      this.turnCount += 1
      calendar.add(Calendar.HOUR_OF_DAY, 12)
    }
    outcomeReport.getOrElse("Unknown command: \"" + command + "\".")
  }
  
  def time: String = format.format(calendar.getTime)

}

