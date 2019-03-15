package cs3500.animator.model;

import cs3500.animator.model.qualities.color.TextureImpl;
import cs3500.animator.model.qualities.dimensions.Size2D;
import cs3500.animator.model.qualities.positions.Position2D;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * This class represents a collection of Shapes that could be displayed during one animation.
 */
public class AnimationImpl implements Animation {

    private Map<String, Shape> shapes;
    private Stack<Integer> lastKeyframeTickOnWholeAnimation;

    /**
     * Initializes ShapeImpl.
     */
    public AnimationImpl() {
        shapes = new HashMap<>();
        this.lastKeyframeTickOnWholeAnimation = new Stack<>();
        this.lastKeyframeTickOnWholeAnimation.push(0);
    }


    @Override
    public void addShape(String name, String type) {
        Shapes shapeType = null;
        for (Shapes s : Shapes.values()) {
            if (s.name().equals(type)) {
                shapeType = s;
            }
        }
        if (shapeType == null) {
            throw new IllegalArgumentException("Shape type cannot be null.");
        }

        if (name == null) {
            throw new IllegalArgumentException("Shape name cannot be null.");
        }

        shapes.put(name, new ShapeImpl(shapeType, name));
    }

    @Override
    public void addMotion(String shapeName, Keyframe start, Keyframe end) {
        if (end.getTick() > this.lastKeyframeTickOnWholeAnimation.peek()) {
            this.lastKeyframeTickOnWholeAnimation.push(end.getTick());
        }

        if (end.getTick() <= start.getTick()) {
            throw new IllegalArgumentException("End keyframe is before/concurrent with start keyframe");
        }
        if (!shapes.containsKey(shapeName)) {
            throw new IllegalArgumentException("The given shape does not exist or has not been declared");
        }
        int currentShapeDuration = shapes.get(shapeName).totalDuration();
        if (start.getTick() < currentShapeDuration || end.getTick() < currentShapeDuration) {
            throw new IllegalArgumentException("There cannot be overlap of motions.");
        }
        if (currentShapeDuration != 0 && currentShapeDuration != start.getTick()) {
            throw new IllegalArgumentException(
                    "There cannot be gaps between motions. The next motion to be added should start at "
                            + currentShapeDuration);
        }
        for (Shape s : shapes.values()) {
            if (s.getName().equals(shapeName)) {
                s.addMotion(new MotionImpl(start.getTick(), end.getTick(), s, start, end));
            }
        }
    }

    @Override
    public void addRotationless2DMotion(String shapeName, int startingTick, double x0, double y0,
                                        double w0, double h0, double r0, double g0, double b0, int endingTick, double x1, double y1,
                                        double w1, double h1, double r1, double g1, double b1) {
        this.addMotion(shapeName,
                new KeyframeImpl(startingTick,
                        new Position2D(x0, y0),
                        new Size2D(w0, h0),
                        new TextureImpl(r0, g0, b0, 1)),
                new KeyframeImpl(endingTick,
                        new Position2D(x1, y1),
                        new Size2D(w1, h1),
                        new TextureImpl(r1, g1, b1, 1)));
    }

    @Override
    public String toFile() {

        StringBuilder built = new StringBuilder("");
        for (Shape s : shapes.values()) {
            built.append("shape ").append(s.getName()).append(" ").append(s.getShape()).append("\n")
                    .append(s.toFile());
        }
        return built.toString().substring(0, built.toString().length() - 1);
    }

    @Override
    public int totalDuration() {
        return this.lastKeyframeTickOnWholeAnimation.peek();
    }

    @Override
    public Map<String, Shape> getShapes() {
        return shapes;
    }

    @Override
    public AnimationBuilder<Animation> getBuilder() {
        return new Builder();
    }

    @Override
    public void setBounds(int x, int y, int width, int height) {

    }

    /**
     * This is a Builder class capable of building an Animation.
     */
    public final static class Builder implements AnimationBuilder<Animation> {

        Animation animation;

        public Builder() {
            this.animation = new AnimationImpl();
        }

        @Override
        public Animation build() {
            return animation;
        }

        @Override
        public AnimationBuilder<Animation> setBounds(int x, int y, int width, int height) {
            this.animation.setBounds(x, y, width, height);
            return this;
        }

        @Override
        public AnimationBuilder<Animation> declareShape(String name, String type) {
            this.animation.addShape(name, type);
            return this;
        }

        @Override
        public AnimationBuilder<Animation> addMotion(String name, int t1, int x1, int y1, int w1, int h1, int r1, int g1, int b1, int t2, int x2, int y2, int w2, int h2, int r2, int g2, int b2) {
            this.animation.addRotationless2DMotion(name, t1, x1, y1, w1, h1, r1, g1, b1, t2, x2, y2, w2, h2, r2, g2, b2);
            return this;
        }

        @Override
        public AnimationBuilder<Animation> addKeyframe(String name, int t, int x, int y, int w, int h, int r, int g, int b) {
            //TODO
            //the way to go about this is to split a where the tick belongs and create two separate new motions...
            return this;
        }
    }
}
