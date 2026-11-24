package bank.exception;

public class BankException extends RuntimeException{

	// 부모 예외 처리
	public BankException(String message) {
		super(message);
	}
	
	public BankException(String message, Throwable cause) {
		super(message, cause);
	}
	
	
	
	
}
