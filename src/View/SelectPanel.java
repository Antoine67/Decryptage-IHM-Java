package View;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import java.awt.List;
import java.awt.Window;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;



public class SelectPanel extends Panel {
	
	
	private static String INITIAL_DESTINATION = "defaut.txt";
	
	private JLabel title = new JLabel("Groupe 4 - Projet Mad Max");
	private JButton load = new JButton ("Charger un fichier crypté");
	private JButton identify = new JButton ("Identifier le fichier de destination");
	private JLabel identifyLabel = new JLabel("Destination: "+INITIAL_DESTINATION,JLabel.CENTER);
	private JButton decrypt = new JButton ("Décrypter le fichier selectionné"); 
	private JLabel decryptLabel = new JLabel("Fichier: Aucun fichier pour l'instant",JLabel.CENTER);
	private JLabel filesLabel = new JLabel("Liste des fichiers cryptés enregistrés",JLabel.CENTER);
	private JButton refresh = new JButton ("Rafraichir les fichiers");
	
		

	
	final JFileChooser fileChooser = new JFileChooser();
	
	List listOfCryptedFiles = new List(5,false);
	
	private File destinationFile = new File(Controller.Controller.folderToStoreFileToDecrypt+"/"+INITIAL_DESTINATION);
	private File fileToDecrypt = null;
	
	  
	public SelectPanel(View view,Frame frame) {
		super(view,frame);
		
		
		updateFilesToDecrypt();
		
		

		
		this.add(title);
	    this.add(load);
	    this.add(identify);
	    this.add(decrypt);
	    this.add(messageDisplayer);
	    this.add(identifyLabel);
	    this.add(decryptLabel);
	    this.add(listOfCryptedFiles);
	    this.add(filesLabel);
	    this.add(refresh);
	    this.setBackground(Color.white);
	    this.setVisible(true); 
	    this.setLayout(null);
	    
	    
	    
	    messageDisplayer.setBounds(30,150,380,25 );
	    messageDisplayer.setVisible(false);
	    
	    title.setBounds(50,100, 200,25);  
	    
	    load.setBounds(100,240, 250,25);  
	    identify.setBounds(100,275, 250,25);  
	    identifyLabel.setBounds(80,300, 300,25);  
	    decrypt.setBounds(100,400, 250,25); 
	    decryptLabel.setBounds(80,425, 300,25);
	    refresh.setBounds(650,80, 200,25);
	    
	    filesLabel.setBounds(500,170, 500,30); 
	    listOfCryptedFiles.setBounds(500,200, 500,500); 
	    
	    load.addActionListener(new LoadListener());
	    identify.addActionListener(new IdentifyListener());
	    decrypt.addActionListener(new DecryptListener());
	    listOfCryptedFiles.addActionListener(new ListActionListener());
	    refresh.addActionListener(new RefreshActionListener());
	    
	    decrypt.setEnabled(false);    
	    
	    
	    
	}


	public void paintComponent(Graphics g){
		
		try {
		      Image img = ImageIO.read(new File("assets/img/cesi.png"));
		      g.drawImage(img, 30, 10, this);
		    } 
		catch (IOException e) {
		      e.printStackTrace();
		    }

	}
	
	
	public void updateFilesToDecrypt() {
		listOfCryptedFiles.removeAll();
		ArrayList<String> files = view.loadFilesToDecrypt();
		files.forEach((file) -> {
			file = file.substring(file.lastIndexOf("\\") + 1);
			listOfCryptedFiles.add(file);
		});
	}
	  
	
	
	  class LoadListener implements ActionListener{
		    public void actionPerformed(ActionEvent e) {
		    	

		    	fileChooser.setCurrentDirectory(new File("/"));
		    	fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		    	int result = fileChooser.showOpenDialog(new JFrame());
		    	
		    	if(result == JFileChooser.APPROVE_OPTION) {
		    	       //System.out.println("Selected file: " + fileChooser.getSelectedFile().getName());
		    	       view.loadFile(fileChooser.getSelectedFile());
		    	       updateFilesToDecrypt();
		    	}
		    }
		  }
	  
	  class IdentifyListener implements ActionListener{
		    

			public void actionPerformed(ActionEvent e) {
		    	fileChooser.setCurrentDirectory(new File("/"));
		    	fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		    	int result = fileChooser.showOpenDialog(new JFrame());
		    	
		    	if(result == JFileChooser.APPROVE_OPTION) {
		    	       System.out.println("Selected file: " + fileChooser.getSelectedFile().getName());
		    	       destinationFile = fileChooser.getSelectedFile(); 
		    	       identifyLabel.setText("Destination: "+destinationFile.getName());
		    	       frame.repaint();
		    	       frame.revalidate();
		    	}
		    }
		  }
	  
	  
	  SelectPanel that = this;
	  class DecryptListener implements ActionListener{
		    public void actionPerformed(ActionEvent e) {
	
		    	createProgressBarModal(e);
		    	//System.out.println(destinationFile.getPath());
		    	
		    }

			
		  }
	  
