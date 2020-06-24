package victor.training.oo.behavioral.visitor;

import victor.training.oo.behavioral.visitor.model.Circle;
import victor.training.oo.behavioral.visitor.model.Shape;
import victor.training.oo.behavioral.visitor.model.Square;

import java.util.Arrays;
import java.util.List;

public class VisitorPlay {

	public static void main(String[] args) {
		List<Shape> shapes = Arrays.asList(
				new Square(10), 
				new Circle(5), 
				new Square(5));
//
//		PerimeterCalculatorVisitor perimeterCalculator = new PerimeterCalculatorVisitor();
//		for (Shape shape : shapes) {
//			shape.accept(perimeterCalculator);
//		}
//		System.out.println("Total perimeter: " + perimeterCalculator.getTotal());

		double area = 0;
		for (Shape shape : shapes) {
			area += shape.area();
		}

		System.out.println("Total area: " + area); // TODO

	}

}
