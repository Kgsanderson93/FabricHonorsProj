//defines a pattern and its variables/constructors
import java.io.Serializable;

public class Pattern  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8973096340297472521L;


private String name;
private String patternType;
private  boolean splityardage;
private int yardage;
private int stretch = 100;
private String base;
private int contrastFabric;
private int mainFabric;
private int bandFabric;


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
public int getYardage() {
	return yardage;
}
public void setYardage(int yardage) {
	this.yardage = yardage;
}
public int getStretch() {
	return stretch;
}
public void setStretch(int stretch) {
	this.stretch = stretch;
}
public int getContrastFabric() {
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
public int getMainFabric() {
	return mainFabric;
}
public void setMainFabric(int mainFabric) {
	this.mainFabric = mainFabric;
}
public int getBandFabric() {
	return bandFabric;
}
public void setBandFabric(int bandFabric) {
	this.bandFabric = bandFabric;
}
public Pattern() {
	

}
public Pattern(String name2, String patternTypesSelected, String whofor, double yardage2, String base2, int minStretch,
		int maxStretch) {
	// TODO Auto-generated constructor stub
}
public Pattern(String name2, String patternTypesSelected, String whofor, double yardage2, String base2, int minStretch,
		int maxStretch, int mainFabric2, int contrastFabric2, int bandFabric2) {
	// TODO Auto-generated constructor stub
}
}
