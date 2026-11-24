package bank.member.dto;

public class LoginUser {
	
	
	   private final String memberId;   
	    private final String name;       
	    private final String role;      

	    public LoginUser(String memberId, String name, String role) {
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
	    public String getRole() {
	    	return role; 
	    }

	    
	    public boolean isAdmin() {
	        return "ADMIN".equals(role);
	    }
	}