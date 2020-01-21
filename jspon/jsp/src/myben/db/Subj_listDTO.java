package myben.db;

public class Subj_listDTO {
	private int idx;
	private String subject;
	private String kind;
	private int width;
	private String m_height;
	private String view;
	private int top_idx;
	private int count;
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public String getM_height() {
		return m_height;
	}
	public void setM_height(String m_height) {
		this.m_height = m_height;
	}
	public String getView() {
		return view;
	}
	public void setView(String view) {
		this.view = view;
	}
	public int getTop_idx() {
		return top_idx;
	}
	public void setTop_idx(int top_idx) {
		this.top_idx = top_idx;
	}
}
