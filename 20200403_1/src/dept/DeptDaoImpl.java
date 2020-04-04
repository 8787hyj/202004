package dept;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import employee.Employee;

public class DeptDaoImpl implements DeptDao {

	private static DeptDaoImpl instance;

	private DeptDaoImpl() {
	}

	public static DeptDaoImpl getInstance() {
		if (instance == null)
			instance = new DeptDaoImpl();
		return instance;
	}

	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private boolean connect() {
		boolean result = false; // 불리언 타입으로 리절트를 펄스값으로 주고

		try {
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "scott";
			String password = "tiger";
			con = DriverManager.getConnection(url, user, password); // 커넥션 콘을 연결한다
			result = true; // 리절트는 트루값을 준다
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result; // 반환은 리절트로 한다
	}

	private void close() {
		try {
			if (rs != null)
				rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 3.사원 등록  
	@Override
	public boolean insert(DeptDto dto) {
		boolean isSuccess = false;
		if (connect()) {
			StringBuffer sql = new StringBuffer();
			sql.append("insert into dept(deptno,dname,loc) values(?,?,?)");
			try {
				pstmt = con.prepareStatement(sql.toString());
				int index = 0;
				pstmt.setInt(++index, dto.getNo());
				pstmt.setString(++index, dto.getName());
				pstmt.setString(++index, dto.getLoc());
				pstmt.executeUpdate();
				isSuccess = true;

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		close();
		return isSuccess;
	}

	// 4.사원정보 수정
	@Override
	public boolean update(DeptDto dto) {
		boolean isSuccess = false;
		if (connect()) {
			StringBuffer sql = new StringBuffer();
			sql.append("update dept set dname=?, loc=? where deptno=? ");
			try {
				pstmt = con.prepareStatement(sql.toString());
				int index = 0;
				pstmt.setString(++index, dto.getName());
				pstmt.setString(++index, dto.getLoc());
				pstmt.setInt(++index, dto.getNo());
				pstmt.executeUpdate();
				isSuccess = true;

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		close();
		return isSuccess;
	}

	// 5.삭제 
	@Override
	public boolean delete(int deptNo) {
		boolean isSuccess = false;
		if (connect()) {
			StringBuffer sql = new StringBuffer();
			sql.append("DELETE FROM dept WHERE deptno = ?");
			try {
				pstmt = con.prepareStatement(sql.toString());
				int index = 0;
				pstmt.setInt(++index, deptNo);
				pstmt.executeUpdate();
				isSuccess = true;

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		close();
		return isSuccess;
	}

	//1.전체 조회
	@Override
	public List<DeptDto> select() {
		List<DeptDto> list = new ArrayList<DeptDto>();
		StringBuffer sql = new StringBuffer();

		if (connect()) {
			sql.append("SELECT deptno, dname, loc ");
			sql.append("FROM dept ");
			sql.append("ORDER BY deptno");
			try {
				pstmt = con.prepareStatement(sql.toString());
				rs = pstmt.executeQuery();
				while (rs.next()) {
					int index = 1;
					int deptno = rs.getInt(index++);
					String dname = rs.getString(index++);
					String loc = rs.getString(index++);
					list.add(new DeptDto(deptno, dname, loc));
					// list.add(em);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		close();
		return list;
	}

	// 2.번호로 조회
	@Override
	public DeptDto select(int deptNo) {
		DeptDto dto = null;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT deptno, dname, loc ");
		sql.append("FROM dept ");
		sql.append("WHERE deptno = ?");

		try {
			pstmt = con.prepareStatement(sql.toString());
			int index = 0;
			pstmt.setInt(++index, deptNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int deptno = rs.getInt(index++);
				String dname = rs.getString(index++);
				String loc = rs.getString(index++);
				dto = new DeptDto(deptno, dname, loc);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		close();
		return dto;
	}

}
