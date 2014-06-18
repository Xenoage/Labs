package com.xenoage.labs.javafxsymbolstest.nodes;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;
import javafx.scene.shape.SVGPath;

import com.xenoage.labs.javafxsymbolstest.MainPanelController;

/**
 * A pane where animated symbols are drawn as JavaFX nodes.
 * 
 * @author Andreas Wenger
 */
public class NodesPane
	extends Pane {
	
	protected MainPanelController mainPanel;
	private AnimationTimer timer;
	
	protected List<NodeSymbol> symbols = new ArrayList<NodeSymbol>();
	private int visibleNodesCount = 0;
	
	//FPS counter
	private long lastSecondStart = -1;
	private int frameCount = 0;


	public NodesPane(MainPanelController mainPanel) {
		this.mainPanel = mainPanel;
		setStyle("-fx-background-color: white;");
		//add nodes
		for (int i = 0; i < 10000; i++) {
			NodeSymbol symbol = new NodeSymbol(Math.random() * getWidth(), Math.random() * getHeight(),
				Math.random() * 2 - 1, Math.random() * 2 - 1);
			symbols.add(symbol);
			getChildren().add(symbol.node);
		}
		//init timer
		timer = new AnimationTimer() {

			@Override public void handle(long now) {
				move();
			}
		};
		lastSecondStart = System.nanoTime();
	}

	protected void move() {
		//show/hide nodes
		if (visibleNodesCount != mainPanel.symbolsCount.intValue()) {
			visibleNodesCount = mainPanel.symbolsCount.intValue();
			for (int i = 0; i < symbols.size(); i++)
				symbols.get(i).node.setVisible(i < visibleNodesCount);
		}
		//animate symbols
		for (int i = 0; i < mainPanel.symbolsCount.intValue(); i++) {
			NodeSymbol symbol = symbols.get(i);
			symbol.x += symbol.dirX * 5;
			symbol.y += symbol.dirY * 5;
			if (symbol.x < 0)
				symbol.dirX = Math.abs(symbol.dirX);
			if (symbol.y < 0)
				symbol.dirY = Math.abs(symbol.dirY);
			if (symbol.x > getWidth())
				symbol.dirX = -1 * Math.abs(symbol.dirX);
			if (symbol.y > getHeight())
				symbol.dirY = -1 * Math.abs(symbol.dirY);
			symbol.node.setLayoutX(symbol.x);
			symbol.node.setLayoutY(symbol.y);
		}
		//measure FPS
		frameCount++;
		long currentTime = System.nanoTime();
		if (currentTime > lastSecondStart + 1000000000) {
			int fps = frameCount;
			frameCount = 0;
			lastSecondStart = currentTime;
			mainPanel.onFPSChange(fps);
		}
	}
	
	public void start() {
		timer.start();
	}
	
	public void stop() {
		timer.stop();
	}

}
