package employee;

import java.util.List;

public interface EmpDao {

	public boolean insertEmp(Employee em);
	public boolean updateEmp(Employee em);
	public boolean deleteEmp(int bunho);
	public List<Employee> selectAll();
	public Employee selectOne(int bunho);
	public List<Employee> selectNameEmp(String name);
	
}
