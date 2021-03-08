package cs3500.animator.model;

/**
 * Represents the types of changes that can be made to a Shape.
 */
public enum ChangeType {
  COLOR("Color"), POSITION("Position"), WIDTH("Width"), HEIGHT("Height"), NOCHANGE("No Change"),
  ROTATION("Rotation");

  private final String type;

  ChangeType(String type) {
    this.type = type;
  }

  public String getChangeType() {
    return this.type;
  }
}