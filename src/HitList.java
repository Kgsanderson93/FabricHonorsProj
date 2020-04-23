import java.util.ArrayList;

//takes parameters from new Pattern and creates a copy of current fabric inventory searches and pops invalid entries according to patterns parameters. 
public class HitList<f> {
	public static final String FABRIC_SAVE = "fabric_inventory.sav";
	private Pattern newPattern;
	private ArrayList<Fabric> fabricList;

	private String patternName;
	// search against!
	private String patternType;
	// do not search against
	private String patternWhoFor;
	// not search against?
	private boolean splitYardage;
	// not explicitly searched against (yardage converted to main fabric if split is
	// false)
	private double yardage = 0;
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
	private String Fabricname;
	private double fabricYardage = 0;
	private int fabricStretch = 0;
	private String fabricUses;
	private String fabricBase;

	private final String[] patternTypes = { "Shirt", "Dress", "Pants", "Swim", "Lingerie", "Pjs", "Bags", "Toys",
	"Romper" };
	private final String[] fabricBaseLabels = { "Double Brushed Poly", "Cotton Lycra", "French Terry", "Rayon",
	"Denim" };
	private final String[] patternWho = { "Men", "Women", "Children", "Baby", "Other" };

	@SuppressWarnings("unchecked")
	public HitList(Pattern newPattern2, SaveFile<f> saveFile2) {
		// set up and pull new patterns reqs
		this.newPattern = newPattern2;
		fabricList = (ArrayList<Fabric>) saveFile2.getInventory();
		patternName = newPattern.getName();
		patternType = newPattern.getType();
		patternWhoFor = newPattern.getWhoFor();
		splitYardage = newPattern.isSplityardage();
		yardage = newPattern.getYardage();
		minStretch = newPattern.getMinStretch();
		maxStretch = newPattern.getMaxStretch();
		patternBase = newPattern.getBase();
		contrastFabric = newPattern.getContrastFabric();
		mainFabric = newPattern.getMainFabric();
		bandFabric = newPattern.getBandFabric();

		// copy fabric array so pops arent hitting main databank
		ArrayList<Fabric> newListMain = copyArray(fabricList);

		// starta poppin
		popStretch(newListMain);
		popBase(newListMain);
		popType(newListMain);

		// copy remaining list for any yardage reqs above 0
		if (contrastFabric > 0) {
			ArrayList<Fabric> newListContrast = copyArray(newListMain);
			popYardage(newListContrast, contrastFabric);
		}
		if (bandFabric > 0) {
			ArrayList<Fabric> newListBand = copyArray(newListMain);
			popYardage(newListBand, bandFabric);
		}
		popYardage(newListMain, mainFabric);

	}

	private void popYardage(ArrayList<Fabric> newList, double yardage) {
		Fabric temp;
		for (int i = 0; i < newList.size(); i++) {
			temp = newList.get(i);
			fabricYardage = temp.getYardage();
			if (fabricYardage < yardage) {
				newList.remove(i);
			}
		}
	}

	private void popBase(ArrayList<Fabric> newList) {
		Fabric temp;
		for (int i = 0; i < newList.size(); i++) {
			temp = newList.get(i);
			fabricBase = temp.getBase();
			if (!patternBase.contains(fabricBase)) {
				newList.remove(i);
			}
		}
	}

	private void popStretch(ArrayList<Fabric> newList) {
		Fabric temp;
		for (int i = 0; i < newList.size(); i++) {
			temp = newList.get(i);
			fabricStretch = temp.getStretch();
			if (fabricStretch > maxStretch || fabricStretch < minStretch) {
				newList.remove(i);
			}
		}
	}

	private void popType(ArrayList<Fabric> newList) {
		Fabric temp;
		for (int i = 0; i < newList.size(); i++) {
			temp = newList.get(i);
			fabricUses = temp.getUses();
			if (!patternType.contains(fabricUses)) {
				newList.remove(i);
			}
		}
	}

	public ArrayList<Fabric> copyArray(ArrayList<Fabric> fabricList) {
		ArrayList<Fabric> newList = new ArrayList<>();
		for (int i = 0; i < fabricList.size(); i++) {
			newList.add(i, fabricList.get(i));
		}
		return newList;
	}

}
