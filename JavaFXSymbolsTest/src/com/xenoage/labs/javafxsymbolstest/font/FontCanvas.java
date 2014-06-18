package com.xenoage.labs.javafxsymbolstest.font;

import com.xenoage.labs.javafxsymbolstest.BaseCanvas;
import com.xenoage.labs.javafxsymbolstest.MainPanelController;

/**
 * A canvas where animated symbols are drawn as font glyphs.
 * 
 * @author Andreas Wenger
 */
public class FontCanvas
	extends BaseCanvas {

	public FontCanvas(MainPanelController mainPanel) {
		super(mainPanel);
		//init symbols
		for (int i = 0; i < 1000; i++) {
			symbols.add(new FontSymbol(Math.random() * getWidth(), Math.random() * getHeight(),
				Math.random() * 2 - 1, Math.random() * 2 - 1));
		}
	}

}
