package cs3500.animator.view;

import java.util.HashMap;
import java.util.Map;

public class ViewFactoryImpl implements ViewFactory {

    Map<String, View> supportedViews;


    /**
     * Default constructor that contains all the initial supported views as indicated by EasyAnimator
     * initial features.
     */
    public ViewFactoryImpl() {
        supportedViews = new HashMap<>();
        supportedViews.put("visual", new VisualViewImpl());
        supportedViews.put("text", new TextualViewImpl());
        supportedViews.put("svg", new SVGViewImpl());
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
