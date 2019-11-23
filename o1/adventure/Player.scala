package o1.adventure

import scala.collection.mutable.Map
import scala.util.Random

object Player {
  val foods: Vector[String] = Vector[String]("varenyky", "bublyk", "Chicken Kiev", "vushka", "kovbasa", "salo", "goulash", "kompot")
}

/** A `Player` object represents a player character controlled by the real-life user of the program.
  *
  * A player object's state is mutable: the player's location and possessions can change, for instance.
  *
  * @param startingArea  the initial location of the player */
class Player(startingArea: Area) {

  private var currentLocation = startingArea        // gatherer: changes in relation to the previous location
  private var quitCommandGiven = false              // one-way flag
  private val items: Map[String, Item] = Map[String, Item]()
  private val rand: Random = new Random() // random generator
  
  private var _rep: Double = 0.0 // the player's reputation
  private var _hunger: Int = 0 // hunger level


  /** Determines if the player has indicated a desire to quit the game. */
  def hasQuit = this.quitCommandGiven


  /** Returns the current location of the player. */
  def location = this.currentLocation


  /** Attempts to move the player in the given direction. This is successful if there
    * is an exit from the player's current location towards the direction name. Returns
    * a description of the result: "You go DIRECTION." or "You can't go DIRECTION." */
  def go(direction: String) = {
    val destination = this.location.neighbor(direction)
    if (_hunger == 5) "You're too hungry to continue. Feeling exhausted, you take a break." else {
      this.currentLocation = destination.getOrElse(this.currentLocation)
      if (destination.isDefined) { incHunger(); "You go " + direction + "." } else "You can't go " + direction + "."
    }
  }


  /** Causes the player to rest for a short while (this has no substantial effect in game terms).
    * Returns a description of what happened. */
  def rest() = "You rest for a while. Better get a move on, though."
  
  /** Causes the player to eat something, restoring their hunger level to zero.
   *  Returns the food they ate. */
  def eat(): String = {
    _hunger = 0
    "You have some " + Player.foods(rand.nextInt(Player.foods.length)) + " and feel satiated."
  }
  
  def incHunger(): Unit = { _hunger = _hunger + 1 }
  
  /** Returns the player's hunger level as an integer on the half-open interval [0,∞[. Zero means the player is sated. */
  def hunger: Int = _hunger
  
  /** If the player's hunger is too great, the player will starve. */
  def isStarved: Boolean = _hunger >= 10

  /** Signals that the player wants to quit the game. Returns a description of what happened within
    * the game as a result (which is the empty string, in this case). */
  def quit() = {
    this.quitCommandGiven = true
    ""
  }
  
  /** Gives a list of new commands and what the player has to in order to win. */
  def help() = {
    "New commands:\n- speech\n- self\n- eat (remember to eat!)\n- rest"
  }
  
  /** Causes the player to hold a speech. This may or may not change the player's reputation. */
  def holdSpeech(): String = {
    val dRep: Double = rand.nextGaussian()
    _rep = _rep + dRep // changes the reputation
    incHunger()
    if (dRep < -1.0) {
      "Your speech instilled a deep fear and hatred in the population, proving a strong setback for your campaign."
    } else if (dRep < -0.5) {
      "Your speech was a bit too negative to the people. While a minor setback, you've learned to be a bit more gentle next time."
    } else if (dRep < 0.5) {
      "The people listened to your speech and largely agree with you, although some were more than apathetic. You keep going forward."
    } else if (dRep < 1.0) {
      "The impending uprising motivated you during your speech and you gave a fluid and resounding performance. With your hopes up, you move ahead."
    } else {
      "Your speech resonated more than powerfully with the people. They wholeheartedly agree with you and swear to support you."
    }
  }
  
  /** Returns the player's reputation. The bigger the reputation, the better a reputation the player has. */
  def reputation: Double = _rep
  
  // Item methods
  private def doOrElse(item: Option[Item])(defined: Item => String, empty: () => String): String = {
    if (item.isDefined) { defined(item.get) } else { empty() }
  }
  
  def get(itemName: String): String = {
    val defined: Item => String = (item: Item) => {
      items += item.name -> item
      s"You pick up the ${itemName}."
    }
    val empty: () => String = () => s"There is no ${itemName} here to pick up."
    doOrElse(currentLocation.removeItem(itemName))(defined, empty)
  }
  
  def drop(itemName: String): String = {
    val defined: Item => String = (item: Item) => {
      currentLocation.addItem(item)
      items.remove(item.name)
      s"You drop the ${itemName}."
    }
    val empty: () => String = () => "You don't have that!"
    doOrElse(items.get(itemName))(defined, empty)
  }
  
  def examine(itemName: String): String = {
    val defined: Item => String = (item: Item) => s"You look closely at the ${item.name}.\n${item.description}"
    val empty: () => String = () => "If you want to examine something, you need to pick it up first."
    doOrElse(items.get(itemName))(defined, empty)
  }
  
  def inventory: String = {
    if (items.isEmpty) { "You are empty-handed." }
    else {
      "You are carrying:\n" + items.keys.mkString("\n")
    }
  }
  
  def has(itemName: String): Boolean = items.contains(itemName)
  
  /** Returns a brief description of the player's state, for debugging purposes. */
  override def toString = "Now at: " + this.location.name
  
  def self: String = s"Name: Bohdan Zynoviy Mykhailovych Khmelnytsky\nHunger: $hunger\nReputation: $reputation"
}


