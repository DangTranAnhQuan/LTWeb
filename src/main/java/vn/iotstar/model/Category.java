package vn.iotstar.model;

public class Category {
	private int cateid;
	private String catename;
	private String icon;
	private int userId;

	public Category() {
	}

	public Category(int cateid, String catename, String icon, int userId) {
		this.cateid = cateid;
		this.catename = catename;
		this.icon = icon;
		this.userId = userId;
	}

	public int getCateid() {
		return cateid;
	}

	public void setCateid(int cateid) {
		this.cateid = cateid;
	}

	public String getCatename() {
		return catename;
	}

	public void setCatename(String catename) {
		this.catename = catename;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
}
