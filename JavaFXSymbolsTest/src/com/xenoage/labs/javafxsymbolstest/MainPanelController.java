package com.xenoage.labs.javafxsymbolstest;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import javax.swing.JOptionPane;

/**
 * Controller for the MainPanel.fxml
 * 
 * @author Andreas Wenger
 */
public class MainPanelController {
	
	@FXML private Slider sliderSymbols;
	@FXML private TabPane tabPane;
	@FXML private Canvas canvasPaths;
	
	private IntegerProperty symbolsCount = new SimpleIntegerProperty();
	
	
	public void initialize() { 
		//number of symbols
		symbolsCount.bind(sliderSymbols.valueProperty());
		sliderSymbols.valueProperty().addListener(n -> JOptionPane.showMessageDialog(null, n));
		//tab changes
		tabPane.getSelectionModel().selectedItemProperty().addListener(
			(ov, oldVal, newVal) -> JOptionPane.showMessageDialog(null, newVal.getText()));
	}
	
	

}
