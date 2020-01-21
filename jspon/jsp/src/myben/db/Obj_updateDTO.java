package myben.db;

public class Obj_updateDTO {
	private int top_idx;
	private int idx;
	private int ch;
	private int count;
	private String column;
	public String getColumn() {
		return column;
	}
	public void setColumn(String column) {
		this.column = column;
	}
	public int getTop_idx() {
		return top_idx;
	}
	public void setTop_idx(int top_idx) {
		this.top_idx = top_idx;
	}
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public int getCh() {
		return ch;
	}
	public void setCh(int ch) {
		this.ch = ch;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}

}
