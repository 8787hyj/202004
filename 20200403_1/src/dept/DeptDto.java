package dept;

public class DeptDto {
	private int no;
	private String name;
	private String loc;
	
	public DeptDto() {}
	public DeptDto(int no, String name, String loc) {
		this.no = no;
		this.name = name;
		this.loc = loc;
	}
	public int getNo() {
		return no;
	}
	public String getName() {
		return name;
	}
	public String getLoc() {
		return loc;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}
	@Override
	public String toString() {
		return "DeptDto [no=" + no + ", name=" + name + ", loc=" + loc + "]";
	}
}
