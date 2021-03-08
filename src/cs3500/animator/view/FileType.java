package cs3500.animator.view;

/**
 * Represents the types of files that can be saved/loaded.
 */
public enum FileType {
  SVG("svg"), TEXT("text");

  private final String type;

  FileType(String type) {
    this.type = type;
  }

  public String getFileType() {
    return this.type;
  }
}
