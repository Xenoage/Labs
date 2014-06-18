package com.xenoage.labs.javafxsymbolstest.paths;

import com.xenoage.labs.javafxsymbolstest.BaseCanvas;
import com.xenoage.labs.javafxsymbolstest.MainPanelController;

/**
 * A canvas where animated symbols are drawn as paths.
 * 
 * @author Andreas Wenger
 */
public class PathCanvas
	extends BaseCanvas {
	
	public PathCanvas(MainPanelController mainPanel) {
		super(mainPanel);
		//init symbols
		for (int i = 0; i < 10000; i++) {
			symbols.add(new PathSymbol(Math.random() * getWidth(), Math.random() * getHeight(),
				Math.random() * 2 - 1, Math.random() * 2 - 1));
		}
	}

}
