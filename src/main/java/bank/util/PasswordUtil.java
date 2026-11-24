package bank.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import bank.exception.BankException;


public class PasswordUtil {
	
	
	private PasswordUtil() {
	}
	
    public static String sha256(String raw) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(raw.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder(64);
            for (byte b : hash) sb.append(String.format("%02x", b));
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-256 을 사용할 수 없습니다.", e);
        }
    }
	
    public static boolean matches(String raw, String storedHash) {
        return raw != null && storedHash != null && sha256(raw).equals(storedHash);
    }
    
    public static void checkPin(String pin) {
        if (pin == null || !pin.matches("\\d{4}")) {
            throw new BankException("비밀번호는 숫자 4자리여야 합니다.");
        }
    }
}
