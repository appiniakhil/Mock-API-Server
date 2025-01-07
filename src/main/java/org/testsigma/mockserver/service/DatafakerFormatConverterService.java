package org.testsigma.mockserver.service;

import net.datafaker.Faker;
import org.springframework.stereotype.Service;
import org.testsigma.mockserver.enums.DynamicVariables;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class DatafakerFormatConverterService {
    private static final Faker faker = new Faker();
    // pattern which matches  any of these -> functionName, functionName(argument1) and functionName(argument1,argument2).
    private static final Pattern DYNAMIC_PATTERN = Pattern.compile("\\{\\{\\$([a-zA-Z]+)\\((\\d*)\\,?(\\d*)\\)?\\}\\}");
    private static final Pattern REQUEST_BODY_PATTERN = Pattern.compile("\\{\\{\\$requestBody\\}\\}");
    public static String getUpdatedString(String body,String responseBody) {
        for (DynamicVariables variable : DynamicVariables.values()) {
            responseBody = responseBody.replace(variable.getPlaceholder(), variable.getFakerExpression());
        }
        if(body!=null){
        responseBody = scanForRequestBody(body, responseBody);
        }
        Matcher matcher = DYNAMIC_PATTERN.matcher(responseBody);
        while (matcher.find()) {
            String placeholder = matcher.group(0);
            String fakerExpression = getFakerExpression(matcher);
            responseBody = responseBody.replace(placeholder, fakerExpression);
        }
        return faker.expression(responseBody);
    }

    private static String scanForRequestBody(String body,String responseBody) {
        Matcher matcher = REQUEST_BODY_PATTERN.matcher(responseBody);

        StringBuffer result = new StringBuffer();

        while (matcher.find()) {
            matcher.appendReplacement(result, body);
        }
        matcher.appendTail(result);

        return result.toString();
    }
    // converting dynamic Variables into the appropriate Datafaker format
    private static String getFakerExpression(Matcher matcher) {
        String functionName = matcher.group(1);
        String arg1 = matcher.group(2);
        String arg2 = matcher.group(3);
        String[] args = functionName.split("(?=[A-Z])");
        String fakerExpression;
        if (!arg1.isEmpty() && !arg2.isEmpty()) {
            fakerExpression = String.format("#{%s.%s_%s '%s','%s'}",args[0],args[0],args[1], arg1, arg2);
        } else if (!arg1.isEmpty()) {
            fakerExpression = String.format("#{%s.%s '%s'}", args[0],args[1], arg1);
        } else {
            fakerExpression = String.format("#{%s}", functionName);
        }
        return fakerExpression;
    }
}
