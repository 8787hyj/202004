package employee;

import java.sql.*;
import java.util.*;

public class EmpDaoImpl implements EmpDao {

	private static EmpDaoImpl instance;
	
	private EmpDaoImpl() {}
	
	public static EmpDaoImpl getInstance() {
		if(instance==null) 
			instance=new EmpDaoImpl();
		return instance;
	}
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private boolean connect() {
		boolean result = false; //불리언 타입으로 리절트를 펄스값으로 주고
		
		try {
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "scott";
			String password = "tiger";
			conn = DriverManager.getConnection(url, user, password); //커넥션 콘을 연결한다
			System.out.println("DB 연결 성공");
			result = true; // 리절트는 트루값을 준다
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result; // 반환은 리절트로 한다
	}
	
	private void close() {
		try {
			if(rs!=null) rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean insertEmp(Employee em) {
		boolean result = false;
		if(connect()) {
			String sql = "insert into employee values(?,?,?,?,?)";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, em.getName());
				pstmt.setDate(2, em.getBirth());
				pstmt.setString(3, em.getAddr());
				pstmt.setString(4, em.getTel());
				pstmt.setString(5, em.getEmail());
				
				int res = pstmt.executeUpdate();
				if(res > 0) result = true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		close();
		return result;
	}

	@Override
	public boolean updateEmp(Employee em) {
		boolean result = false;
		if(connect()) {
			String sql = "update employee set name=?,birth=?,addr=?,tel=?,email=? where bunho=?)";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, em.getName());
				pstmt.setDate(2, em.getBirth());
				pstmt.setString(3, em.getAddr());
				pstmt.setString(4, em.getTel());
				pstmt.setString(5, em.getEmail());
				pstmt.setInt(6, em.getBunho());
				
				int res = pstmt.executeUpdate();
				if(res > 0) result = true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		close();
		return result;
	}

	@Override
	public boolean deleteEmp(int bunho) {
		boolean result = false;
		if(connect()) {
			String sql = "delete form employee where bunho=?)";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, bunho);
				
				int res = pstmt.executeUpdate();
				if(res > 0) result = true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		close();
		return result;
	}

	@Override
	public List<Employee> selectAll() {
		List<Employee> empList = new ArrayList<Employee>();
		if(connect()) {
			String sql = "select * from emp";
			try {
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					Employee em = new Employee();
					em.setBunho(rs.getInt("bunho"));
					em.setName(rs.getString("name"));
					em.setBirth(rs.getDate("birth"));
					em.setTel(rs.getString("tel"));
					em.setAddr(rs.getString("addr"));
					em.setEmail(rs.getString("email"));
					empList.add(em);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return empList;
	}

	@Override
	public Employee selectOne(int bunho) {
		Employee em = new Employee();
		if(connect()) {
			String sql = "select * from employee where bunho = ?";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, bunho);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					em.setBunho(rs.getInt("bunho"));
					em.setName(rs.getString("name"));
					em.setBirth(rs.getDate("birth"));
					em.setTel(rs.getString("tel"));
					em.setAddr(rs.getString("addr"));
					em.setEmail(rs.getString("email"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		close();
		return em;
	}

	@Override
	public List<Employee> selectNameEmp(String name) {
		List<Employee> empList = new ArrayList<Employee>();
		if(connect()) {
			StringBuffer delete_by_name = new StringBuffer();
			delete_by_name.append("select * from employee where name like ?");
			try {
				pstmt = conn.prepareStatement(delete_by_name.toString());
				pstmt.setString(1, "%"+name+"%");
				rs = pstmt.executeQuery();
				while(rs.next()) {
					Employee em = new Employee();
					em.setBunho(rs.getInt("bunho"));
					em.setName(rs.getString("name"));
					em.setBirth(rs.getDate("birth"));
					em.setTel(rs.getString("tel"));
					em.setAddr(rs.getString("addr"));
					em.setEmail(rs.getString("email"));
					empList.add(em);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return empList;
	}
}
