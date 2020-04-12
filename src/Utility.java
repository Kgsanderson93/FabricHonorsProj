import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Utility {


public static void clearFields(JPanel panel) {
	
	
	 for(Component c : panel.getComponents()){ {
		if(c instanceof JTextField) {
			JTextField item=(JTextField)c;
			item.setText("");				
		}
		else if(c instanceof JComboBox) {
			@SuppressWarnings("rawtypes")
			JComboBox box= (JComboBox)c;
			box.setSelectedIndex(0);				
		}
		else if(c instanceof JRadioButton) {
			JRadioButton button= (JRadioButton) c;
			button.setSelected(false);
		}
		else if(c instanceof JCheckBox) {
			JCheckBox box2= (JCheckBox)c;
			box2.setSelected(false);
		}
	}
}
}
}