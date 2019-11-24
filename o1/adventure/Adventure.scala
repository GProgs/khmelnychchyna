package o1.adventure

import java.text.SimpleDateFormat
import java.util.{Calendar, GregorianCalendar}

/** The class `Adventure` represents text adventure games. An adventure consists of a player and
  * a number of areas that make up the game world.*/
class Adventure {

  /** The title of the adventure game. */
  val title = "Khmelnychchyna"

  private val Oster        = new Area("Oster", 6300)
  private val cChyhyryn    = new Area("City of Chyhyryn", 980)
  private val cNikopol     = new Area("City of Nikopol", 1230)
  private val Kyiv         = new Area("Kyiv", 12000)
  private val cKyiv        = new Area("City of Kyiv", 2355)
  private val Chernihiv    = new Area("Chernihiv", 2200)
  private val Zhytomyr     = new Area("Zhytomyr", 3300)
  private val Ovruch       = new Area("Ovruch", 2100)
  private val Zvenyhorod   = new Area("Zvenyhorod", 4200)
  private val Vinnytsia    = new Area("Vinnytsia", 6300)
  private val Bratslav     = new Area("Bratslav", 7800)
  private val Letychiv     = new Area("Letychiv", 4300)
  private val Kamianets    = new Area("Kamianets", 5500)
  private val Chervonohrad = new Area("Chervonohrad", 2200)
  private val Lutsk        = new Area("Lutsk", 4300)
  private val Volodymyr    = new Area("Volodymyr", 3210)
  private val Busk         = new Area("Busk", 1200)
  private val Belz         = new Area("Belz", 3200)
  private val Halych       = new Area("Halych", 2300)
  private val Lviv         = new Area("Lviv", 8300)
  private val Zhydachiv    = new Area("Zhydachiv", 1945)
  private val Stryi        = new Area("Stryi", 1939)
  private val Sambir       = new Area("Sambir", 1300)
  private val Kolomyia     = new Area("Kolomyia", 2200)
  private val Drohobych    = new Area("Drohobych", 2300)
  private val Kremenets    = new Area("Kremenets", 5200)
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
  cKyiv addItem new Item("map", "A map of the Zaporozhian Sich. It is a bit worn out, but not too much.", "The place you want to get to is Nikopol, about as far east as you can get.")
  Vinnytsia addItem new Item("sword", "A sword in excellent condition. It's only been used for practice.", "Man, this is a nice sword... too bad there's nothing I could try it on.")

  /** The character that the player controls in the game. */
  val player = new Player(Oster)

  /** The number of turns that have passed since the start of the game. */
  var turnCount = 0
  /** The maximum number of turns that this adventure game allows before time runs out. */
  val timeLimit = 170 // beginning: 01.11.1647 6AM; end: 25.01.1648 6AM
  val calendar: GregorianCalendar = new GregorianCalendar(1647, 10, 1, 6, 0) // calendar
  val format: SimpleDateFormat = new SimpleDateFormat("dd.MM.yyyy 'at' HH:mm")

  /** Determines if the adventure is complete, that is, if the player has won. */
  def isComplete = (this.player.location == this.destination) && ((this.turnCount == this.timeLimit) || this.player.hasEnded) && this.player.has("sword") && this.player.has("map")

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
      "You have starved. Should have eaten while you had the chance...\nGame over!"
    else  // game over due to player quitting
      "Quitter!"
  }


  /** Plays a turn by executing the given in-game command, such as "go west". Returns a textual
    * report of what happened, or an error message if the command was unknown. In the latter
    * case, no turns elapse. Turns don't elapse for the commands "help" and "self". */
  def playTurn(command: String) = {
    val action = new Action(command)
    val outcomeReport = action.execute(this.player)
    if (outcomeReport.isDefined && outcomeReport!= Some(this.player help) && outcomeReport != Some(this.player self) && outcomeReport != Some(this.player end)) {
      this.turnCount += 1
      calendar.add(Calendar.HOUR_OF_DAY, 12)
    }
    outcomeReport.getOrElse("Unknown command: \"" + command + "\".")
  }
  
  def time: String = format.format(calendar.getTime)

}

