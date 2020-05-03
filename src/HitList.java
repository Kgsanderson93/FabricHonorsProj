import java.util.ArrayList;
import java.util.Iterator;

//takes parameters from new Pattern and creates a copy of current fabric inventory searches and pops invalid entries according to patterns parameters. 
public class HitList {
	public static final String FABRIC_SAVE = "fabric_inventory.sav";
	private Pattern newPattern;
	private ArrayList<Fabric> fabricList;

	private String patternName;
	// search against!
	private String patternType;
	// do not search against
	// not search against?
	// not explicitly searched against (yardage converted to main fabric if split is
	// false)
	// search against min and max at same time!
	private int minStretch = 0;
	private int maxStretch = 200;
	// search against!
	private String patternBase;
	// (main contrast and yardage may all be searched against seperately)
	// holy shit that raises so many issues....fuuccckkk.
	private double contrastFabric = 0;

	private double mainFabric = 0;
	private double bandFabric = 0;

	// may not need these?
	private double fabricYardage = 0;
	private int fabricStretch = 0;
	private String fabricUses;
	private String fabricBase;

	// hit list instance lists
	private ArrayList<Fabric> newListContrast;
	private ArrayList<Fabric> newListBand;
	private ArrayList<Fabric> newListMain;
	private ArrayList<Fabric> copyList;
	private ArrayList<Fabric> listToEdit;

	// errors for if after searching a list is empty
	private boolean emptyMain;
	private boolean emptyContrast;
	private boolean emptyBand;

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
		copyList=copyArray(newListMain);//pristine copy for reset used nowhere but reset
		listToEdit=copyArray(newListMain);//remove yardage during display from here 
		

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
	public void popMain() {
		newListMain=copyArray(listToEdit);//copy array so that method can be used outside of HitList with edits made to listToEdit
		popYardage(newListMain, mainFabric);
		emptyMain = newListMain.isEmpty();
	}
	public void popBand() {
		newListBand = copyArray(listToEdit);
		popYardage(newListBand, bandFabric);
		emptyBand = newListBand.isEmpty();
	}
	public void popContrast() {
		newListContrast = copyArray(listToEdit);
		popYardage(newListContrast, contrastFabric);
		emptyContrast = newListContrast.isEmpty();
	}
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

	public ArrayList<Fabric> copyArray(ArrayList<Fabric> fabricList) {
		ArrayList<Fabric> newList = new ArrayList<>();
		newList.addAll(fabricList);

		return newList;
	}

	public String getPatternName() {
		return patternName;
	}

	public void setPatternName(String patternName) {
		this.patternName = patternName;
	}

	public ArrayList<Fabric> getNewListContrast() {
		return newListContrast;
	}

	public ArrayList<Fabric> getNewListBand() {
		return newListBand;
	}

	public ArrayList<Fabric> getNewListMain() {
		return newListMain;
	}

	public ArrayList<Fabric> getCopyList() {
		return copyList;
	}

	public void setCopyList(ArrayList<Fabric> copyList) {
		this.copyList = copyList;
	}

	public ArrayList<Fabric> getListToEdit() {
		return listToEdit;
	}

	public void setListToEdit(ArrayList<Fabric> listToEdit) {
		this.listToEdit = listToEdit;
	}

	public double getContrastFabric() {
		return contrastFabric;
	}

	public void setContrastFabric(double contrastFabric) {
		this.contrastFabric = contrastFabric;
	}

	public double getMainFabric() {
		return mainFabric;
	}

	public void setMainFabric(double mainFabric) {
		this.mainFabric = mainFabric;
	}

	public double getBandFabric() {
		return bandFabric;
	}

	public void setBandFabric(double bandFabric) {
		this.bandFabric = bandFabric;
	}
	public boolean getEmptyMain() {
		return emptyMain;
	}
	public boolean getEmptyContrast() {
		return emptyContrast;
	}
	public boolean getEmptyBand() {
		return emptyBand;
	}
	public void resetListToEdit() {
		listToEdit=copyArray(copyList);
	}
		
}
