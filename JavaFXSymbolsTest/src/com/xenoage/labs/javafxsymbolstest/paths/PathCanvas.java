package com.xenoage.labs.javafxsymbolstest.paths;

import com.xenoage.labs.javafxsymbolstest.MainPanelController;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * A canvas where animated symbols are drawn.
 * 
 * @author Andreas Wenger
 */
public class PathCanvas
	extends Canvas {
	
	private MainPanelController mainPanel;
	private AnimationTimer timer;
	
	//symbols
	private PathSymbol[] symbols;

	//FPS counter
	private long lastSecondStart = -1;
	private int frameCount = 0;


	public PathCanvas(MainPanelController mainPanel) {
		this.mainPanel = mainPanel;
		//repaint on resize
		widthProperty().addListener(evt -> draw());
		heightProperty().addListener(evt -> draw());
		//init symbols
		symbols = new PathSymbol[1000];
		for (int i = 0; i < symbols.length; i++) {
			symbols[i] = new PathSymbol(Math.random() * getWidth(), Math.random() * getHeight(),
				Math.random() * 2 - 1, Math.random() * 2 - 1);
		}
		//init timer
		timer = new AnimationTimer() {

			@Override public void handle(long now) {
				draw();
			}
		};
		lastSecondStart = System.nanoTime();
		timer.start();
	}

	private void draw() {
		GraphicsContext gc = this.getGraphicsContext2D();
		//clear area with white
		gc.setFill(Color.WHITE);
		gc.fillRect(0, 0, this.getWidth(), this.getHeight());
		//draw debug lines
		gc.setStroke(Color.BLUE);
		gc.strokeLine(0, 0, this.getWidth(), this.getHeight());
		gc.strokeLine(this.getWidth(), 0, 0, this.getHeight());
		//animate and draw symbols
		gc.setFill(Color.BLACK);
		for (int i = 0; i < mainPanel.symbolsCount.intValue(); i++) {
			symbols[i].x += symbols[i].dirX * 5;
			symbols[i].y += symbols[i].dirY * 5;
			if (symbols[i].x < 0)
				symbols[i].dirX = Math.abs(symbols[i].dirX);
			if (symbols[i].y < 0)
				symbols[i].dirY = Math.abs(symbols[i].dirY);
			if (symbols[i].x > getWidth())
				symbols[i].dirX = -1 * Math.abs(symbols[i].dirX);
			if (symbols[i].y > getHeight())
				symbols[i].dirY = -1 * Math.abs(symbols[i].dirY);
			symbols[i].draw(gc);
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

	@Override public boolean isResizable() {
		return true;
	}

	@Override public double prefWidth(double height) {
		return 100; //very small preferred size, to allow downsizing
	}

	@Override public double prefHeight(double width) {
		return 100; //very small preferred size, to allow downsizing
	}

}
