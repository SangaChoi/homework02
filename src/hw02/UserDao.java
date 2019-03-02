package hw02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class UserDao {
	
	public void addUser(UserVO userVO){
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try{		
			InitialContext ic=new InitialContext();
			DataSource ds=(DataSource)ic.lookup("java:comp/env/jdbc/ora");
			con=ds.getConnection();
			
			pstmt = con.prepareStatement(	"INSERT INTO addUser"
					+ "(name, sex, year, birthMonth, birthDay, occupation, firstNumberM, middleNumberM, lastNumberM)"
					+ "values(?,?,?,?,?,?,?,?,?)" );
			
			pstmt.setString(1,userVO.getName());
			pstmt.setString(2,userVO.getSex());
			pstmt.setString(3,userVO.getYear());
			pstmt.setString(4,userVO.getBirthMonth());
			pstmt.setString(5,userVO.getBirthDay());
			pstmt.setString(6,userVO.getOccupation());
			pstmt.setString(7,userVO.getFirstNumberM());
			pstmt.setString(8,userVO.getMiddleNumberM());
			pstmt.setString(9,userVO.getLastNumberM());
			
			rs=pstmt.executeQuery();

			//3단계 결과값 확인하기 => DB에 data insert 유무확인 ...
			if(rs.next()) {
				userVO.setActive(true);	
				System.out.println(userVO);
			}
		}catch(Exception e){		
			e.printStackTrace();
		}finally{
			//각각의 DB와 관련된 객체 close
			if(pstmt != null){
				try{	
					pstmt.close();	
				}catch(Exception e2){  }
			}
			if(con != null){
				try{	
					con.close();	
				}catch(Exception e3){  }
			}
		}
	}//end of addUser()
	
	
	public UserVO findUser(String name){
		
		UserVO uv=new UserVO();
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			InitialContext ic=new InitialContext();
			DataSource ds=(DataSource)ic.lookup("java:comp/env/jdbc/ora");
			con=ds.getConnection();
			
			pstmt=con.prepareStatement("SELECT*FROM addUser WHERE name=?");
			pstmt.setString(1, name);
			
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				uv.setName(rs.getString(1));
				uv.setSex(rs.getString(2));
				uv.setYear(rs.getString(3));
				uv.setBirthMonth(rs.getString(4));
				uv.setBirthDay(rs.getString(5));
				uv.setOccupation(rs.getString(6));
				uv.setFirstNumberM(rs.getString(7));
				uv.setMiddleNumberM(rs.getString(8));
				uv.setLastNumberM(rs.getString(9));
				uv.setActive(true);				
			}else {
				uv.setActive(false);
			}
					
		}catch(Exception e) {
				e.printStackTrace();
			}finally {
				if(rs !=null) {
					try {
						rs.close();
					}catch(Exception e1) {}
				}
				if(pstmt !=null) {
					try {
						pstmt.close();
					}catch(Exception e2) {}
				}
				if(con !=null) {
					try {
						con.close();
					}catch(Exception e3) {}
				}
			}
					
		return uv;
	}

}

