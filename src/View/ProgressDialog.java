package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JProgressBar;

public class ProgressDialog extends JDialog {
	 
    public ProgressDialog(Frame owner) {
        super(owner, true);
        JProgressBar progressBar = new JProgressBar(0, 100);
        progressBar.setIndeterminate(true);
        this.getContentPane().add(progressBar);
        this.setLocationRelativeTo(owner);
        this.pack();
    }
}