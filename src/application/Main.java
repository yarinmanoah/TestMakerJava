package application;

import java.io.IOException;
import id316397249_id208945956.controller.QuestionController;
import id316397249_id208945956.model.Manager;
import id316397249_id208945956.viewTest.TestGUI;
import id316397249_id208945956.viewTest.Testable;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {



	public void start(Stage primaryStage) {
		Manager m = new Manager();
		Testable view = new TestGUI(primaryStage);
		QuestionController controller = new QuestionController(m, view);
		try {
			m.inFile();
		} catch (ClassNotFoundException | IOException e) {
			System.out.println("Didnt load");
			m.initial();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
