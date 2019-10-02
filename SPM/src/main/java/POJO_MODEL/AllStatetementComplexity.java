package POJO_MODEL;

public class AllStatetementComplexity {

	
	private int lineNumber;
	private int Cs;
	private int Ctc;
	private int Cnc;
	private int Ci;
	private int Cr;
	
	
	
	public AllStatetementComplexity(int lineNumber, int cs, int ctc, int cnc, int ci, int cr) {
		super();
		this.lineNumber = lineNumber;
		Cs = cs;
		Ctc = ctc;
		Cnc = cnc;
		Ci = ci;
		Cr = cr;
	}
	
	public int getLineNumber() {
		return lineNumber;
	}
	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}
	public int getCs() {
		return Cs;
	}
	public void setCs(int cs) {
		Cs = cs;
	}
	public int getCtc() {
		return Ctc;
	}
	public void setCtc(int ctc) {
		Ctc = ctc;
	}
	public int getCnc() {
		return Cnc;
	}
	public void setCnc(int cnc) {
		Cnc = cnc;
	}
	public int getCi() {
		return Ci;
	}
	public void setCi(int ci) {
		Ci = ci;
	}
	public int getCr() {
		return Cr;
	}
	public void setCr(int cr) {
		Cr = cr;
	}
	
	
	
}
