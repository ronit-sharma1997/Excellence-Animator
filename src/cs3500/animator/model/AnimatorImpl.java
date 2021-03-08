package cs3500.animator.model;


import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import cs3500.animator.util.AnimationBuilder;
import java.util.List;
import java.util.Map;

/**
 * Represents an animation of shapes and their respective motions over time. Every shape within this
 * {@link AnimatorImpl} is unique in each Shape's name. For each Shape, Changes either happen one at
 * a time, or multiple Changes happen the exact same start and end time. All valid changes are
 * guaranteed to occur within the smallest appear time out of any of the shapes in this animation
 * and the largest disappear time out of any of the Shape's in this animation.
 */
public final class AnimatorImpl implements Animator {

  // Using a HashMap provides easy iteration and access to see which shapes have been added to the
  // animation and whether any have any motions
  private Map<String, Shape> shapeMap;
  private Map<Integer, List<String>> layers;
  private int boundsX;
  private int boundsY;
  private int boundsWidth;
  private int boundsHeight;
  private int animationMaxTime;
  private int animationStartTime;
  private boolean hasLayers;

  /**
   * Constructs an animation of shapes and their respective motions over time.
   */
  public AnimatorImpl() {
    // Implemented a LinkedHashMap to keep track of the shapes that are added to the animation.
    // LinkedHashMaps preserve the order in which keys are added, which is why we picked this
    // type for testing purposes
    this.shapeMap = new LinkedHashMap<>();
    this.animationMaxTime = 0;
    this.animationStartTime = Integer.MAX_VALUE;
    this.hasLayers = false;
    this.layers = new LinkedHashMap<>();
    this.layers.put(0, new ArrayList<>());
  }

  /**
   * addShape is used to add a shape to this animation.
   */
  @Override
  public void addShape(String name, String type) {
    if (name == null || type == null) {
      throw new IllegalArgumentException("Null arguments are not allowed");
    } else if (name.equals("")) {
      throw new IllegalArgumentException("Name cannot be empty");
    } else if (this.shapeMap.containsKey(name)) {
      throw new IllegalArgumentException("Shape Name already exists");
    }
    Shape s;
    switch (type) {
      case "oval":
        s = new Oval(name);
        this.shapeMap.put(name, s);
        break;
      case "ellipse":
        s = new Oval(name);
        this.shapeMap.put(name, s);
        break;
      case "rectangle":
        s = new Rectangle(name);
        this.shapeMap.put(name, s);
        break;
      default:
        throw new IllegalArgumentException("Invalid shape type");
    }
    if (!this.hasLayers) {
      List<String> shapesInLayer = this.layers.get(0);
      shapesInLayer.add(s.getName());
      this.layers.put(0, shapesInLayer);
    }
  }

  @Override
  public void addShape(String name, String type, int layer) {
    if (name == null || type == null) {
      throw new IllegalArgumentException("Null inputs are not allowed");
    }
    if (layer < 0) {
      throw new IllegalArgumentException("Layer must be a non-negative integer");
    }
    if (!this.layers.containsKey(layer)) {
      throw new IllegalArgumentException("Specified layer does not exist");
    }
    this.addShape(name, type);
    List<String> shapesInLayer = this.layers.get(layer);
    shapesInLayer.add(name);
    this.layers.put(layer, shapesInLayer);
  }

  @Override
  public void editKeyframe(String name, int t, int x, int y, int w, int h, int r, int g, int b) {
    if (name == null || !shapeMap.containsKey(name)) {
      throw new IllegalArgumentException("Shape does not exist");
    }
    this.shapeMap.get(name).editKeyframe(t, x, y, w, h, r, g, b);
  }

  @Override
  public void editKeyframe(String name, int t, Attribute keyframeAttributes) {
    if (name == null || !shapeMap.containsKey(name)) {
      throw new IllegalArgumentException("Shape does not exist");
    }
    this.shapeMap.get(name).editKeyframe(t, keyframeAttributes);
  }

  @Override
  public void deleteKeyframe(String name, int t) {
    if (name == null || !this.shapeMap.containsKey(name)) {
      throw new IllegalArgumentException("Invalid input");
    }
    this.shapeMap.get(name).deleteKeyframe(t);
    if (t == animationMaxTime || t == animationStartTime) {
      int max = 0;
      int min = Integer.MAX_VALUE;
      for (Shape shape : shapeMap.values()) {
        if (shape.getMaxEndTime() > max) {
          max = shape.getMaxEndTime();
        }
        if (shape.getStartTime() < min) {
          min = shape.getStartTime();
        }
      }
      animationMaxTime = max;
      animationStartTime = min;
    }
  }

