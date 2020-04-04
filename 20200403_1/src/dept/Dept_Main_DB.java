package dept;

import java.util.*;

public class Dept_Main_DB {
	public static void main(String[] args) {
		DeptDao dao = DeptDaoImpl.getInstance();
		Scanner sc = new Scanner(System.in);
		
		System.out.println("원하는 서비스를 선택하시오");
		System.out.println("=====================================================");
		System.out.println(" 1.전체 조회 2.번호로 조회 3.사원 등록 4.사원정보 수정 5.삭제 ");
		System.out.println("=====================================================");
		
		int select = sc.nextInt();
		
		if(select==1) { //조회 OK
			System.out.println("selectAll");
			List<DeptDto> empList = dao.select();
			for(DeptDto e:empList) System.out.println(e);
		}else if(select==2) { //안된다
			System.out.println("selectOne");
			DeptDto dto = dao.select(2);
			System.out.println(dto);
		}else if(select==3) { //삽입 OK
			System.out.println("insertEmp");
			DeptDto em = new DeptDto(3, "농어회", "서울");
			if(dao.insert(em)) {
				System.out.println("저장 완료");
			}else {
				System.out.println("저장 실패");
			}
		}else if(select==4) {//OK
			System.out.println("updateEmp");
			DeptDto e1 = new DeptDto();
			e1.setNo(2);
			e1.setName("농어회");
			e1.setLoc("부산");
			if(dao.update(e1)) {
				System.out.println("수정 완료");
			} else {
				System.out.println("수정 실패");
			}
		}else if(select==5) { //삭제 OK
			System.out.println("deleteEmp");
			if(dao.delete(1)) {
				System.out.println("삭제 완료");
			}else {
				System.out.println("삭제 실패");
			}
		}
	}
}
