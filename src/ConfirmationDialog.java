// A class to implement a popup confirmation to save the constructed fabric

//needs to link to the action of a button being pressed
//should spit back all user entered texts 
//should show 2 buttons confirm/dump
//confirm should allow the original button being pressed to continue
// dump should reset the window the original button came from 

import java.awt.BorderLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JPanel;

public class ConfirmationDialog<e> {
	private JFrame parent;
	private ArrayList<e> saveFile;
	private e newE;

	public ConfirmationDialog(JFrame parent, SaveFile<e> saveFile, JPanel displayInfo2Confirm,e newE, String title) {
		this.setNewE(newE);
		this.setParent(parent);
		this.setSaveFile(saveFile.getInventory());
		JFrame popup = new JFrame("is this correct?");
		popup.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		popup.setSize(560, 200);
		popup.setLocationRelativeTo(null);
		popup.setVisible(true);
		LayoutManager layout = new BorderLayout();

		JLabel confirmPrompt = new JLabel("Is this correct? Press Yes to save and No to return to"+ title);
//change to +class?
		
		JButton yesButton = new JButton("yes");
		JButton noButton = new JButton("no");
		popup.setLayout(layout);
		displayInfo2Confirm.setLayout(layout);
		popup.add(confirmPrompt);
		popup.add(displayInfo2Confirm);

		ActionListener addButtonListeneryes = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveFile.add(newE);
				confirmationyes(saveFile);
				Utility.clearFields(parent);
				
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

	public void confirmationyes(SaveFile<e> saveFile2) {

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

	public ArrayList<e> getSaveFile() {
		return saveFile;
	}

	public void setSaveFile(ArrayList<e> saveFile) {
		this.saveFile = saveFile;
	}

	public e getNewE() {
		return newE;
	}

	public void setNewE(e newE) {
		this.newE = newE;
	}

}
