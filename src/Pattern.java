
//defines a pattern and its variables/constructors
import java.io.Serializable;

public class Pattern implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8973096340297472521L;

	private String name;
	private String patternType;
	private String whoFor;
	private boolean splityardage;
	private double yardage = 0;
	private int minStretch = 0;
	private int maxStretch = 200;
	private String base;
	private double contrastFabric = 0;
	private double mainFabric = 0;
	private double bandFabric = 0;
	private final String[] patternTypes = { "Shirt", "Dress", "Pants", "Swim", "Lingerie", "Pjs", "Bags", "Toys", "Romper" };
	private final String[] fabricBaseLabels = { "Double Brushed Poly", "Cotton Lycra", "French Terry", "Rayon", "Denim" };
	private final String[] patternWho = { "Men", "Women", "Children", "Baby", "Other" };

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return patternType;
	}

	public void setType(String type) {
		this.patternType = type;
	}

	public boolean isSplityardage() {
		return splityardage;
	}

	public void setSplityardage(boolean splityardage) {
		this.splityardage = splityardage;
	}

	public double getYardage() {
		return yardage;
	}

	public void setYardage(int yardage) {
		this.yardage = yardage;
	}

	public int getStretch() {
		return minStretch;
	}

	public void setStretch(int stretch) {
		this.minStretch = stretch;
	}

	public double getContrastFabric() {
		return contrastFabric;
	}

	public void setContrastFabric(int contrastFabric) {
		this.contrastFabric = contrastFabric;
	}

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}

	public double getMainFabric() {
		return mainFabric;
	}

	public void setMainFabric(int mainFabric) {
		this.mainFabric = mainFabric;
	}

	public double getBandFabric() {
		return bandFabric;
	}

	public void setBandFabric(int bandFabric) {
		this.bandFabric = bandFabric;
	}

	public int getMaxStretch() {
		return maxStretch;
	}

	public void setMaxStretch(int maxStretch) {
		this.maxStretch = maxStretch;
	}

	public String getWhoFor() {
		return whoFor;
	}

	public void setWhoFor(String whoFor) {
		this.whoFor = whoFor;
	}

	@Override
	public String toString() {
		return "Pattern {\n\t name=" + name + "\n\tbase=" + base + "\n\t yardage=" + yardage + "\n\t Min stretch="
				+ minStretch + "\n\t Max stretch=" + maxStretch + "\n\t Who it's for=" + whoFor + "\n\t Pattern Type="
				+ patternType + "\n\t Main Fabric=" + mainFabric + "\n\t contrast Fabric=" + contrastFabric
				+ "\n\t Band Fabric=" + bandFabric + "\n}";
	}

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

	public Pattern(String name2, String patternTypesSelected, String whofor, double yardage2, String base2,
			int minStretch, int maxStretch, int mainFabric2, int contrastFabric2, int bandFabric2) {
		this.name = name2;
		this.patternType = patternTypesSelected;
		this.whoFor = whofor;
		this.yardage = yardage2;
		this.base = base2;
		this.minStretch = minStretch;
		this.maxStretch = maxStretch;
		this.mainFabric = mainFabric2;
		this.contrastFabric=contrastFabric2;
		this.bandFabric=bandFabric2;
		// TODO Auto-generated constructor stub
	}

}
