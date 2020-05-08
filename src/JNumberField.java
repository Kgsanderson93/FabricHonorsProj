import javax.swing.JTextField;

/**
 * class that extends jTextField to preset text to 0 and to make it easier to
 * avoid number format exceptions by reducing the possible number of null
 * strings used primarily in pattern constructor, fabric constructor should be
 * updated to use as well.
 * 
 * @author Kayla
 *
 */
public class JNumberField extends JTextField {
	/**
	 * 
	 */
	private static final long serialVersionUID = 342897165189097433L;

	public JNumberField() {
		this.setText("0");
	}
}
