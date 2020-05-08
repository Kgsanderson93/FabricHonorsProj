import java.io.IOException;

/**
 * main method starts app and initializes saves holds save locations and
 * initializes first window
 * 
 * @author Kayla
 *
 */
public class StartApp {

	public static final String PATTERN_SAVE = "pattern_inventory.sav";
	public static final String FABRIC_SAVE = "fabric_inventory.sav";

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		SaveFile<Pattern> patternSave = new SaveFile<>();
		SaveFile<Fabric> fabricSave = new SaveFile<>();
		fabricSave.callInventory(FABRIC_SAVE);
		patternSave.callInventory(PATTERN_SAVE);

		@SuppressWarnings("unused")
		FirstWindow fw = new FirstWindow(fabricSave, patternSave);
	}
}