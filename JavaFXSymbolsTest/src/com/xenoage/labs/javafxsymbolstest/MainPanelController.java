package com.xenoage.labs.javafxsymbolstest;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import javax.swing.JOptionPane;

import com.xenoage.labs.javafxsymbolstest.font.FontCanvas;
import com.xenoage.labs.javafxsymbolstest.nodes.NodesPane;
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
	@FXML private AnchorPane parentForPathCanvas;
	@FXML private AnchorPane parentForNodes;
	@FXML private AnchorPane parentForFontCanvas;

	public IntegerProperty symbolsCount = new SimpleIntegerProperty();


	public void initialize() {
		//number of symbols
		symbolsCount.bind(sliderSymbols.valueProperty());
		//sliderSymbols.valueProperty().addListener(n -> JOptionPane.showMessageDialog(null, n));
		//tab changes
		tabPane.getSelectionModel().selectedItemProperty().addListener((ov, oldVal, newVal) -> {
			setTabActive(oldVal, false);
			setTabActive(newVal, true);
		});
		//add path canvas
		{
			PathCanvas c = new PathCanvas(this);
			c.widthProperty().bind(parentForPathCanvas.widthProperty());
			c.heightProperty().bind(parentForPathCanvas.heightProperty());
			parentForPathCanvas.getChildren().add(c);
			c.start();
		}
		//add nodes pane
		{
			NodesPane c = new NodesPane(this);
			AnchorPane.setLeftAnchor(c, 10.0);
			AnchorPane.setTopAnchor(c, 10.0);
			AnchorPane.setRightAnchor(c, 10.0);
			AnchorPane.setBottomAnchor(c, 10.0);
			parentForNodes.getChildren().add(c);
		}
		//add font canvas
		{
			FontCanvas c = new FontCanvas(this);
			c.widthProperty().bind(parentForFontCanvas.widthProperty());
			c.heightProperty().bind(parentForFontCanvas.heightProperty());
			parentForFontCanvas.getChildren().add(c);
		}
	}

	private void setTabActive(Tab tab, boolean active) {
		AnchorPane pane = (AnchorPane) tab.getContent();
		if (pane.getChildren().size() == 0)
			return;
		Node node = pane.getChildren().get(0);
		if (node instanceof BaseCanvas) {
			BaseCanvas canvas = (BaseCanvas) node;
			if (active)
				canvas.start();
			else
				canvas.stop();
		}
		else if (node instanceof NodesPane) {
			NodesPane p = (NodesPane) node;
			if (active)
				p.start();
			else
				p.stop();
		}
	}

	public void onFPSChange(int fps) {
		lblFPS.setText("" + fps);
	}

}
