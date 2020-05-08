import java.util.ArrayList;
import java.util.Iterator;

/**
 * takes parameters from new Pattern and creates a copy of current fabric
 * inventory searches and pops invalid entries according to patterns parameters.
 * 
 * @author Kayla
 *
 */
public class HitList {
	public static final String FABRIC_SAVE = "fabric_inventory.sav";
	private Pattern newPattern;
	private ArrayList<Fabric> fabricList;
	private String patternName;
	private String patternType;
	private int minStretch = 0;
	private int maxStretch = 200;
	private String patternBase;
	private double contrastFabric = 0;
	private double mainFabric = 0;
	private double bandFabric = 0;
	private double fabricYardage = 0;
	private int fabricStretch = 0;
	private String fabricUses;
	private String fabricBase;
	private ArrayList<Fabric> newListContrast;
	private ArrayList<Fabric> newListBand;
	private ArrayList<Fabric> newListMain;
	private ArrayList<Fabric> copyList;
	private ArrayList<Fabric> listToEdit;
	private boolean emptyMain;
	private boolean emptyContrast;
	private boolean emptyBand;

	/**
	 * constructor to make an instance of hitlist
	 * 
	 * @param newPattern2
	 * @param saveFile2
	 */
	public HitList(Pattern newPattern2, SaveFile<Fabric> saveFile2) {
		// set up and pull new patterns reqs
		this.newPattern = newPattern2;
		fabricList = saveFile2.getInventory();
		setPatternName(newPattern.getName());
		patternType = newPattern.getType();
		minStretch = newPattern.getMinStretch();
		maxStretch = newPattern.getMaxStretch();
		patternBase = newPattern.getBase();
		contrastFabric = newPattern.getContrastFabric();
		mainFabric = newPattern.getMainFabric();
		bandFabric = newPattern.getBandFabric();

		// copy fabric array so pops arent hitting main databank
		newListMain = copyArray(fabricList);

		// starta poppin
		popStretch(newListMain);
		popBase(newListMain);
		popType(newListMain);

		// copy remaining list for any yardage reqs above 0
		newListContrast = new ArrayList<Fabric>();
		newListBand = new ArrayList<Fabric>();
		copyList = copyArray(newListMain);// pristine copy for reset used nowhere but reset
		listToEdit = copyArray(newListMain);// remove yardage during display from here

		if (contrastFabric > 0) {
			popContrast();
		}
		if (bandFabric > 0) {
			popBand();

		}
		if (mainFabric > 0) {
			popMain();
		}

	}

	/**
	 * takes a an array list and removes fabrics that dont match the specified
	 * yardage
	 * 
	 * @param newList
	 * @param yardage
	 */
	public void popYardage(ArrayList<Fabric> newList, double yardage) {
		Fabric temp;
		for (Iterator<Fabric> iterator = newList.iterator(); iterator.hasNext();) {
			temp = iterator.next();
			fabricYardage = temp.getYardage();
			if (fabricYardage < yardage) {
				iterator.remove();
				;
			}
		}
	}

	/**
	 * spcifies which array list should be sent to pop yardage and checks if empty
	 * to trigger error message
	 */
	public void popMain() {
		newListMain = copyArray(listToEdit);// copy array so that method can be used outside of HitList with edits made
											// to listToEdit
		popYardage(newListMain, mainFabric);
		emptyMain = newListMain.isEmpty();
	}

	/**
	 * Same as above for band
	 */
	public void popBand() {
		newListBand = copyArray(listToEdit);
		popYardage(newListBand, bandFabric);
		emptyBand = newListBand.isEmpty();
	}

	/**
	 * same as above for contrast
	 */
	public void popContrast() {
		newListContrast = copyArray(listToEdit);
		popYardage(newListContrast, contrastFabric);
		emptyContrast = newListContrast.isEmpty();
	}

