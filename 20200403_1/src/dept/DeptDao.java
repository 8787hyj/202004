package dept;

import java.util.*;

public interface DeptDao {
	
	public boolean insert(DeptDto dto);
	public boolean update(DeptDto dto);
	public boolean delete(int deptNo);
	public List<DeptDto> select();
	public DeptDto select(int deptNo);
	
}