  @Override
  public void addKeyframe(String name, int t, int x, int y, int w, int h, int r, int g, int b) {
    if (name == null || !this.shapeMap.containsKey(name)) {
      throw new IllegalArgumentException("Invalid shape name");
    }
    if (t > animationMaxTime) {
      animationMaxTime = t;
    }
    if (t < animationStartTime) {
      animationStartTime = t;
    }
    this.shapeMap.get(name).addKeyframe(t, x, y, w, h, r, g, b);
  }

  @Override
  public void addKeyframe(String name, int t, Attribute keyframeAttributes) {
    if (name == null || !this.shapeMap.containsKey(name)) {
      throw new IllegalArgumentException("Invalid shape name");
    }
    if (t > animationMaxTime) {
      animationMaxTime = t;
    }
    if (t < animationStartTime) {
      animationStartTime = t;
    }
    this.shapeMap.get(name).addKeyframe(t, keyframeAttributes);

  }

  @Override
  public void deleteShape(String name) {
    if (name == null || !this.shapeMap.containsKey(name)) {
      throw new IllegalArgumentException("Invalid shape name");
    }
    if (this.shapeMap.get(name).getMaxEndTime() == animationMaxTime ||
        this.shapeMap.get(name).getStartTime() == animationStartTime) {
      this.shapeMap.remove(name);
      int max = 0;
      int min = Integer.MAX_VALUE;
      if (shapeMap.size() == 0) {
        min = 0;
      }
      for (Shape shape : shapeMap.values()) {
        if (shape.getMaxEndTime() > max) {
          max = shape.getMaxEndTime();
        }
        if (shape.getStartTime() < min) {
          min = shape.getStartTime();
        }
      }
      animationMaxTime = max;
      animationStartTime = min;
    } else {
      this.shapeMap.remove(name);
    }
    for (Map.Entry<Integer, List<String>> entry : this.layers.entrySet()) {
      entry.getValue().remove(name);
    }


  }

  @Override
  public void resetCurrentMotions() {
    for (Shape shape : this.shapeMap.values()) {
      shape.resetCurrentMotion();
    }
  }

  @Override
  public void addLayer(int layer) {
    if (layer < 0) {
      throw new IllegalArgumentException("Layer must be a non-negative integer");
    }
    if (this.layers.containsKey(layer)) {
      throw new IllegalArgumentException("Specified layer already exists, cannot be added twice");
    }
    this.layers.put(layer, new ArrayList<>());
    if (!this.hasLayers) {
      this.hasLayers = true;
    }
    List<Map.Entry<Integer, List<String>>> lyrs =
        new ArrayList<Map.Entry<Integer, List<String>>>(this.layers.entrySet());
    Collections.sort(lyrs, new Comparator<Map.Entry<Integer, List<String>>>() {
      public int compare(Map.Entry<Integer, List<String>> a, Map.Entry<Integer, List<String>> b) {
        return a.getKey() - b.getKey();
      }
    });
    Map<Integer, List<String>> resortedLayers = new LinkedHashMap<Integer, List<String>>();
    for (Map.Entry<Integer, List<String>> entry : lyrs) {
      resortedLayers.put(entry.getKey(), entry.getValue());
    }
    this.layers = resortedLayers;
  }

  @Override
  public void deleteLayer(int layer) {
    if (!this.layers.containsKey(layer)) {
      throw new IllegalArgumentException("Specified layer does not exist");
    }
    for (Map.Entry<Integer, List<String>> entry : this.layers.entrySet()) {
      if (entry.getKey() == layer) {
        for (String shapeInLayer : entry.getValue()) {
          this.shapeMap.remove(shapeInLayer);
        }
      }
    }
    this.layers.remove(layer);
    if (this.layers.size() == 0) {
      this.hasLayers = false;
    }
  }

