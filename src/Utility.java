import java.awt.Component;
import java.awt.Container;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Utility {

	public static void clearFields(Container container) {

		for (Component c : container.getComponents()) {
			if (c instanceof Container) {
				Container cont = (Container) c;
				clearFields(cont);
			}
			if (c instanceof JTextField) {
				JTextField item = (JTextField) c;
				item.setText("");
			} else if (c instanceof JComboBox) {
				@SuppressWarnings("rawtypes")
				JComboBox box = (JComboBox) c;
				box.setSelectedIndex(0);
			} else if (c instanceof JRadioButton) {
				JRadioButton button = (JRadioButton) c;
				button.setSelected(false);
			} else if (c instanceof JCheckBox) {
				JCheckBox box2 = (JCheckBox) c;
				box2.setSelected(false);
			}

		}

	}
	public static String arrayToString(String[] array) {
		String str="";
		for(int i=0; i<array.length;i++)
			str+=array[i];
		return str;
		
	}
	public static boolean checkIfValid(String string) {
		boolean isValid=true;
		if (string.equals(null)) {
			isValid=false;
		}
		return isValid;
		
	}
}