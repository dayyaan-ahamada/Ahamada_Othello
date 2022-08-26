package Main;

import javafx.application.Application;
import javafx.stage.Stage;

import GUI.*;

public class othello_main extends Application{

	public static void main(String[] args) {
        Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Othello");
		Interface i = new Interface();
		
		i.show();
	}
}
