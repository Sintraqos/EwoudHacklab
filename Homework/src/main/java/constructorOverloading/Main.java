package constructorOverloading;

import java.awt.*;
import java.util.ArrayList;

public class Main {
    ShapeManager shapeManager;

    void main() {
        shapeManager = new ShapeManager();
    }
}

//region Shape Manager

@SuppressWarnings("InfiniteLoopStatement")  // Since the loop is infinite, and it needs to be, suppress the warning it throws
class ShapeManager {

    public static ShapeManager instance;
    public ShapeMaster currentShape;
    public ShapeMaster previousShape;

    FenceManager fenceManager;

    public ShapeManager() {
        if (instance == null)
            instance = this;
        if (instance != this) return;

        fenceManager = new FenceManager();

        while (true) {
            System.out.println("""
                    Enter: '1' or 'create shape' to create a new shape
                    Enter: '2' or 'create fence' to create a new fence
                    Enter: '3' or 'compare shape' to compare two shapes
                    """);

            switch (System.console().readLine().toLowerCase()) {
                case "1", "create shape":   // Create new shape
                    CreateShape();
                    continue;

                case "2", "create fence":   // Create fence
                    fenceManager.CreateFence(currentShape);
                    continue;

                case "3", "compare shape":  // Compare shapes
                    CompareShape();
                    continue;

                default:
                    System.out.println("Invalid input!");
                    continue;
            }
        }
    }

    void CreateShape() {
        System.out.println("""
                Enter: '1' or 'create rectangle' to create a new shape
                Enter: '2' or 'create circle' to create a new fence
                """);

        switch (System.console().readLine().toLowerCase()) {
            case "1", "create rectangle":   // Create Rectangle
                if (currentShape != null) previousShape = currentShape;
                currentShape = new Rectangle().CreateRectangle();

                //System.out.println(STR."Created new rectangle with width: \{((Rectangle)currentShape).width} and length: \{((Rectangle)currentShape).length}");

                break;

            case "2", "create circle":   // Create Circle
                if (currentShape != null) previousShape = currentShape;
                currentShape = new Circle().CreateCircle();

                //System.out.println(STR."Created new circle with radius: \{((Circle)currentShape).radius}");

                break;

            default:
                System.out.println("Invalid input!");
                break;
        }
    }

    void CompareShape() {
        // If there are no shapes stored create new shapes
        if (currentShape == null && previousShape == null) {
            System.out.println("No shapes found!");
        } else if (currentShape != null && previousShape == null) {
            System.out.println("""
                    Only 1 shape found, do you wish to create a new shape?
                    Enter: '1' or 'yes' to create a new shape
                    Enter: '2' or 'no' to return
                    """);

            switch (System.console().readLine().toLowerCase()) {
                case "1", "yes":   // Create new shape
                    CreateShape();
                    break;

                case "2", "no":   // Return to main
                    break;

                default:
                    System.out.println("Invalid input!");
                    CompareShape();
                    break;
            }
        } else {
            System.out.println("""
                Enter: '1' or 'rectangle' to compare rectangle shapes
                Enter: '2' or 'circle' to compare circle shapes
                """);

            switch (System.console().readLine().toLowerCase()) {
                case "1", "rectangle":   // Create Rectangle
                    CompareRectangle();
                    break;

                case "2", "circle":   // Create Circle
                    CompareCircle();
                    break;

                default:
                    System.out.println("Invalid input!");
                    break;
            }
        }
    }

    public void CompareRectangle() {
        if (!(currentShape instanceof Rectangle)) {
            System.out.println("Shape 1 is not a rectangle!");
            return;
        }
        if (!(previousShape instanceof Rectangle)) {
            System.out.println("Shape 2 is not a rectangle!");
            return;
        }

        //System.out.println(STR."Rectangle 1 fits: \{Math.floor(currentShape.getSurface() / previousShape.getSurface())} times in rectangle 2");
    }

    public void CompareCircle() {
        if (!(currentShape instanceof Circle)) {
            System.out.println("Shape 1 is not a rectangle!");
            return;
        }
        if (!(previousShape instanceof Circle)) {
            System.out.println("Shape 2 is not a rectangle!");
            return;
        }

        //System.out.println(STR."Circle 1 fits: \{Math.floor(currentShape.getSurface() / previousShape.getSurface())} times in circle 2");
    }
}

