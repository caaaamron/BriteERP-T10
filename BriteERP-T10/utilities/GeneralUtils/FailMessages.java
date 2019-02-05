package utilities.GeneralUtils;

public class FailMessages {
	
	public static String METHOD_FAIL;
	public static String EXCEPTION;
	
	public static String getMethod(Exception e) {
		METHOD_FAIL = e.getStackTrace()[0].getMethodName();
		return METHOD_FAIL;
	}
	
	public static String getException(Exception e) {
		EXCEPTION = e.getClass().getSimpleName();
		return EXCEPTION;
	}
	
	public static String fail(Exception e) {
		String failMessage = getMethod(e) + ": FAILED --> " + getException(e);
		System.out.println(failMessage);
		return failMessage;
	}
}