  @Override
  public void changeLayer(int fromLayer, int toLayer) {
    if (this.layers.containsKey(fromLayer) && this.layers.containsKey(toLayer)) {
      List<String> shapesInFromLayer = new ArrayList<>();
      for (String shape : this.layers.get(fromLayer)) {
        shapesInFromLayer.add(shape);
      }
      if (fromLayer > toLayer) {
        List<String> shapesInPrevLayer = new ArrayList<>();
        boolean found = false;
        for (Map.Entry<Integer, List<String>> entry : this.layers.entrySet()) {
          if (entry.getKey() == toLayer) {
            if (entry.getValue().size() != 0) {
              for (String shape : entry.getValue()) {
                shapesInPrevLayer.add(shape);
              }
            }
            this.layers.put(entry.getKey(), shapesInFromLayer);
            found = true;
          } else if (found) {
            List<String> tempList = new ArrayList<>();
            if (!entry.getValue().isEmpty()) {
              for (String shape : entry.getValue()) {
                tempList.add(shape);
              }
            }
            this.layers.put(entry.getKey(), shapesInPrevLayer);
            if (entry.getKey() == fromLayer) {
              return;
            }
            shapesInPrevLayer = tempList;
          }
        }
      } else if (fromLayer < toLayer) {
        int previousLayer = -1;
        boolean found = false;
        for (Map.Entry<Integer, List<String>> entry : this.layers.entrySet()) {
          if (entry.getKey() == toLayer) {
            this.layers.put(previousLayer, entry.getValue());
            this.layers.put(entry.getKey(), shapesInFromLayer);
            return;
          }
          if (entry.getKey() == fromLayer) {
            previousLayer = entry.getKey();
            found = true;
          } else if (found) {
            this.layers.put(previousLayer, entry.getValue());
            previousLayer = entry.getKey();
          }
        }
      }
    } else {
      throw new IllegalArgumentException("Layer does not exist");
    }
  }

  @Override
  public int getBoundsX() {
    return this.boundsX;
  }

  @Override
  public int getBoundsY() {
    return this.boundsY;
  }

  @Override
  public int getBoundsWidth() {
    return this.boundsWidth;
  }

  @Override
  public int getBoundsHeight() {
    return this.boundsHeight;
  }

  @Override
  public int getAnimationEndTime() {
    return this.animationMaxTime;
  }

  @Override
  public int getAnimationStartTime() {
    return this.animationStartTime;
  }

  @Override
  public HashMap<String, ImmutableShape> getShapeMap() {
    HashMap<String, ImmutableShape> copyOfShapeMap = new LinkedHashMap<>();
    for (Map.Entry<String, Shape> e : this.shapeMap.entrySet()) {
      copyOfShapeMap.put(e.getKey(), e.getValue().copyShape());
    }
    return copyOfShapeMap;
  }

  @Override
  public List<ImmutableShape> getForwardAnimationStateTick(int currentTick) {
    List<ImmutableShape> updatedShapes = new ArrayList<>();
    for (Map.Entry<String, Shape> e : getShapeMapSortedByLayer().entrySet()) {
      ImmutableShape newShape = getForwardAnimationStateShape(e.getValue(), currentTick);
      if (newShape == null) {
        continue;
      }
      updatedShapes.add(newShape);
    }
    return updatedShapes;
  }

  private Map<String, Shape> getShapeMapSortedByLayer() {
    Map<String, Shape> sortedMap = new LinkedHashMap<>();
    for (Map.Entry<Integer, List<String>> layers : this.layers.entrySet()) {
      for (String shapeInLayer : layers.getValue()) {
        sortedMap.put(shapeInLayer, shapeMap.get(shapeInLayer));
      }
    }
    return sortedMap;
  }

  @Override
  public Map<Integer, List<String>> getLayers() {
    Map<Integer, List<String>> lyrs = new LinkedHashMap<>();
    for (Map.Entry<Integer, List<String>> e : this.layers.entrySet()) {
      lyrs.put(e.getKey(), e.getValue());
    }
    return lyrs;
  }

  @Override
  public Map<String, Map<Integer, Attribute>> getKeyframes() {
    Map<String, Map<Integer, Attribute>> shapeKeyFrames = new LinkedHashMap<>();
    Map<Integer, Attribute> timeKeyFrames = new LinkedHashMap<>();
    for (Map.Entry<String, Shape> e : shapeMap.entrySet()) {
      int sizeOfMotions = e.getValue().getMotions().size();
      int counter = 0;
      for (Motion m : e.getValue().getMotions()) {
        timeKeyFrames.put(m.getStartTime(), m.getPreviousAttribute());
        if (counter == (sizeOfMotions - 1)) {
          timeKeyFrames.put(m.getEndTime(), m.getNewAttribute());
        }
        counter++;
      }
      shapeKeyFrames.put(e.getKey(), new LinkedHashMap<>(timeKeyFrames));
      timeKeyFrames.clear();
    }
    return shapeKeyFrames;
  }

