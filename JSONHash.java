import java.io.PrintWriter;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * JSON hashes/objects.
 */
public class JSONHash implements JSONValue {

  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The array to store linked lists of key-value pairs
   */
  private LinkedList<KVPair<JSONString, JSONValue>>[] table;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a new JSONHash with a specific size
   */
  @SuppressWarnings("unchecked")
  public JSONHash(int size) {
    // Create an array of raw types
    table = (LinkedList<KVPair<JSONString, JSONValue>>[]) new LinkedList[size];

    // Initialize each element in the array
    for (int i = 0; i < size; i++) {
      table[i] = new LinkedList<>();
    }
  } // JSONHash(int)

  // +-------------------------+-------------------------------------
  // | Standard object methods |
  // +-------------------------+

  /**
   * Convert to a string (e.g., for printing).
   */
  public String toString() {
    StringBuilder result = new StringBuilder("{");
    boolean first = true;
    for (LinkedList<KVPair<JSONString, JSONValue>> list : table) {
      for (KVPair<JSONString, JSONValue> pair : list) {
        if (!first) {
          result.append(", ");
        } else {
          first = false;
        } // if {} ... else {}
        result.append(pair.key()).append(": ").append(pair.value());
      } // for {}
    } // for {}

    result.append("}");
    return result.toString();
  } // toString()

  /**
   * Compare to another object.
   */
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    } // if {}
    if (other == null || getClass() != other.getClass()) {
      return false;
    } // if {}

    JSONHash jsonHash = (JSONHash) other;
    return this.toString().equals(jsonHash.toString());
  } // equals(Object)

  /**
   * Compute the hash code.
   */
  public int hashCode() {
    return toString().hashCode();
  } // hashCode()

  // +--------------------+------------------------------------------
  // | Additional methods |
  // +--------------------+

  /**
   * Write the value as JSON.
   */
  public void writeJSON(PrintWriter pen) {
    pen.print(toString());
  } // writeJSON(PrintWriter)

  /**
   * Get the underlying value.
   */
  public Iterator<KVPair<JSONString, JSONValue>> getValue() {
    return iterator();
  } // getValue()

  // +-------------------+-------------------------------------------
  // | Hashtable methods |
  // +-------------------+

  /**
   * Get the value associated with a key.
   */
  public JSONValue get(JSONString key) {
    int index = hash(key);
    LinkedList<KVPair<JSONString, JSONValue>> list = table[index];
    for (KVPair<JSONString, JSONValue> pair : list) {
      if (pair.key().equals(key)) {
        return pair.value();
      } // if {}
    } // for {}
    return null;
  } // get(JSONString)

  /**
   * Get all of the key/value pairs.
   */
  public Iterator<KVPair<JSONString, JSONValue>> iterator() {
    LinkedList<KVPair<JSONString, JSONValue>> result = new LinkedList<>();
    for (LinkedList<KVPair<JSONString, JSONValue>> list : table) {
      result.addAll(list);
    } // for {}
    return result.iterator();
  } // iterator()

  /**
   * Set the value associated with a key.
   */
  public void set(JSONString key, JSONValue value) {
    int index = hash(key);
    LinkedList<KVPair<JSONString, JSONValue>> list = table[index];
    for (KVPair<JSONString, JSONValue> pair : list) {
      if (pair.key().equals(key)) {
        pair.value = value;
        return;
      } // if{}
    } // for {}
    list.add(new KVPair<>(key, value));
  } // set(JSONString, JSONValue)

  /**
   * Find out how many key/value pairs are in the hash table.
   */
  public int size() {
    int count = 0;
    for (LinkedList<KVPair<JSONString, JSONValue>> list : table) {
      count += list.size();
    } // for {}
    return count;
  } // size()

  // +-----------------+--------------------------------------------
  // | Hashing methods |
  // +-----------------+

  /**
   * compute the hash code for a key
   */
  private int hash(JSONString key) {
    return Math.abs(key.hashCode() % table.length);
  } // hash(JSONString)
} // class JSONHash
