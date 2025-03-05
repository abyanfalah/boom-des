package id.asqi.idesa.bumdes.core.component.logging;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
@Slf4j
public class ErrorLogger {

	public static void printFilteredStackTrace (Exception e, String packagePrefix) {
		packagePrefix = getMainPackageString(packagePrefix);

		String logString = """
				    
				Error Message: "[message]"
				Location: [location]
				Simplified error stack trace --------------------------------------------------------------------------------------------------------------
				[stackTrace]
				""";

		String stackTraceString = "";
		Pattern pattern = Pattern.compile(packagePrefix + "\\..+");
		for (StackTraceElement element : e.getStackTrace()) {
			if (pattern.matcher(element.getClassName()).find()) {
				stackTraceString = stackTraceString.concat("\t" + element).concat("\n");
			}
		}

		StackTraceElement element = e.getStackTrace()[0];
		String location = element.getClassName() + "." + element.getMethodName() + "():" + element.getLineNumber();

		logString = logString.replace("[message]", e.getClass().getSimpleName() + ": " + e.getMessage());
		logString = logString.replace("[stackTrace]", stackTraceString);
		logString = logString.replace("[location]", location);
		log.error(logString);

		// not logged to file
//		System.err.println(logString);
	}

	private static String getMainPackageString (String packageName) {
		int thirdDotIndex = packageName.indexOf('.', packageName.indexOf('.', packageName.indexOf('.') + 1) + 1);
		if (thirdDotIndex != - 1) {
			packageName = packageName.substring(0, thirdDotIndex);
		}
		return packageName;
	}
}