
/**
 * defines a pattern and its variables/constructors
 */
import java.io.Serializable;

public class Pattern implements Serializable {

	public static final String[] WHO_FOR = { "Men", "Women", "Children", "Baby", "Other" };

	public static final String[] PATTERN_TYPES = { "Shirt", "Dress", "Pants", "Swim", "Lingerie", "Pjs", "Bags", "Toys",
			"Romper" };

	/**
	 * 
	 */
	private static final long serialVersionUID = 8973096340297472521L;

	private String name;
	private String patternType;
	private String whoFor;
	private double yardage = 0;
	private int minStretch = 0;
	private int maxStretch = 200;
	private String base;
	private double contrastFabric = 0;
	private double mainFabric = 0;
	private double bandFabric = 0;

	/**
	 * constructs a no arg pattern (not used)
	 */
	public Pattern() {

	}

	public Pattern(String name2, String patternTypesSelected, String whofor, double yardage2, String base2,
			int minStretch, int maxStretch) {
		this.name = name2;
		this.patternType = patternTypesSelected;
		this.whoFor = whofor;
		this.yardage = yardage2;
		this.base = base2;
		this.minStretch = minStretch;
		this.maxStretch = maxStretch;
		this.mainFabric = yardage2;

	}

	/**
	 * all arguement constructor for pattern
	 * 
	 * @param name2
	 * @param patternTypesSelected
	 * @param whofor
	 * @param yardage2
	 * @param base2
	 * @param minStretch
	 * @param maxStretch
	 * @param mainFabric2
	 * @param contrastFabric2
	 * @param bandFabric2
	 */
	public Pattern(String name2, String patternTypesSelected, String whofor, double yardage2, String base2,
			int minStretch, int maxStretch, double mainFabric2, double contrastFabric2, double bandFabric2) {
		this.name = name2;
		this.patternType = patternTypesSelected;
		this.whoFor = whofor;
		this.yardage = yardage2;
		this.base = base2;
		this.minStretch = minStretch;
		this.maxStretch = maxStretch;
		this.mainFabric = mainFabric2;
		this.contrastFabric = contrastFabric2;
		this.bandFabric = bandFabric2;
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @return
	 */
	public String getType() {
		return patternType;
	}

	/**
	 * 
	 * @param type
	 */
	public void setType(String type) {
		this.patternType = type;
	}

	/**
	 * 
	 * @return
	 */
	public double getYardage() {
		return yardage;
	}

	/**
	 * 
	 * @param yardage
	 */
	public void setYardage(int yardage) {
		this.yardage = yardage;
	}

	/**
	 * 
	 * @return
	 */
	public int getMinStretch() {
		return minStretch;
	}

	/**
	 * 
	 * @param minStretch
	 */
	public void setMinStretch(int minStretch) {
		this.minStretch = minStretch;
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
	public void setContrastFabric(int contrastFabric) {
		this.contrastFabric = contrastFabric;
	}

	/**
	 * 
	 * @return
	 */
	public String getBase() {
		return base;
	}

	/**
	 * 
	 * @param base
	 */
	public void setBase(String base) {
		this.base = base;
	}

	/**
	 * 
	 * @return
	 */
	public double getMainFabric() {
		return mainFabric;
	}

	/**
	 * 
	 * @param mainFabric
	 */
	public void setMainFabric(int mainFabric) {
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
	public void setBandFabric(int bandFabric) {
		this.bandFabric = bandFabric;
	}

	/**
	 * 
	 * @return
	 */
	public int getMaxStretch() {
		return maxStretch;
	}

	/**
	 * 
	 * @param maxStretch
	 */
	public void setMaxStretch(int maxStretch) {
		this.maxStretch = maxStretch;
	}

	/**
	 * 
	 * @return
	 */
	public String getWhoFor() {
		return whoFor;
	}

	/**
	 * 
	 * @param whoFor
	 */
	public void setWhoFor(String whoFor) {
		this.whoFor = whoFor;
	}

	/**
	 * overrides to string method to display patterns in a meaningful way
	 */
	@Override
	public String toString() {
		return "Pattern \n\t name=" + name + "\n\tbase=" + base + "\n\t yardage=" + yardage + "\n\t Min stretch="
				+ minStretch + "\n\t Max stretch=" + maxStretch + "\n\t Who it's for=" + whoFor + "\n\t Pattern Type="
				+ patternType + "\n\t Main Fabric=" + mainFabric + "\n\t contrast Fabric=" + contrastFabric
				+ "\n\t Band Fabric=" + bandFabric + "\n";
	}

}
