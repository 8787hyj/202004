package employee;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Emp_Main_DB_2 {
	public static void main(String[] args) {
		EmpDao_2 dao = EmpDaoImpl_2.getInstance();
		Scanner sc = new Scanner(System.in);
		
		System.out.println("원하는 서비스를 선택하시오");
		System.out.println("-----------------------------------------------");
		System.out.println("1.전체조회 2.번호조회 3.사원등록 4.사원정보수정 5.삭제 6.이름조회");
		System.out.println("-----------------------------------------------");
		int select = sc.nextInt();
		if(select==1) {
			System.out.println("================selectAll======================");
			List<Employee> empList = dao.selectAll();
			for(Employee e:empList) System.out.println(e);
		} else if(select==2) {
			System.out.println("================selectOne======================");
			Employee em = dao.selectOne(4);
			System.out.println(em);
		} else if(select==3) {
			System.out.println("================insertEmp======================");
			Employee em = new Employee(0, "우럭회", Date.valueOf(LocalDate.of(2007, 03, 11)), "010-0000-0000", "부천", "aa@aa.com");
			Employee e1 = new Employee();
			e1.setName("농어회");
			e1.setBirth(Date.valueOf(LocalDate.of(2005, 01, 21)));
			e1.setTel("111-1111-1111");
			e1.setAddr("paris");
			e1.setEmail("bb@bb.com");
			
			if(dao.insertEmp(e1)) System.out.println("저장 완료");
			else System.out.println("저장 실패");
		} else if(select==4) {
			System.out.println("================updateEmp======================");
			Employee em = new Employee(2, "농어회", Date.valueOf(LocalDate.of(2000, 07, 15)), "222-2222-2222", "서울", "bb@bb.com");
			if(dao.updateEmp(em)) System.out.println("변경 완료");
			else System.out.println("변경 실패");
		} else if(select==5) {
			System.out.println("================deleteEmp======================");
			if(dao.deleteEmp(1)) System.out.println("삭제 완료");
			else System.out.println("삭제 실패");
		} else if(select==6) {
			System.out.println("================selectNameEmp======================");
			String name = "회";
			List<Employee>empList = dao.selectNameEmp(name);
			for(Employee e:empList) System.out.println(e);
		}//if
	}//main
}//class
