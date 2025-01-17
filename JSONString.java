import java.io.PrintWriter;

/**
 * JSON strings.
 */
public class JSONString implements JSONValue {

  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The underlying string.
   */
  String value;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a new JSON string for a particular string.
   */
  public JSONString(String value) {
    this.value = value;
  } // JSONString(String)

  // +-------------------------+-------------------------------------
  // | Standard object methods |
  // +-------------------------+

  /**
   * Convert to a string (e.g., for printing).
   */
  public String toString() {
    return "" + escape(value) + "\"";
  } // toString()

  /**
   * Compare to another object.
   */
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    } // if{}
    if (other == null || getClass() != other.getClass()) {
      return false;
    } // if{}

    JSONString that = (JSONString) other;
    return value.equals(that.value);
  } // equals(Object)

  /**
   * Compute the hash code.
   */
  public int hashCode() {
    return value.hashCode();
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
  public String getValue() {
    return this.value;
  } // getValue()

  /**
   * Helper method to escape special characters in a string
   */
  private String escape(String input) {
    // Implement some escape logic for characters if needed
    return input;
  } // escape(String)

} // class JSONString
