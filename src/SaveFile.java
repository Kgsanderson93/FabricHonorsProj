import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class SaveFile<E> {
	
	private ArrayList<E> currentList = new ArrayList<>();
	
	public SaveFile() {
		
	}
	
	public SaveFile(ArrayList<E> elements) {
		this.currentList.addAll(elements);
	}
	
	public void add(E element) {
		currentList.add(element);
	}
	
	public ArrayList<E> callInventory(String fileLocation) throws IOException, ClassNotFoundException{
		ArrayList<E> patternList;
		ObjectInputStream ois = null;
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(fileLocation);
			ois = new ObjectInputStream(fis);
			patternList = (ArrayList<E>) ois.readObject();
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
	
	public void saveInventory(String fileLocation) throws IOException {
		FileOutputStream fos = new FileOutputStream(new File(fileLocation));
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(currentList);
        oos.close();
	}

}
