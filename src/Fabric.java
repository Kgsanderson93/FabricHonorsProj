
/**
 * Defines a Fabric and its variables
 */

import java.io.Serializable;

public class Fabric implements Serializable {

	public static final String[] FABRIC_BASES = { "Double Brushed Poly", "Cotton Lycra", "French Terry", "Rayon",
			"Denim", "Hacci", "Waffle", "Scuba" };

	public final static String[] ALL_USES = { "Shirt", "Pants", "Underwear", "Outerwear", "Dresses" };

	/**
	 * 
	 */
	private static final long serialVersionUID = -6023935205525499701L;

	private String name;

	private String base;

	private double yardage;

	private int stretch = 100;

	private String uses = "";

	/**
	 * constructor creates a fabric
	 * 
	 * @param name
	 * @param base
	 * @param amount
	 */
	public Fabric(String name, String base, double amount) {
		this(name, amount, base, 100, allUsesToString()); // All of them.
	}

	/**
	 * constructor creates a fabric
	 * 
	 * @param name
	 * @param base
	 * @param amount
	 * @param stretch
	 */
	public Fabric(String name, String base, double amount, int stretch) {
		this(name, amount, base, stretch, allUsesToString()); // All of them.
	}

	/**
	 * constructor creates a fabric
	 * 
	 * @param name
	 * @param amount
	 * @param base
	 * @param uses
	 */
	public Fabric(String name, double amount, String base, String uses) {
		this(name, amount, base, 100, uses);
	}

	/**
	 * constructor creates a fabric
	 * 
	 * @param fabricName
	 * @param yardage
	 * @param base
	 * @param stretch
	 * @param uses
	 */
	public Fabric(String fabricName, double yardage, String base, int stretch, String uses) {
		this.name = fabricName;
		this.base = base;
		this.yardage = yardage;
		this.stretch = stretch;
		this.setUses(uses);
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
	public double getYardage() {
		return yardage;
	}

	/**
	 * 
	 * @param yardage
	 */
	public void setYardage(double yardage) {
		this.yardage = yardage;
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
	 * overides toString method to display the fabric in a meaningful way
	 */
	@Override
	public String toString() {
		return "Fabric \n\tname=" + name + "\n\tbase=" + base + "\n\tyardage=" + yardage + "\n\tstretch=" + stretch
				+ "\n\tuses=" + uses + "\n";
	}

	/**
	 * 
	 * @return
	 */

	public int getStretch() {
		return stretch;
	}

	/**
	 * 
	 * @param stretch
	 */
	public void setStretch(int stretch) {
		this.stretch = stretch;
	}

	/**
	 * 
	 * @return
	 */
	public String getUses() {
		return uses;
	}

	/**
	 * 
	 * @param uses
	 */
	public void setUses(String uses) {
		this.uses = uses;
	}

	/**
	 * 
	 * @return
	 */
	private static String allUsesToString() {
		String allUses = "";
		for (String use : ALL_USES)
			allUses = allUses + " " + use;
		return allUses;
	}

}
