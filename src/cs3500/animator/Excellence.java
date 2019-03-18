package cs3500.animator;

import cs3500.animator.model.Animation;
import cs3500.animator.model.AnimationImpl;
import cs3500.animator.view.SVGViewImpl;
import cs3500.animator.view.TextualViewImpl;
import cs3500.animator.view.View;
import cs3500.animator.view.VisualViewImpl;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public final class Excellence {
    public static void main(String[] args) {
        View mainView = null;
        String input = "";
        PrintStream output = System.out;
        int speed = 1;
        for (int i = 0; i < args.length; i += 2) {
            if (args[i].equals("-view") && args[i + 1].equals("text")) {
                mainView = new TextualViewImpl();
            } else if (args[i].equals("-view") && args[i + 1].equals("visual")) {
                mainView = new VisualViewImpl();
            } else if (args[i].equals("-view") && args[i + 1].equals("text")) {
                mainView = new SVGViewImpl();
            } else if (args[i].equals("-speed")) {
                speed = Integer.parseInt(args[i + 1]);
            } else if (args[i].equals("-in")) {
                input = args[i + 1];
            } else if (args[i].equals("-out")) {
                //output = args[i + 1];
            }
        }
        if (mainView == null) {
            throw new IllegalArgumentException("View type was unspecified.");
        } else {
            mainView.setTicksPerSecond(speed);

        }

        //runVisualExample();

    }

    public static void runVisualExample() {
        View visualView = new VisualViewImpl();

        Animation exampleModel = new AnimationImpl();
        exampleModel.addShape("R", "rectangle");
        exampleModel.addRotationless2DMotion("R",
                1, 200, 200, 50, 100, 255, 0, 0,
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

        visualView.displayView(exampleModel);
    }
}