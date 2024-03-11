package assignment04;

public class Result {
	private int bstNumOfComparisons;
	private int listNumOfComparisons;
	
	public Result() {}
	
	public Result(int bstNumOfComparisons, int listNumOfComparisons) {
		this.bstNumOfComparisons = bstNumOfComparisons;
		this.listNumOfComparisons= listNumOfComparisons;
	}
	
	public int getBstNumOfComparisons() {
		return this.bstNumOfComparisons;
	}
	
	public void setBstNumOfComparisons(int val) {
		this.bstNumOfComparisons = val;
	}
	
	public int getListNumOfComparisons() {
		return this.listNumOfComparisons;
	}
	
	public void setListNumOfComparisons(int val) {
		this.listNumOfComparisons = val;
	}
}
