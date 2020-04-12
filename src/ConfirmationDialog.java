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

public class ConfirmationDialog {
	private FabricConstructor parent;
	private ArrayList<Fabric> fabricList;
	private Fabric newFabric;

	public ConfirmationDialog(FabricConstructor parent, ArrayList<Fabric> fabricList, JPanel displayInfo2Confirm,Fabric newFabric) {
		this.newFabric= newFabric;
		this.parent = parent;
		this.fabricList = fabricList;
		JFrame popup = new JFrame("is this correct?");
		popup.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		popup.setSize(560, 200);
		popup.setLocationRelativeTo(null);
		popup.setVisible(true);

		LayoutManager layout = new FlowLayout();

		JLabel confirmPrompt = new JLabel("Is this correct? Press Yes to save and No to return to Add-a-Fabric");
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
				fabricList.add(newFabric);
				confirmationyes(fabricList);
				Utility.clearFields(displayInfo2Confirm);
				
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

	public void confirmationyes(ArrayList<Fabric> fabricList) {
		
		
		
		try {
			parent.writeFabricList(fabricList);
		} catch (IOException e) {
			// Also notify the user that we couldn't save the file for some reason.
			e.printStackTrace();
		}

	}

	

	}

