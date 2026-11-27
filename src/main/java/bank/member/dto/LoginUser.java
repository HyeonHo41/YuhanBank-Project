package bank.member.dto;

// View에 쓸 로그인 DTO (MemberDTO에 민감한 정보 빠진 버전)
public class LoginUser {

	private final String memberId;
	private final String name;
	private final MemberRole role;         

	public LoginUser(String memberId, String name, MemberRole role) {
		this.memberId = memberId;
		this.name = name;
		this.role = role;
	}

	public String getMemberId() {
		return memberId;
	}

	public String getName() {
		return name;
	}

	public MemberRole getRole() {
		return role;
	}

	public boolean isAdmin() {
		return role == MemberRole.ADMIN;    
	}
}