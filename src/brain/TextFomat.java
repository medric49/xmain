package brain;

import java.util.ArrayList;

public abstract class TextFomat {

    // ======================= Public methods =============================

    public static String createHypothesis(String problem, String solution) {
        return "hypothesis(\"" + solution + "\") :- "+problem+", !.";
    }

    public static String createVerification(String problem, ArrayList<String> symptoms) {
        return problem + " :- " + createVerifyList(symptoms) + ".";
    }

    // ======================= Private methods =============================
    private static String createVerifyElement(String symptom) {
        return "verify(\""+symptom+"\")";
    }

    private static String createVerifyList(ArrayList<String> symptoms) {
        StringBuilder result = new StringBuilder();

        for (int i  = 0; i< symptoms.size()-1; i++) {
            result.append(createVerifyElement(symptoms.get(i))).append(", ");
        }

        result.append(createVerifyElement(symptoms.get(symptoms.size()-1)));

        return result.toString();
    }

}
