package com.xenoage.labs.javafxsymbolstest;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import com.xenoage.labs.javafxsymbolstest.font.FontCanvas;
import com.xenoage.labs.javafxsymbolstest.paths.PathCanvas;

/**
 * The base canvas for {@link PathCanvas} and {@link FontCanvas}.
 * 
 * @author Andreas Wenger
 */
public class BaseCanvas
	extends Canvas {
	
	protected MainPanelController mainPanel;
	private AnimationTimer timer;
	
	protected List<BaseSymbol> symbols = new ArrayList<BaseSymbol>();
	
	//FPS counter
	private long lastSecondStart = -1;
	private int frameCount = 0;


	public BaseCanvas(MainPanelController mainPanel) {
		this.mainPanel = mainPanel;
		//repaint on resize
		widthProperty().addListener(evt -> draw());
		heightProperty().addListener(evt -> draw());
		//init timer
		timer = new AnimationTimer() {

			@Override public void handle(long now) {
				draw();
			}
		};
		lastSecondStart = System.nanoTime();
	}

	protected void draw() {
		GraphicsContext gc = this.getGraphicsContext2D();
		//clear area with white
		gc.setFill(Color.WHITE);
		gc.fillRect(0, 0, this.getWidth(), this.getHeight());
		//draw debug lines
		gc.setStroke(Color.RED);
		gc.strokeLine(0, 0, this.getWidth(), this.getHeight());
		gc.strokeLine(this.getWidth(), 0, 0, this.getHeight());
		//animate and draw symbols
		gc.setFill(Color.BLACK);
		for (int i = 0; i < mainPanel.symbolsCount.intValue(); i++) {
			BaseSymbol symbol = symbols.get(i);
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
			symbol.draw(gc);
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
