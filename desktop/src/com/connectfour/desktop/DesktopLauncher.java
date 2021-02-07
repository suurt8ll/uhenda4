package com.connectfour.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.connectfour.Games;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setTitle("ConnectFour");
		config.setWindowedMode(800, 600);
		config.setWindowPosition(1, 30);
		config.setResizable(true);
		config.setWindowIcon(Files.FileType.Absolute, "C:\\Users\\leoku\\Desktop\\UhendaNeli\\core\\assets\\jamesicon_128px.png");
		config.useOpenGL3(true,3,2);
		new Lwjgl3Application(new Games(), config);
	}
}
