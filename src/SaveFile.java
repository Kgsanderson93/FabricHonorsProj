import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * class creates and handles all save file functions as a generic to take both
 * pattern and fabric saves
 * 
 * @author Kayla
 *
 * @param <E>
 */
public class SaveFile<E> {

	private ArrayList<E> currentList = new ArrayList<>();
	private String saveLocation;

	public SaveFile() {

	}

	public SaveFile(ArrayList<E> elements) {
		this.currentList.addAll(elements);
	}

	public void add(E element) {
		currentList.add(element);
	}

	@SuppressWarnings("unchecked")
	public ArrayList<E> callInventory(String fileLocation) throws IOException, ClassNotFoundException {
		ArrayList<E> objectList;
		ObjectInputStream ois = null;
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(fileLocation);
			ois = new ObjectInputStream(fis);
			objectList = (ArrayList<E>) ois.readObject();
		} catch (EOFException eof) {
			System.err.println("No Object found in file");
			objectList = new ArrayList<>();
		} catch (FileNotFoundException fnf) {
			System.err.println("Save file doesn't exist: Creating new Inventory.");
			objectList = new ArrayList<>();
		} finally {
			if (fis != null) {
				fis.close();
			}
			if (ois != null) {
				ois.close();
			}
		}
		this.currentList = objectList;
		setSaveLocation(fileLocation);
		return objectList;
	}

	public void saveInventory(String fileLocation) throws IOException {
		FileOutputStream fos = new FileOutputStream(new File(fileLocation));
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(currentList);
		oos.close();
	}

	public ArrayList<E> getInventory() {
		return currentList;
	}

	public String getSaveLocation() {
		return saveLocation;
	}

	public void setSaveLocation(String saveLocation) {
		this.saveLocation = saveLocation;
	}
}
