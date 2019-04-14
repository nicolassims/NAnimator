package cs3500.animator.view;

import cs3500.animator.provider.view.EditorView;
import cs3500.animator.provider.view.EditorViewImplementingView;
import java.util.HashMap;
import java.util.Map;

/**
 * This class builds a view based on the string parameter passed in to its getView.
 */
public class ViewFactoryImpl implements ViewFactory {

  private Map<String, View> supportedViews;

  /**
   * Default constructor that contains all the initial supported views as indicated by EasyAnimator
   * initial features.
   */
  public ViewFactoryImpl() {
    supportedViews = new HashMap<>();
    supportedViews.put("visual", new VisualViewImpl());
    supportedViews.put("text", new TextualViewImpl());
    supportedViews.put("svg", new SVGViewImpl());
    supportedViews.put("edit", new EditorViewImpl());
    supportedViews.put("hybrid", new EditorViewImplementingView(new EditorView()));
  }

  /**
   * Constructor allowing the client to decide what instances of view are supported at this
   * factory.
   */
  public ViewFactoryImpl(Map<String, View> supportedViews) {
    this.supportedViews = supportedViews;
  }

  @Override
  public View getView(String viewType) throws IllegalArgumentException {
    if (this.supportedViews.containsKey(viewType)) {
      return this.supportedViews.get(viewType);
    }
    throw new IllegalArgumentException(viewType + "Unsupported view: " + viewType);
  }
}