	/**
	 * takes a list and removes elements whose base do not match the requirements
	 * 
	 * @param newList
	 */
	private void popBase(ArrayList<Fabric> newList) {
		Fabric temp;
		for (Iterator<Fabric> iterator = newList.iterator(); iterator.hasNext();) {
			temp = iterator.next();
			fabricBase = temp.getBase();
			if (!patternBase.contains(fabricBase)) {
				iterator.remove();
			}
		}
	}

	/**
	 * takes a list and removes elements whose stretch is not with the specified
	 * allowance
	 * 
	 * @param newList
	 */
	private void popStretch(ArrayList<Fabric> newList) {
		Fabric temp;
		for (Iterator<Fabric> iterator = newList.iterator(); iterator.hasNext();) {
			temp = iterator.next();
			fabricStretch = temp.getStretch();
			if (fabricStretch > maxStretch || fabricStretch < minStretch) {
				iterator.remove();
			}
		}
	}

	/**
	 * takes a list and removes elements whose uses dont meet the specification
	 * 
	 * @param newList
	 */
	private void popType(ArrayList<Fabric> newList) {
		Fabric temp;
		for (Iterator<Fabric> iterator = newList.iterator(); iterator.hasNext();) {
			temp = iterator.next();
			fabricUses = temp.getUses();
			if (!fabricUses.contains(patternType)) {
				iterator.remove();
			}
		}
	}

	/**
	 * copies an array for use in other methods
	 * 
	 * @param fabricList
	 * @return
	 */
	public ArrayList<Fabric> copyArray(ArrayList<Fabric> fabricList) {
		ArrayList<Fabric> newList = new ArrayList<>();
		newList.addAll(fabricList);

		return newList;
	}

	/**
	 * 
	 * @return
	 */
	public String getPatternName() {
		return patternName;
	}

	/**
	 * 
	 * @param patternName
	 */
	public void setPatternName(String patternName) {
		this.patternName = patternName;
	}

	/**
	 * 
	 * @return
	 */
	public ArrayList<Fabric> getNewListContrast() {
		return newListContrast;
	}

	/**
	 * 
	 * @return
	 */
	public ArrayList<Fabric> getNewListBand() {
		return newListBand;
	}

	/**
	 * 
	 * @return
	 */
	public ArrayList<Fabric> getNewListMain() {
		return newListMain;
	}

	/**
	 * 
	 * @return
	 */
	public ArrayList<Fabric> getCopyList() {
		return copyList;
	}

	/**
	 * 
	 * @param copyList
	 */
	public void setCopyList(ArrayList<Fabric> copyList) {
		this.copyList = copyList;
	}

	/**
	 * 
	 * @return
	 */
	public ArrayList<Fabric> getListToEdit() {
		return listToEdit;
	}

	/**
	 * 
	 * @param listToEdit
	 */
	public void setListToEdit(ArrayList<Fabric> listToEdit) {
		this.listToEdit = listToEdit;
	}

	/**
	 * 
	 * @return
	 */
	public double getContrastFabric() {
		return contrastFabric;
	}

	/**
	 * 
	 * @param contrastFabric
	 */
	public void setContrastFabric(double contrastFabric) {
		this.contrastFabric = contrastFabric;
	}

	/**
	 * @return
	 */
	public double getMainFabric() {
		return mainFabric;
	}

	/**
	 * 
	 * @param mainFabric
	 */
	public void setMainFabric(double mainFabric) {
		this.mainFabric = mainFabric;
	}

	/**
	 * 
	 * @return
	 */
	public double getBandFabric() {
		return bandFabric;
	}

	/**
	 * 
	 * @param bandFabric
	 */
	public void setBandFabric(double bandFabric) {
		this.bandFabric = bandFabric;
	}

	/**
	 * 
	 * @return
	 */
	public boolean getEmptyMain() {
		return emptyMain;
	}

	/**
	 * 
	 * @return
	 */
	public boolean getEmptyContrast() {
		return emptyContrast;
	}

	/**
	 * 
	 * @return
	 */
	public boolean getEmptyBand() {
		return emptyBand;
	}

	/**
	 * 
	 */
	public void resetListToEdit() {
		listToEdit = copyArray(copyList);
	}

}
