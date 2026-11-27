package bank.member.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import bank.member.dto.MemberDTO;
import bank.member.dto.MemberRole;
import bank.member.dto.MemberStatus;

public class MemberDAO {

	// Id로 회원 조회 
	public MemberDTO findById(Connection conn, String memberId) throws SQLException {
		
		String sql = "SELECT * FROM MEMBER WHERE MEMBER_ID = ?";
		
		
		try (PreparedStatement ps = conn.prepareStatement(sql)){

			ps.setString(1, memberId);
			try (ResultSet rs = ps.executeQuery()){
				if (rs.next()) {
					return memberDtoMapper(rs);
				}
				return null;
				
			}
		} 
		
		
	}
	
	// 회원 생성
	public int insert(Connection conn, MemberDTO dto) throws SQLException {
		String sql = "INSERT INTO MEMBER (MEMBER_ID, MEMBER_PW, MEMBER_NAME, BIRTH_DATE,PHONE, EMAIL) VALUES (?, ?, ?, ?, ?, ?)";
		
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, dto.getMemberId());
			ps.setString(2, dto.getMemberPw());
			ps.setString(3, dto.getMemberName());
			ps.setDate(4, Date.valueOf(dto.getBirthDate()));
			ps.setString(5, dto.getPhone());
			ps.setString(6, dto.getEmail());
			
			return ps.executeUpdate();
			
		}
	}
	
	
	// email 중복확인
	public boolean existsEmail(Connection conn, String email) throws SQLException{
		String sql = "SELECT COUNT(*) FROM MEMBER WHERE EMAIL = ?";
		
		try (PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setString(1, email);
			try (ResultSet rs = ps.executeQuery()){
				rs.next();
				return rs.getInt(1) > 0;
				
				
			}
		} 
	}

	// MemberDTO로 변환
	private MemberDTO memberDtoMapper(ResultSet rs) throws SQLException{
		
		MemberDTO dto = new MemberDTO();
		
		dto.setMemberId(rs.getString("MEMBER_ID"));
		dto.setMemberPw(rs.getString("MEMBER_PW"));
		dto.setMemberName(rs.getString("MEMBER_NAME"));
		
		// DATE -> LocalDate로 변환
		Date birth = rs.getDate("BIRTH_DATE");
		dto.setBirthDate(birth == null ? null : birth.toLocalDate());

		dto.setPhone(rs.getString("PHONE"));
		dto.setEmail(rs.getString("EMAIL"));
		// 문자열 -> ENUM값으로 변환
		dto.setMemberRole(MemberRole.valueOf(rs.getString("MEMBER_ROLE")));
		dto.setMemberStatus(MemberStatus.valueOf(rs.getString("MEMBER_STATUS")));
		
		dto.setLoginFailCnt(rs.getInt("LOGIN_FAIL_CNT"));
		
		// TimeStamp -> LocalDateTime으로 변혼
		dto.setLockedUntil(rs.getObject("LOCKED_UNTIL", LocalDateTime.class));
		dto.setJoinedAt(rs.getObject("JOINED_AT", LocalDateTime.class));
		dto.setUpdatedAt(rs.getObject("UPDATED_AT", LocalDateTime.class));
		dto.setWithdrawnAt(rs.getObject("WITHDRAWN_AT", LocalDateTime.class));
		
		
		return dto;
	}
}
