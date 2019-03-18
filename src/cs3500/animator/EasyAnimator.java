package cs3500.animator;

import cs3500.animator.model.Animation;
import cs3500.animator.model.AnimationImpl;
import cs3500.animator.model.AnimationReader;
import cs3500.animator.view.View;
import cs3500.animator.view.ViewFactoryImpl;
import cs3500.animator.view.VisualViewImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class EasyAnimator {
    public static void main(String[] args) {
        String inArg = "";
        String viewArg = "";
        float speedArg = 1;
        String outArg = "System.out";

        for (int i = 0; i < args.length; i += 2) {
            String command = args[i];
            String value = args[i + 1];
            System.out.println("command " + command);
            System.out.println("value " + value);

            switch (command) {
                case "-in":
                    inArg = value;
                    break;
                case "-out":
                    outArg = value;
                    break;
                case "-view":
                    viewArg = value;
                    break;
                case "-speed":
                    speedArg = Float.parseFloat(value);
                    break;
            }
        }

        if (inArg.equals("")) {
            throw new IllegalArgumentException("-in not provided");
        }
        if (viewArg.equals("")) {
            throw new IllegalArgumentException("-view not provided");
        }


        try {
            new AnimationReader();
            Animation model = AnimationReader.parseFile(new FileReader(new File(inArg).getAbsolutePath()), new AnimationImpl.Builder());

            View view = new ViewFactoryImpl().getView(viewArg);
            view.setTicksPerSecond(speedArg);
            view.setOutputDestination(outArg);

        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("File not found: " + inArg);
        }

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