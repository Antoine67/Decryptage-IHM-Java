package Controller;

import java.awt.Component;
import java.math.BigInteger;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import View.SelectPanel;

public class Decrypter {

	Controller controller;

	// constructor of the decrypter class
	public Decrypter(Controller controller) {
		this.controller = controller;

	}

	// Here we enter the maximum size of the key
	public static int MAX_KEY_LENGHT = 12;

	// We declare a variable that gives us the time of the keys
	String temp_key;

}
