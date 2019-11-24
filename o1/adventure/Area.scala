package o1.adventure

import scala.collection.mutable.Map
import scala.math.max

/** The class `Area` represents locations in a text adventure game world. A game world
  * consists of areas. In general, an "area" can be pretty much anything: a room, a building,
  * an acre of forest, or something completely different. What different areas have in
  * common is that players can be located in them and that they can have exits leading to
  * other, neighboring areas. An area also has a name and a description.
  * @param name         the name of the area
  * @param description  a basic description of the area (typically not including information about items) */
class Area(var name: String, private var _recruitablePopulation: Int) {

  private val neighbors = Map[String, Area]()
  private val items: Map[String, Item] = Map[String, Item]()
  private var _talksGiven: Int = 0

  /** Returns the area that can be reached from this area by moving in the given direction. The result
    * is returned in an `Option`; `None` is returned if there is no exit in the given direction. */
  def neighbor(direction: String) = this.neighbors.get(direction)


  /** Adds an exit from this area to the given area. The neighboring area is reached by moving in
    * the specified direction from this area. */
  def setNeighbor(direction: String, neighbor: Area) = {
    this.neighbors += direction -> neighbor
  }


  /** Adds exits from this area to the given areas. Calling this method is equivalent to calling
    * the `setNeighbor` method on each of the given direction--area pairs.
    * @param exits  contains pairs consisting of a direction and the neighboring area in that direction
    * @see [[setNeighbor]] */
  def setNeighbors(exits: Vector[(String, Area)]) = {
    this.neighbors ++= exits
  }


  /** Returns a multi-line description of the area as a player sees it. This includes a basic
    * description of the area as well as information about exits and items. */
  def fullDescription = {
    val itemList = if (!items.isEmpty) "\nYou see here: " + items.keys.mkString(" ") else ""
    val exitList = "\n\nExits available: " + this.neighbors.keys.mkString(" ")
    s"$name\nRecruitable population: ${_recruitablePopulation}" + itemList + exitList
  }
  
  // Item methods
  def addItem(item: Item): Unit = items += item.name -> item
  
  def removeItem(itemName: String): Option[Item] = items.remove(itemName)
  
  def contains(itemName: String): Boolean = items.contains(itemName)
  
  // Population methods
  def recruitablePopulation: Int = _recruitablePopulation
  
  /** Updates the population, given a coefficient. If the new population turns out negative, then just clip it at zero.
   *  Returns the difference in population. */
  def updatePopulation(d: Int): Int = {
    val oldPop: Int = _recruitablePopulation
    val newPop: Int = _recruitablePopulation + d
    if (newPop < 0) { _recruitablePopulation = 0; oldPop } else { _recruitablePopulation = newPop; d }
  }
  
  /** Indicates an attempt to give a talk. If successsful, return true; if not, return false. A player can give a talk if there have been four or less talks in this area. */
  def talk(): Boolean = if (_talksGiven <= 4) {_talksGiven = _talksGiven + 1; true} else false
  
  /** Returns a single-line description of the area for debugging purposes. */
  override def toString = this.name



}
