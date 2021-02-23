// A class to store output
class COMP108A1Output {
	public int hitCount;
	public int missCount;
	public String hitPattern;
	
	public COMP108A1Output() {
		hitCount = 0;
		missCount = 0;
		hitPattern = "";
	}

	public void print() {
		System.out.println(hitPattern);
		System.out.println(hitCount + " h " + missCount + " m");
	}	
}
