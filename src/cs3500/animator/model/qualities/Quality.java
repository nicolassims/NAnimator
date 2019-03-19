package cs3500.animator.model.qualities;

/**
 * Represents a characteristic that can describe an animated object.
 */
public interface Quality {

  /**
   * returns the sum together of two qualities.
   */
  Quality addTogether(Quality quality);

  /**
   * Returns a string description of the dimensions inside parenthesis. For instance,
   * "[dimensions:Size2D: (weight: 2.0, height: 3.0)]"
   */
  String getQualities();


  /**
   * Returns the difference between two qualies given that the qualities are of the same type.
   */
  Quality getDifference(Quality other);

  /**
   * Scales down a quality double fields by a given int.
   */
  Quality divideBy(int duration);

  /**
   * Scales up a quality double fields by given int.
   */
  Quality multiplyBy(int currentTick);

  /**
   * Checks if two qualities are equal.
   */
  boolean equals(Object other);

  /**
   * Returns a string representation of the Quality.
   */
  String toFile();
}
