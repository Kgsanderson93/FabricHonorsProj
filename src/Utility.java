import java.awt.Component;
import java.awt.Container;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 * a utility class that holds methods that are not class specific and are
 * broadly used.
 * 
 * @author Kayla
 *
 */
public class Utility {

	public static void clearFields(Container container) {

		for (Component c : container.getComponents()) {
			if (c instanceof Container) {
				Container cont = (Container) c;
				clearFields(cont);
			}
			if (c instanceof JNumberField) {
				JNumberField item = (JNumberField) c;
				item.setText("0");
			} else if (c instanceof JTextField) {
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
		String str = "";
		for (int i = 0; i < array.length; i++)
			str += array[i];
		return str;

	}

	public static boolean checkIfValid(String string) {
		boolean isValid = true;
		if (string.equals(null)) {
			isValid = false;
		}
		return isValid;

	}

	public static String getSelectedBoxes(JCheckBox[] boxes) {
		String selected = "";
		for (JCheckBox box : boxes)
			if (box.isSelected())
				selected = selected + " " + (box.getText());
		return selected;
	}

	public static Fabric searchName(String name, ArrayList<Fabric> fabricList) {
		// Wes said something about Streams
		for (Fabric ele : fabricList) {
			if (ele.getName().equalsIgnoreCase(name)) {
				return ele;
			}
		}
		return null;
	}
}