//endregion

//region Shape

class ShapeMaster {
    double circumference;
    double surface;

    public double getCircumference() {
        return circumference;
    }

    public double getSurface() {
        return surface;
    }
}

//endregion

//region Rectangle

class Rectangle extends ShapeMaster {

    int width;
    int length;

    public Rectangle() {
        width = 2;
        length = 4;

        SetVariables();
    }

    public Rectangle(int size) {
        width = size;
        length = size;

        SetVariables();
    }

    public Rectangle(int width, int length) {
        this.width = width;
        this.length = length;

        SetVariables();
    }

    void SetVariables() {
        circumference = (width * 2) + (length * 2);
        surface = width * length;
    }

    public Rectangle CreateRectangle() {
//        System.out.println(STR."""
//                             Enter length and width for the new rectangle, you can also only enter the size of the rectangle if the length and width are the same.
//                             If you leave it blank it will create a rectangle with" '\{length}' as it's length, and '\{width}' as it's width
//                             """);
        String[] userInputSplit = System.console().readLine().split(" ");

        if (userInputSplit.length < 1) {
            return new Rectangle();
        } else if (userInputSplit.length == 1) {
            // Parse size
            Integer size = Functions.parseIntOrNull(userInputSplit[0]);
            if (size == null) {
                System.out.println("Invalid input!");
                return CreateRectangle();
            }

            return new Rectangle(size);
        } else {
            // Parse width
            Integer width = Functions.parseIntOrNull(userInputSplit[0]);
            if (width == null) {
                System.out.println("Invalid input!");
                return CreateRectangle();
            }

            // Parse length
            Integer length = Functions.parseIntOrNull(userInputSplit[1]);
            if (length == null) {
                System.out.println("Invalid input!");
                return CreateRectangle();
            }

            return new Rectangle(width, length);
        }
    }
}

//endregion

//region Circle

class Circle extends ShapeMaster {
    double radius = 8;
    double diameter;

    public Circle() {
        SetVariables();
    }

    public Circle(double radius) {
        this.radius = radius;

        SetVariables();
    }

    void SetVariables() {
        diameter = radius * 2;
        circumference = Math.PI * diameter;
        surface = radius * Math.PI;
    }

    public Circle CreateCircle() {
//        System.out.println(STR."""
//                             Enter radius for the new circle,
//                             if you leave it blank it will create a circle with '\{radius}' as it's size.
//                             """);
        String userInput = System.console().readLine();

        if (userInput.isEmpty()) {
            return new Circle();
        } else {
            // Parse radius
            Double radius = Functions.parseDoubleOrNull(userInput);
            if (radius == null) {
                System.out.println("Invalid input!");
                return CreateCircle();
            }

            return new Circle(radius);
        }
    }
}

//endregion

//region Fence

class FenceManager {

    public void CreateFence(ShapeMaster currentShape) {
        System.out.println("Enter how many poles the fence needs to have:");
        Integer poleCount = Functions.parseIntOrNull(System.console().readLine());
        if (poleCount == null) {
            System.out.println("Invalid Input!");
            CreateFence(currentShape);
            return;
        }

//        System.out.println(STR."Polecount: \{poleCount}");
//
//        System.out.println(STR."Shape Info: Circumference: \{Functions.formatDecimal(currentShape.getCircumference())} Meter - Current Surface: \{Functions.formatDecimal(currentShape.getSurface())} Meter");
//        System.out.println(STR."Should Place Poles Every: \{Functions.formatDecimal(currentShape.getSurface() / poleCount)} Meter");
    }
}

//endregion

//region Functions

class Functions {

    // Try parse int from string
    public static Integer parseIntOrNull(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    // Try parse double from string
    public static Double parseDoubleOrNull(String value) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    // Parse a double to 2digits after the comma.
    // IE: '1.23' instead of '1.2345678'
    public static String formatDecimal(double input) {
        return String.format("%.2f", input);
    }
}

//endregion
