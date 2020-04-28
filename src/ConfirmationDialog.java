// A class to implement a popup confirmation to save the constructed fabric

//needs to link to the action of a button being pressed
//should spit back all user entered texts 
//should show 2 buttons confirm/dump
//confirm should allow the original button being pressed to continue
// dump should reset the window the original button came from 


import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

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
		popup.setSize(400, 250);
		popup.setLocationRelativeTo(null);
		popup.setVisible(true);
		LayoutManager layout = new BorderLayout();
		
		JPanel button= new JPanel(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.NORTHWEST;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.weightx = 1;
		constraints.weighty = 0;

		JLabel confirmPrompt = new JLabel("Is this correct? Press Yes to save and No to return to "+ title);

		
		JButton yesButton = new JButton("yes");
		JButton noButton = new JButton("no");
		popup.setLayout(layout);
		popup.add(confirmPrompt, BorderLayout.PAGE_START);
		JScrollPane scrollPane = new JScrollPane(displayInfo2Confirm);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setOpaque(false);
		popup.add(scrollPane,BorderLayout.CENTER);
		
		

		ActionListener addButtonListeneryes = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveFile.add(newE);
				confirmationyes(saveFile);
				Utility.clearFields(parent);
				FabricHitList(saveFile2);
				popup.dispose();
				parent.dispose();
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
		
		popup.add(button, BorderLayout.PAGE_END);
		button.add(yesButton,constraints);
		constraints.gridx++;
		button.add(noButton,constraints);
		
		
		
	}
@SuppressWarnings("unchecked")
public void FabricHitList(SaveFile<F> saveFile2) {
	if(newE instanceof Pattern) {
		Pattern newPattern=(Pattern)newE;
	

		HitList newPatternHits= new HitList(newPattern, (SaveFile<Fabric>) saveFile2);
		@SuppressWarnings("unused")
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
