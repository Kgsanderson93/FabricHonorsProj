import java.io.IOException;
import java.util.ArrayList;

public class FabricTester {
	
	private static final String PATTERN_SAVE = "pattern_inventory.sav";
	private static final String FABRIC_SAVE = "fabric_inventory.sav";
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		SaveFile<Pattern> patternSave = new SaveFile<>();
		SaveFile<Fabric> fabricSave = new SaveFile<>();
		ArrayList<Fabric> fabricList = fabricSave.callInventory(FABRIC_SAVE);
		ArrayList<Pattern> patternList= patternSave.callInventory(PATTERN_SAVE);

		FirstWindow fw = new FirstWindow(fabricList, patternList);
	}
}