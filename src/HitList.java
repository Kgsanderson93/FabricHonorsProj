import java.util.ArrayList;

//takes parameters from new Pattern and creates a copy of current fabric inventory searches and pops invalid entries according to patterns parameters. 
public class HitList<f> {
	public static final String FABRIC_SAVE = "fabric_inventory.sav";
	private Pattern newPattern;
	private ArrayList<Fabric> fabricList;

	private String patternName;
	private String patternType;
	private String patternWhoFor;
	private boolean splitYardage;
	private double yardage = 0;
	private int minStretch = 0;
	private int maxStretch = 200;
	private String patternBase;
	private double contrastFabric = 0;
	private double mainFabric = 0;
	private double bandFabric = 0;
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
		this.newPattern = newPattern2;
		fabricList = (ArrayList<Fabric>) saveFile2.getInventory();
		patternName=newPattern.getName();
		patternType=newPattern.getType();
		patternWhoFor=newPattern.getWhoFor();
		splitYardage=newPattern.isSplityardage();
		yardage = newPattern.getYardage();
		minStretch = newPattern.getMinStretch();
		maxStretch = newPattern.getMaxStretch();
		patternBase=newPattern.getBase();
		contrastFabric = newPattern.getContrastFabric();
		mainFabric = newPattern.getMainFabric();
		bandFabric = newPattern.getBandFabric();
		ArrayList<Fabric> newList=copyArray(fabricList);
		popStretch(newList);
		popBase(newList);
		

	}

	private void popBase(ArrayList<Fabric> newList) {
		Fabric temp;
		for(int i=0; i<newList.size();i++){
			temp=newList.get(i);
			fabricBase=temp.getBase();
			if (patternBase.contains(fabricBase)) {				
			}	
			else {
				newList.remove(i);
			}
		}	
		
	}

	private void popStretch(ArrayList<Fabric> newList) {
		Fabric temp;
		for(int i=0; i<newList.size();i++){
			temp=newList.get(i);
			fabricStretch=temp.getStretch();
			if (fabricStretch > maxStretch || fabricStretch < minStretch) {
				newList.remove(i);
			}						
		}		
	}

	

	public ArrayList<Fabric> copyArray(ArrayList<Fabric> fabricList) {
		ArrayList<Fabric> newList = new ArrayList<>();
		for (int i = 0; i < fabricList.size(); i++) {
			newList.add(i, this.get(i));
		}
		return newList;
	}

	public Fabric get(int i) {
		Fabric e = fabricList.get(i);
		return e;
	}
}
