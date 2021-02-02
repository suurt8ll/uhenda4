package com.connectfour.desktop;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.connectfour.Games;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setTitle("ConnectFour");
		config.setWindowedMode(800, 600);
		config.setResizable(true);
		config.useOpenGL3(true,3,2);
		new Lwjgl3Application(new Games(), config);
	}
}
