package bank.member.dto;

public enum MemberStatus {
	
	ACTIVE("정상"),
	LOCKED("잠김"),        
	SUSPENDED("정지"),    
	WITHDRAWN("탈퇴");

	private final String label;

	MemberStatus(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
}