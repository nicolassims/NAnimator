package cs3500.animator.view;

import cs3500.animator.model.Animation;
import cs3500.animator.model.AnimationImpl;
import org.junit.Before;
import org.junit.Test;

public class SVGViewImplTest {

    private View visualView;

    private Animation exampleModel;

    @Before
    public void setUp() {
        visualView = new SVGViewImpl();

        exampleModel = new AnimationImpl();
        exampleModel.addShape("R", "rectangle");
        exampleModel.addRotationless2DMotion("R",
                0, 200, 200, 50, 100, 255, 0, 0,
                10, 200, 200, 50, 100, 255, 0, 0);
        exampleModel.addRotationless2DMotion("R",
                10, 200, 200, 50, 100, 255, 0, 0,
                50, 300, 300, 50, 100, 255, 0, 0);
        exampleModel.addRotationless2DMotion("R",
                50, 300, 300, 50, 100, 255, 0, 0,
                51, 300, 300, 50, 100, 255, 0, 0);
        exampleModel.addRotationless2DMotion("R",
                51, 300, 300, 50, 100, 255, 0, 0,
                70, 300, 300, 25, 100, 255, 0, 0);
        exampleModel.addRotationless2DMotion("R",
                70, 300, 300, 25, 100, 255, 0, 0,
                100, 200, 200, 25, 100, 255, 0, 0);

        exampleModel.addShape("C", "ellipse");
        exampleModel.addRotationless2DMotion("C",
                6, 440, 70, 120, 60, 0, 0, 255,
                20, 440, 70, 120, 60, 0, 0, 255);
        exampleModel.addRotationless2DMotion("C",
                20, 440, 70, 120, 60, 0, 0, 255,
                50, 440, 250, 120, 60, 0, 0, 255);
        exampleModel.addRotationless2DMotion("C",
                50, 440, 250, 120, 60, 0, 0, 255,
                70, 440, 370, 120, 60, 0, 170, 85);
        exampleModel.addRotationless2DMotion("C",
                70, 440, 370, 120, 60, 0, 170, 85,
                80, 440, 370, 120, 60, 0, 255, 0);
        exampleModel.addRotationless2DMotion("C",
                80, 440, 370, 120, 60, 0, 255, 0,
                100, 440, 370, 120, 60, 0, 255, 0);

    }

    @Test
    public void displayView() {

        visualView.displayView(exampleModel);
    }
}