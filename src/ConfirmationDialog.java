// A class to implement a popup confirmation to save the constructed fabric

//needs to link to the action of a button being pressed
//should spit back all user entered texts 
//should show 2 buttons confirm/dump
//confirm should allow the original button being pressed to continue
// dump should reset the window the original button came from 


import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JPanel;

public class ConfirmationDialog<E, F> {
	private JFrame parent;
	private ArrayList<E> saveFile;
	private E newE;

	
	public ConfirmationDialog(JFrame parent, SaveFile<E> saveFile,SaveFile<F> saveFile2 ,JPanel displayInfo2Confirm,E newE, String title) {
		this.setNewE(newE);
		this.setParent(parent);
		this.setSaveFile(saveFile.getInventory());
		JFrame popup = new JFrame("is this correct?");
		popup.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		popup.setSize(560, 200);
		popup.setLocationRelativeTo(null);
		popup.setVisible(true);
		LayoutManager layout = new FlowLayout();

		JLabel confirmPrompt = new JLabel("Is this correct? Press Yes to save and No to return to"+ title);
//change to +class?
		
		JButton yesButton = new JButton("yes");
		JButton noButton = new JButton("no");
		popup.setLayout(layout);
		popup.add(confirmPrompt);
		popup.add(displayInfo2Confirm);
		
		

		ActionListener addButtonListeneryes = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveFile.add(newE);
				confirmationyes(saveFile);
				Utility.clearFields(parent);
				FabricHitList(saveFile2);
				popup.dispose();
			}
		};
		ActionListener addButtonListenerno = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				popup.dispose();
			}
		};
		yesButton.addActionListener(addButtonListeneryes);
		noButton.addActionListener(addButtonListenerno);
		popup.add(yesButton);
		popup.add(noButton);
		
		
		
	}
public void FabricHitList(SaveFile<F> saveFile2) {
	if(newE instanceof Pattern) {
		Pattern newPattern=(Pattern)newE;
		@SuppressWarnings("unused")
		HitList newPatternHits= new HitList(newPattern, (SaveFile<Fabric>) saveFile2);
		DisplayHitList display= new DisplayHitList(newPatternHits, (SaveFile<Fabric>) saveFile2);
		
	}
	
}
	public void confirmationyes(SaveFile<E> saveFile2) {

		String location = saveFile2.getSaveLocation();

		try {
			saveFile2.saveInventory(location);
		} catch (IOException e) {
			// Also notify the user that we couldn't save the file for some reason.
			e.printStackTrace();
		}
	}

	public JFrame getParent() {
		return parent;
	}

	public void setParent(JFrame parent) {
		this.parent = parent;
	}

	public ArrayList<E> getSaveFile() {
		return saveFile;
	}

	public void setSaveFile(ArrayList<E> saveFile) {
		this.saveFile = saveFile;
	}

	public E getNewE() {
		return newE;
	}

	public void setNewE(E newE) {
		this.newE = newE;
	}

}
