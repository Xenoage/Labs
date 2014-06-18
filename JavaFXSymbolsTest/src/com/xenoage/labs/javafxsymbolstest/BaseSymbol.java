package com.xenoage.labs.javafxsymbolstest;

import javafx.scene.canvas.GraphicsContext;

public abstract class BaseSymbol {

	public double x = 0;
	public double y = 0;
	public double dirX = 0;
	public double dirY = 0;


	public BaseSymbol(double x, double y, double dirX, double dirY) {
		this.x = x;
		this.y = y;
		this.dirX = dirX;
		this.dirY = dirY;
	}

	public abstract void draw(GraphicsContext gc);

}