	  class ListActionListener implements ActionListener{
		    public void actionPerformed(ActionEvent e) {
		    	if(listOfCryptedFiles.getSelectedItem() != null) {
		    		decrypt.setEnabled(true);
		    		decryptLabel.setText("Fichier: "+listOfCryptedFiles.getSelectedItem() );
		    		fileToDecrypt = new File(listOfCryptedFiles.getSelectedItem());
		    	    frame.repaint();
		    	    frame.revalidate();
		    	}
		    }
		  }
	  
	  class RefreshActionListener implements ActionListener{
		    public void actionPerformed(ActionEvent e) {
		    	updateFilesToDecrypt();
		    	displaySuccessMessage("Fichiers mis à jour");
		    }
	  }
	  
	  




	public void displayErrorMessage(String message) {
		messageDisplayer.setBackground(Color.red);
		messageDisplayer.setText(message);
		messageDisplayer.setVisible(true);
		frame.repaint();
		frame.revalidate();
	}


	public void displaySuccessMessage(String message) {
		messageDisplayer.setBackground(Color.green);
		messageDisplayer.setText(message);
		messageDisplayer.setVisible(true);
		frame.repaint();
		frame.revalidate();
	}


	public void setProgressBarState(boolean b) {

		
	}


	public void setTriedKeys(int triedKeysNumber) {

		triedKeys.setText("Clés essayées: "+Integer.toString(triedKeysNumber));
	}
	
	
	
	
	
	/*
	private JButton cancelDecrypt = new JButton("Annuler");
	JProgressBar progressBar;
    progressBar = new JProgressBar(0, 100);
    stateDisplayer.add(BorderLayout.NORTH, progressBar);
    stateDisplayer.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
    stateDisplayer.setSize(150, 75);
    stateDisplayer.setLocationRelativeTo(frame);
    
    triedKeys.setText("Clés tentées : 0");

    stateDisplayer.add(BorderLayout.CENTER, triedKeys);
    stateDisplayer.add(BorderLayout.SOUTH,cancelDecrypt);
    stateDisplayer.setSize(300,150);
    stateDisplayer.setResizable(false);*/

	
	private JLabel triedKeys = new JLabel();
	public void createProgressBarModal(ActionEvent evt) {
	      SwingWorker<Void, Void> mySwingWorker = new SwingWorker<Void, Void>(){
	         @Override
	         protected Void doInBackground() throws Exception {

	        	 if(view.pcs_decrypter(fileToDecrypt.getName(),destinationFile.getPath(), that)) {
			    		System.out.println("file decrypted");
			    		displaySuccessMessage("Fichier \""+fileToDecrypt.getName()+"\"decrypté dans "+destinationFile.getName());
			    	}else {
			    		System.out.println("file not decrypted");
			    		displayErrorMessage("Erreur lors du decryptage du fichier "+fileToDecrypt.getName());
			    	}
	            
	            return null;
	         }
	      };

	      Window win = SwingUtilities.getWindowAncestor((AbstractButton)evt.getSource());
	      final JDialog stateDisplayer = new JDialog(win, "Decryptage en cours...", ModalityType.APPLICATION_MODAL);

	      mySwingWorker.addPropertyChangeListener(new PropertyChangeListener() {

	         @Override
	         public void propertyChange(PropertyChangeEvent evt) {
	            if (evt.getPropertyName().equals("state")) {
	               if (evt.getNewValue() == SwingWorker.StateValue.DONE) {
	            	   stateDisplayer.dispose();
	               }
	            }
	         }
	      });
	      mySwingWorker.execute();

	      JProgressBar progressBar = new JProgressBar();
	      progressBar.setIndeterminate(true);
	      JPanel panel = new JPanel(new BorderLayout());
	      
	      panel.add(new JLabel("Decryptage en cours..."), BorderLayout.NORTH);
	      panel.add(progressBar, BorderLayout.CENTER);
	      panel.add(triedKeys, BorderLayout.SOUTH);
	      
	      stateDisplayer.add(panel);
	      //stateDisplayer.pack();
	      stateDisplayer.setSize(500,100);
	      stateDisplayer.setResizable(false);
	      stateDisplayer.setLocationRelativeTo(win);
	      stateDisplayer.setVisible(true);
	   }
	
	
	
	  
	  
	  
}


 