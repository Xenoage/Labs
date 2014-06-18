package com.xenoage.labs.javafxsymbolstest;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

import javax.swing.JOptionPane;

import com.xenoage.labs.javafxsymbolstest.paths.PathCanvas;

/**
 * Controller for the MainPanel.fxml
 * 
 * @author Andreas Wenger
 */
public class MainPanelController {
	
	@FXML private Slider sliderSymbols;
	@FXML private Label lblFPS;
	@FXML private TabPane tabPane;
	@FXML private Canvas canvasPaths;
	@FXML private AnchorPane parentForFontCanvas;
	
	public IntegerProperty symbolsCount = new SimpleIntegerProperty();
	
	
	public void initialize() { 
		//number of symbols
		symbolsCount.bind(sliderSymbols.valueProperty());
		//sliderSymbols.valueProperty().addListener(n -> JOptionPane.showMessageDialog(null, n));
		//tab changes
		tabPane.getSelectionModel().selectedItemProperty().addListener(
			(ov, oldVal, newVal) -> JOptionPane.showMessageDialog(null, newVal.getText()));
		//add canvas
		PathCanvas c = new PathCanvas(this);
    c.widthProperty().bind(parentForFontCanvas.widthProperty());
    c.heightProperty().bind(parentForFontCanvas.heightProperty());
		parentForFontCanvas.getChildren().add(c);
	}
	
	
	public void onFPSChange(int fps) {
		lblFPS.setText("" + fps);
	}
	

}
