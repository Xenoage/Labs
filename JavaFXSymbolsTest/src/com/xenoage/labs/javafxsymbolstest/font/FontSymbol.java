package com.xenoage.labs.javafxsymbolstest.font;

import com.xenoage.labs.javafxsymbolstest.BaseSymbol;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;

public class FontSymbol
	extends BaseSymbol {
	
	private static Font font = new Font("Bravura", 60);

	public FontSymbol(double x, double y, double dirX, double dirY) {
		super(x, y, dirX, dirY);
	}

	@Override public void draw(GraphicsContext gc) {
		gc.setFont(font);
		gc.fillText("", x, y);
	}

}
