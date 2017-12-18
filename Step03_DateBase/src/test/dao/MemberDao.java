package test.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import test.dto.MemberDto;
import test.util.DBConnect;

public class MemberDao {
	//static 맴버필드 
	private static MemberDao dao;
	//외부에서 객체 생성하지 못하게 
	private MemberDao() {}
	//참조값을 리턴해주는 메소드
	public static MemberDao getInstance() {
		if(dao==null) {
			dao=new MemberDao();
		}
		return dao;
	}
	
	//회원 한명의 정보를 수정 반영하는 메소드
	public boolean update(MemberDto dto) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		boolean isSuccess=false;//저장 성공 여부 
		try {
			conn=new DBConnect().getConn();
			String sql="UPDATE member SET name=? , addr=? WHERE num=?";
			pstmt=conn.prepareStatement(sql);
			//?에 추가할 회원 정보 바인딩하기
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getAddr());
			pstmt.setInt(3, dto.getNum());
			
			int flag=pstmt.executeUpdate();
			if(flag>0) {
				isSuccess=true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(conn!=null)pstmt.close();
				if(pstmt!=null)pstmt.close();
			}catch(Exception e) {}
		}
		return isSuccess;
}
	//회원 한명의 정보를 리턴해 주는 메소드
	public MemberDto getData(int num) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		MemberDto dto=null;
		try {
			conn=new DBConnect().getConn();
			String sql="SELECT name,addr FROM member "
					+ "WHERE num=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				String name=rs.getString("name");
				String addr=rs.getString("addr");
				//회원정보를 MemberDto 에 담고
				dto=new MemberDto(num,name,addr);
				//ArrayList 객체에 누적 시킨다.
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}catch(Exception e) {}
		}
		return dto;
}
	//회원 정보를 저장하는 메소드
	public boolean insert(MemberDto dto) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		boolean isSuccess=false;// 삭제 성공 여부 
		try {
			conn=new DBConnect().getConn();
			String sql="INSERT INTO member (num,name,addr) VALUES(member_seq.NEXTVAL,?,?)" ;
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getAddr());
			//추가하고 나서 추가한  row 의 갯수 리턴 받기
			int flag=pstmt.executeUpdate();
			if(flag>0) {
				isSuccess=true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}catch(Exception e) {}
		}
		return isSuccess;
	}
	//회원 정보를 삭제하는 메소드
	public boolean delete(int num) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		boolean isSuccess=false;// 삭제 성공 여부 
		try {
			conn=new DBConnect().getConn();
			String sql="DELETE FROM member WHERE num=?";
			pstmt=conn.prepareStatement(sql);
			//1번째? 에 삭제할 회원번호 바인딩하기
			pstmt.setInt(1, num);
			//삭제하고 삭제한 row 의 갯수 리턴 받기
			int flag=pstmt.executeUpdate();
			if(flag>0) {
				isSuccess=true;
			}
		}catch(Exception e) {
		
		}finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}catch(Exception e) {}
		}
		return isSuccess;
	}
	//회원 목록을 리턴해주는 메소드
	public List<MemberDto> getList(){
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<MemberDto> list=new ArrayList<>();
		try {
			conn=new DBConnect().getConn();
			String sql="SELECT num,name,addr FROM member "
					+ "ORDER BY num DESC";
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				int num=rs.getInt("num");
				String name=rs.getString("name");
				String addr=rs.getString("addr");
				//회원정보를 MemberDto 에 담고
				MemberDto dto=new MemberDto(num,name,addr);
				//ArrayList 객체에 누적 시킨다.
				list.add(dto);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}catch(Exception e) {}
		}
		return list;
	}
}