  /**
   * Gets current forward animation state of a single specified Shape in the Animation.
   *
   * @param currentShape - specific shape of the animation
   * @param currentTick - tick of the Animation
   * @return - {@code ImmutableShape} object and its updated attribute
   */
  private Shape getForwardAnimationStateShape(Shape currentShape, int currentTick) {
    if (currentShape == null || currentShape.noMotions() ||
        currentTick > currentShape.getMaxEndTime() ||
        currentTick < currentShape.getMotions().get(0).getStartTime()) {
      return null;
    }
    Motion currentShapeMotion;
    currentShapeMotion = currentShape.getCurrentMotion();
    if (currentShape.noCurrentMotion() || !(currentShapeMotion.getStartTime() <= currentTick
        && currentShapeMotion.getEndTime() >= currentTick)) {
      for (Motion change : currentShape.getMotions()) {
        int changeStartTime = change.getStartTime();
        int changeEndTIme = change.getEndTime();
        if (changeStartTime <= currentTick && changeEndTIme >= currentTick) {
          currentShape.setCurrentMotion(change);
          Attribute shapeNewState = change.getPreviousAttribute();
          currentShape.setValues(shapeNewState);
          break;

        }
      }
    }

    int[] previousAttributes = currentShape.getAttribute().getAttributes();
    int[] newAttributes = currentShape.getCurrentMotion().getNewState();

    int currentChangeStartTime = currentShape.getCurrentMotion().getStartTime();
    int currentChangeEndTIme = currentShape.getCurrentMotion().getEndTime();

    newAttributes = newAttributesShape(currentChangeStartTime, currentChangeEndTIme, currentTick,
        previousAttributes, newAttributes);

    Shape updatedShape = null;
    if (currentShape.getType().equals(ShapeType.RECTANGLE)) {
      updatedShape = new Rectangle("Updated shape of " + currentShape.getName());
      updatedShape.setValues(new AttributeImpl.Builder(new Position2D(newAttributes[0],
          newAttributes[1]), newAttributes[2], newAttributes[3], new Color(newAttributes[4],
          newAttributes[5], newAttributes[6])).setAngle(newAttributes[7]).build());
    } else if (currentShape.getType().equals(ShapeType.OVAL)) {
      updatedShape = new Oval("Updated shape of " + currentShape.getName());
      updatedShape.setValues(new AttributeImpl.Builder(new Position2D(newAttributes[0],
          newAttributes[1]), newAttributes[2], newAttributes[3], new Color(newAttributes[4],
          newAttributes[5], newAttributes[6])).setAngle(newAttributes[7]).build());
    }
    return updatedShape;
  }

  private int[] newAttributesShape(int motionStartTime, int motionEndTime, int currentTick,
      int[] previousAttributes, int[] newAttributes) {
    int[] result = new int[8];
    if (motionStartTime == motionEndTime) {
      result[0] = newAttributes[0];
      result[1] = newAttributes[1];
      result[2] = newAttributes[2];
      result[3] = newAttributes[3];
      result[4] = newAttributes[4];
      result[5] = newAttributes[5];
      result[6] = newAttributes[6];
      result[7] = newAttributes[7];
    } else {
      result[0] = (interpolation(previousAttributes[0], newAttributes[0], motionStartTime,
          motionEndTime, currentTick));
      result[1] = (interpolation(previousAttributes[1], newAttributes[1], motionStartTime,
          motionEndTime, currentTick));
      result[2] = (interpolation(previousAttributes[2], newAttributes[2], motionStartTime,
          motionEndTime, currentTick));
      result[3] = (interpolation(previousAttributes[3], newAttributes[3], motionStartTime,
          motionEndTime, currentTick));
      result[4] = (interpolation(previousAttributes[4], newAttributes[4], motionStartTime,
          motionEndTime, currentTick));
      result[5] = (interpolation(previousAttributes[5], newAttributes[5], motionStartTime,
          motionEndTime, currentTick));
      result[6] = (interpolation(previousAttributes[6], newAttributes[6], motionStartTime,
          motionEndTime, currentTick));
      result[7] = (interpolation(previousAttributes[7], newAttributes[7], motionStartTime,
          motionEndTime, currentTick));
    }
    return result;
  }

  private int interpolation(double previous, double next, int startTime, int endTime,
      int currentTick) {
    return (int) ((previous * (endTime - currentTick) / (endTime - startTime)) + (
        next * (currentTick - startTime) / (endTime - startTime)));
  }

