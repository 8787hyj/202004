package employee;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Emp_Main_DB {
	public static void main(String[] args) {
		EmpDao dao = EmpDaoImpl.getInstance();
		Scanner sc = new Scanner(System.in);
		
		System.out.println("원하는 서비스를 선택하시오");
		System.out.println("=====================================================");
		System.out.println(" 1.전체 조회 2.번호로 조회 3.사원 등록 4.사원정보 수정 5.삭제 6.이름으로 조회");
		System.out.println("=====================================================");
		
		int select = sc.nextInt();
		if(select==1) {
			System.out.println("selectAll");
			List<Employee> empList = dao.selectAll();
			for(Employee e:empList) System.out.println(e);
		}else if(select==2) {
			System.out.println("selectOne");
			Employee em = dao.selectOne(1);
			System.out.println(em);
		}else if(select==3) {
			System.out.println("insertEmp");
			Employee em = new Employee(0, "우럭회", Date.valueOf(LocalDate.of(2000, 01, 01)),
									"010-1111-1111", "서울시", "aa@aa.com");
			if(dao.insertEmp(em)) {
				System.out.println("저장 완료");
			}else {
				System.out.println("저장 실패");
			}
		}else if(select==4) {
			System.out.println("updateEmp");
			Employee e1 = new Employee();
			e1.setBunho(1);
			e1.setName("농어회");
			e1.setBirth(Date.valueOf(LocalDate.of(2002, 03, 15)));
			e1.setTel("010-2222-1111");
			e1.setAddr("서울시");
			e1.setEmail("dd@dd.com");
			
			if(dao.updateEmp(e1)) {
				System.out.println("수정 완료");
			} else {
				System.out.println("수정 실패");
			}
		}else if(select==5) {
			System.out.println("deleteEmp");
			if(dao.deleteEmp(1)) {
				System.out.println("삭제 완료");
			}else {
				System.out.println("삭제 실패");
			}
		}else if(select==6) {
			System.out.println("selectNameEmp");
			String name = "회";
			List<Employee> empList = dao.selectNameEmp(name);
			for(Employee e: empList)
				System.out.println(e);
		}
	}
}
