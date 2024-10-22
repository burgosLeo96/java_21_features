package examples.java21.stringtemplates;

import java.util.List;

import static java.util.FormatProcessor.FMT;

// NOTE: latest Java version (23) removed String templates.
public class StringTemplates {

    public static String processStringTemplate(StringTemplate.Processor processor, StringTemplate template) throws Throwable {
        return (String) processor.process(template);
    }

    public static String stringTemplateExample(String var1, String var2, int var3) throws Throwable {
        return processStringTemplate(STR,
                StringTemplate.of(
                    List.of("This is an example using STR template processor. var1 = ", ". var2 = ", ". var3 = ", ""), List.of(var1, var2, var3)));
    }

    public static String stringTemplateExample2(String var1, String var2, int var3) {
        return FMT."This is an example using FMT template processor. var1 = %s\{var1}. var2 = %s\{var2}. var3 %d\{var3}";
    }

    public static void main(String[] args) throws Throwable {
        var var1 = "Hello";
        var var2 = "World";
        var var3 = 150_000;

        System.out.println(stringTemplateExample(var1, var2, var3));
        System.out.println(stringTemplateExample2(var1, var2, var3));
    }

}
