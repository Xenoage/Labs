package com.xenoage.labs.javafxsymbolstest;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class JavaFXSymbolsTest
	extends Application {
	
	public static void main(String... args) {
		JavaFXSymbolsTest.launch(args);
	}

	@Override public void start(Stage stage)
		throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("MainPanel.fxml"));
		
		Scene scene = new Scene(root);

		stage.setTitle("FXML Welcome");
		stage.setScene(scene);
		stage.show();
	}

}
