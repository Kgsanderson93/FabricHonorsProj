import java.awt.BorderLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class InvalidDialog {
	public InvalidDialog() {
	JFrame popup = new JFrame("is this correct?");
	popup.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	popup.setSize(560, 200);
	popup.setLocationRelativeTo(null);
	popup.setVisible(true);
	@SuppressWarnings("unused")
	LayoutManager layout = new BorderLayout();
	
	JLabel warning= new JLabel("Both a name and yardage amount must be specified. Please check input and try again.");
	popup.add(warning);
	JButton ok= new JButton("ok");
	ActionListener okButtonListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			popup.dispose();
		}
	};
	ok.addActionListener(okButtonListener);
	popup.add(ok);
	
	}
}
