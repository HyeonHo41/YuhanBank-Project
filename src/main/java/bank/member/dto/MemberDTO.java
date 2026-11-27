package bank.member.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;


public class MemberDTO {

	// MemberDTO -> Role, Status는 ENUM값으로 관리(DB는 check)
	private String memberId;              
	private String memberPw;            
	private String memberName;           
	private LocalDate birthDate;          
	private String phone;                
	private String email;                
	private MemberRole memberRole;            // MEMBER_ROLE     USER / ADMIN
	private MemberStatus memberStatus;          // MEMBER_STATUS   ACTIVE / LOCKED / SUSPENDED / WITHDRAWN
	private int loginFailCnt;            
	private LocalDateTime lockedUntil;  
	private LocalDateTime joinedAt;      
	private LocalDateTime updatedAt;     
	private LocalDateTime withdrawnAt;    

	public MemberDTO() {
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberPw() {
		return memberPw;
	}

	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public MemberRole getMemberRole() {
		return memberRole;
	}

	public void setMemberRole(MemberRole memberRole) {
		this.memberRole = memberRole;
	}

	public MemberStatus getMemberStatus() {
		return memberStatus;
	}

	public void setMemberStatus(MemberStatus memberStatus) {
		this.memberStatus = memberStatus;
	}

	public int getLoginFailCnt() {
		return loginFailCnt;
	}

	public void setLoginFailCnt(int loginFailCnt) {
		this.loginFailCnt = loginFailCnt;
	}

	public LocalDateTime getLockedUntil() {
		return lockedUntil;
	}

	public void setLockedUntil(LocalDateTime lockedUntil) {
		this.lockedUntil = lockedUntil;
	}

	public LocalDateTime getJoinedAt() {
		return joinedAt;
	}

	public void setJoinedAt(LocalDateTime joinedAt) {
		this.joinedAt = joinedAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public LocalDateTime getWithdrawnAt() {
		return withdrawnAt;
	}

	public void setWithdrawnAt(LocalDateTime withdrawnAt) {
		this.withdrawnAt = withdrawnAt;
	}


	@Override
	public String toString() {
		return "MemberDTO [memberId=" + memberId + ", memberName=" + memberName
				+ ", birthDate=" + birthDate + ", phone=" + phone + ", email=" + email
				+ ", memberRole=" + memberRole + ", memberStatus=" + memberStatus
				+ ", loginFailCnt=" + loginFailCnt + ", lockedUntil=" + lockedUntil
				+ ", joinedAt=" + joinedAt + "]";
	}
}
