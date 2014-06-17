package com.xenoage.labs.javafxsymbolstest;

import javax.swing.JOptionPane;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Slider;

/**
 * Controller for the MainPanel.fxml
 * 
 * @author Andreas Wenger
 */
public class MainPanelController {
	
	@FXML private Slider sliderSymbols;
	@FXML private Canvas canvasPaths;
	
	private IntegerProperty symbolsCount = new SimpleIntegerProperty();
	
	
	public void initialize() { 
		symbolsCount.bind(sliderSymbols.valueProperty());
		symbolsCount.addListener(n -> JOptionPane.showMessageDialog(null, n));
	}
	
	

}
