package employee;

import java.sql.*;
import java.util.*;

public class EmpDaoImpl_2 implements EmpDao_2 {

	private static EmpDaoImpl_2 instance;
	private EmpDaoImpl_2() {	}
	public static EmpDao_2 getInstance() {
		if (instance == null) {
			instance = new EmpDaoImpl_2();
		}
		return instance;
	}
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private boolean connect() {
		boolean result = false;
		String url = "jdbc:oracle:thin:@localhost:1521:xe"
				+ "characterEncoding=utf-8&serverTimezone=UTC";
		String user = "scott";
		String password = "tiger";
		
		try {
			conn = DriverManager.getConnection(url, user,password);
			result = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	private void close() {
		try {
			if(rs != null) rs.close();
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
		if (connect()) {
			// String sql = "insert into employee values(?,?,?,?,?,?)";
			String sql = "insert into employee(name, birth, addr, tel, email)"
					+ " values(?,?,?,?,?)";

			try {
				pstmt = conn.prepareStatement(sql);
				// pstmt.setInt(1, em.getBunho());
				pstmt.setString(1, em.getName());
				pstmt.setDate(2, em.getBirth());
				pstmt.setString(3, em.getAddr());
				pstmt.setString(4, em.getTel());
				pstmt.setString(5, em.getEmail());

				int res = pstmt.executeUpdate();
				if (res > 0) {
					result = true;
				}
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
		if (connect()) {
			String sql = "update employee set name=?, birth=?, "
					+ "addr=?, tel=?, email=? where bunho = ?";

			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, em.getName());
				pstmt.setDate(2, em.getBirth());
				pstmt.setString(3, em.getAddr());
				pstmt.setString(4, em.getTel());
				pstmt.setString(5, em.getEmail());
				pstmt.setInt(6, em.getBunho());

				int res = pstmt.executeUpdate();
				if (res > 0) {
					result = true;
				}
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
		if (connect()) {
			String sql = "delete from employee where bunho = ?";

			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, bunho);

				int res = pstmt.executeUpdate();
				if (res > 0) {
					result = true;
				}
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
		List<Employee> empList = new ArrayList<>();
		if (connect()) {
			String sql = "select * from employee";
			try {
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				// System.out.println(rs.next());
				while (rs.next()) {
					Employee em = new Employee();
					em.setBunho(rs.getInt("bunho"));
					em.setName(rs.getString("name"));
					em.setBirth(rs.getDate("birth"));
					em.setTel(rs.getString("tel"));
					em.setAddr(rs.getString("addr"));
					em.setEmail(rs.getString("email"));
					// 리스트에 객체 저장
					empList.add(em);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		close();
		return empList;
	}

	@Override
	public Employee selectOne(int bunho) {
		Employee em = new Employee();
		if (connect()) {
			String sql = "select * from employee where bunho = ?";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, bunho);
				rs = pstmt.executeQuery();
				// System.out.println(rs.next());
				while (rs.next()) {
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
		List<Employee> empList = new ArrayList<>();
		
		if (connect()) {
			StringBuffer delete_by_name = new StringBuffer();
			delete_by_name.append("select * from employee "
					+ "where name like ?");
			try {
				pstmt = conn.prepareStatement(
						delete_by_name.toString());
				pstmt.setString(1, "%" + name + "%");
				rs = pstmt.executeQuery();

				while (rs.next()) {
					Employee em = new Employee();
					em.setBunho(rs.getInt("bunho"));
					em.setName(rs.getString("name"));
					em.setBirth(rs.getDate("birth"));
					em.setTel(rs.getString("tel"));
					em.setAddr(rs.getString("addr"));
					em.setEmail(rs.getString("email"));
					// 리스트에 객체 저장
					empList.add(em);
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		close();
		return empList;
	}
}