  public static final class Builder implements AnimationBuilder<Animator> {

    private int minTime;
    private int boundsX;
    private int boundsY;
    private int boundsWidth;
    private int boundsHeight;
    private AnimatorImpl a;

    public Builder() {
      a = new AnimatorImpl();
    }

    @Override
    public Animator build() {
      a.boundsX = this.boundsX;
      a.boundsY = this.boundsY;
      a.boundsWidth = this.boundsWidth;
      a.boundsHeight = this.boundsHeight;
      for (Map.Entry<String, Shape> e : a.shapeMap.entrySet()) {
        e.getValue().checkShapeMotions();
      }
      return a;
    }

    @Override
    public AnimationBuilder<Animator> setBounds(int x, int y, int width, int height)
        throws IllegalArgumentException {
      if (width < 1 || height < 1) {
        throw new IllegalArgumentException("Invalid Bound Inputs");
      }
      this.boundsX = x;
      this.boundsY = y;
      this.boundsWidth = width;
      this.boundsHeight = height;
      return this;
    }

    @Override
    public AnimationBuilder<Animator> declareShape(String name, String type) {
      if (a.hasLayers) {
        throw new IllegalArgumentException("There is more than one layer in this animation,"
            + "therefore a layer must be specified when declaring a shape");
      }
      a.addShape(name, type);
      return this;
    }

    @Override
    public AnimationBuilder<Animator> declareShape(String name, String type, int layer) {
      if (!a.hasLayers && layer != 0) {
        throw new IllegalArgumentException("Invalid layer, this animation does not have more than"
            + "one layer, you cannot add a shape to a layer that does not exist");
      }
      a.addShape(name, type, layer);
      return this;
    }

    @Override
    public AnimationBuilder<Animator> addMotion(String name,
        int t1, int x1, int y1, int w1, int h1, int r1, int g1, int b1,
        int t2, int x2, int y2, int w2, int h2, int r2, int g2, int b2) {
      if (name == null) {
        throw new IllegalArgumentException("Name cannot be null");
      } else if (!a.shapeMap.containsKey(name)) {
        throw new IllegalArgumentException("No Shape Found");
      } else if (a.shapeMap.get(name).noMotions() || t1 < minTime) {
        this.minTime = t1;
        a.shapeMap.get(name).setValues(new Position2D(x1, y1), w1, h1, new Color(r1, g1, b1));
      }

      if (t2 > a.animationMaxTime) {
        a.animationMaxTime = t2;
      }
      if (t1 < a.animationStartTime) {
        a.animationStartTime = t1;
      }
      Motion newMotion = new MotionImpl(t1, t2, new AttributeImpl.Builder(new Position2D(x1, y1),
          w1, h1, new Color(r1, g1, b1)).build(), new AttributeImpl.Builder(new Position2D(x2, y2),
          w2, h2, new Color(r2, g2, b2)).build());
      a.shapeMap.get(name).addMotion(newMotion);
      return this;
    }

    @Override
    public AnimationBuilder<Animator> addMotion(String name, int t1, Attribute previousState,
        int t2, Attribute newState) {
      if (name == null) {
        throw new IllegalArgumentException("Name cannot be null");
      } else if (!a.shapeMap.containsKey(name)) {
        throw new IllegalArgumentException("No Shape Found");
      } else if (a.shapeMap.get(name).noMotions() || t1 < minTime) {
        this.minTime = t1;
        a.shapeMap.get(name).setValues(previousState);
      }

      if (t2 > a.animationMaxTime) {
        a.animationMaxTime = t2;
      }
      if (t1 < a.animationStartTime) {
        a.animationStartTime = t1;
      }
      Motion newMotion = new MotionImpl(t1, t2, previousState, newState);
      a.shapeMap.get(name).addMotion(newMotion);
      return this;
    }

    @Override
    public AnimationBuilder<Animator> addKeyframe(String name, int t, int x, int y, int w, int h,
        int r, int g, int b) {
      if (t < 0 || w < 1 || h < 1 || r < 0 || g < 0 || b < 0) {
        throw new IllegalArgumentException("Invalid input");
      }
      a.shapeMap.get(name).addKeyframe(t, x, y, w, h, r, g, b);
      return this;
    }

    @Override
    public AnimationBuilder<Animator> declareLayer(int layer) {
      a.addLayer(layer);
      return this;
    }

  }
}