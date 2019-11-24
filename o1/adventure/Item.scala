package o1.adventure

/** The class `Item` represents items in a text adventure game. Each item has a name
  * and a  *  longer description. (In later versions of the adventure game, items may
  * have other features as well.)
  *
  * N.B. It is assumed, but not enforced by this class, that items have unique names.
  * That is, no two items in a game world have the same name.
  *
  * @param name         the item's name
  * @param description  the item's description
  * @param use          what happens when you use the item */
class Item(val name: String, val description: String, val use: String) {

  /** Returns a short textual representation of the item (its name, that is). */
  override def toString = this.name
}