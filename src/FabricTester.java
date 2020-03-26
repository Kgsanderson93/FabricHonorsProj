import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class FabricTester {
	
	private static final String PATTERN_SAVE = "pattern_inventory.sav";
	private static final String FABRIC_SAVE = "fabric_inventory.sav";
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		ArrayList<Fabric> fabricList = initializeFabric();
		ArrayList<Pattern> patternList= initializePattern();

		FirstWindow fw = new FirstWindow(fabricList, patternList);
	}

	private static ArrayList<Fabric> initializeFabric() throws IOException, ClassNotFoundException {
		ArrayList<Fabric> fabricList = callFabricInventory();
		
		return fabricList;
	}	
	private static ArrayList<Pattern> initializePattern() throws IOException, ClassNotFoundException {
		ArrayList<Pattern> patternList = callPatternInventory();
		
		return patternList;
	}	
	private static ArrayList<Pattern> callPatternInventory() throws IOException, ClassNotFoundException{
		ArrayList<Pattern> patternList;
		ObjectInputStream ois = null;
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(PATTERN_SAVE);
			ois = new ObjectInputStream(fis);
			patternList = (ArrayList<Pattern>) ois.readObject();
		} catch (EOFException eof) {
			System.err.println("No Object found in file");
			patternList = new ArrayList<>();
		} catch (FileNotFoundException fnf) {
			System.err.println("Save file doesn't exist: Creating new Inventory.");
			patternList = new ArrayList<>();
		} finally {
			if (fis != null) {
				fis.close();
			}
			if (ois != null) {
				ois.close();
			}
		}
     
		return patternList;
	}

	public static ArrayList<Fabric> callFabricInventory() throws IOException, ClassNotFoundException { 
		ArrayList<Fabric> fabricList;
		ObjectInputStream ois = null;
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(FABRIC_SAVE);
			ois = new ObjectInputStream(fis);
			fabricList = (ArrayList<Fabric>) ois.readObject();
		} catch (EOFException eof) {
			System.err.println("No Object found in file");
			fabricList = new ArrayList<>();
		} catch (FileNotFoundException fnf) {
			System.err.println("Save file doesn't exist: Creating new Inventory.");
			fabricList = new ArrayList<>();
		} finally {
			if (fis != null) {
				fis.close();
			}
			if (ois != null) {
				ois.close();
			}
		}
     
		return fabricList;
	}
}