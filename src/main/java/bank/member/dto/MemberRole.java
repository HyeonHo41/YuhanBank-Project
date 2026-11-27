package bank.member.dto;

public enum MemberRole {
	
	USER("회원"),
	ADMIN("관리자");
	
	private final String label;
	
	private MemberRole(String label) {
		// TODO Auto-generated constructor stub
		this.label = label;
	}
	
	public String getLabel() {
		return label;
	}

}
