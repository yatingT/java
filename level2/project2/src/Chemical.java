// --== CS400 File Header Information ==--
// Name: Bill Yan
// Email: wyan34@wisc.edu
// Team:  MF
// Role: Back End Developer
// TA:  Harit
// Lecturer: Florian Heimerl
// Notes to Grader: <optional extra notes>
public class Chemical implements Comparable<Chemical>
{
	private String chemicalName;
	private int chemicalQuant;
  
	public Chemical(String chemicalName, int chemicalQuant) 
	{
		this.chemicalName = chemicalName;
		this.chemicalQuant = chemicalQuant;
	}
  
	public String chemicalName() {return this.chemicalName;}
  
	public void setChemicalName(String chemicalName) {this.chemicalName = chemicalName;}
  
	public int chemicalQuant() {return this.chemicalQuant;}
  
	public void setChemicalQuant(int chemicalQuant) {this.chemicalQuant = chemicalQuant;}

	@Override
	public int compareTo(Chemical o) 
	{
		return this.chemicalName.compareTo(o.chemicalName);
	}
	
	@Override
	public String toString()
	{
		return this.chemicalName + ": " + this.chemicalQuant + " grams";
	}
}
