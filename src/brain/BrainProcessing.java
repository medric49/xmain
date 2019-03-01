package brain;

import model.Knowledge;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class BrainProcessing {

    private static File file =new File("record.xml");
    private static SAXBuilder sax=new SAXBuilder();
    private static Document document;
    private static XMLOutputter xmlOutput=new XMLOutputter();

    private static final String BRAIN_FILE = "brain.pl";


    private static final String NEXT_ID_TAG = "NextId";
    private static final String NEXT_HYPOTHESIS_LINE_TAG = "NextHypothesisLine";
    private static final String NEXT_VERIFICATION_LINE_TAG = "NextVerificationLine";

    private static final String KNOWLEDGE_TAG = "Knowledge";
    private static final String HYPOTHESIS_LINE_TAG = "HypothesisLine";
    private static final String VERIFICATION_LINE_TAG = "VerificationLine";
    private static final String PROBLEM_TAG = "Problem";
    private static final String SOLUTION_TAG = "Solution";
    private static final String SYMPTOM_TAG = "Symptom";

    static
    {
        setDoc();
    }


    // ========================= Public method =================================
    public static Knowledge save(String problem, String solution, ArrayList<String> symptoms) {
        String hypothesisBuf = TextFomat.createHypothesis(problem, solution);
        String verificationBuf = TextFomat.createVerification(problem, symptoms);


        ArrayList<String> bytesFile = read();

        int hypothesisLine = Integer.parseInt(getNextHypothesisLine());
        int verificationLine = Integer.parseInt(getNextVerificationLine());

        bytesFile.add(hypothesisLine, hypothesisBuf);
        bytesFile.add(verificationLine, verificationBuf);
        write(bytesFile);

        return record(problem, solution, symptoms);
    }

    public static ArrayList<Knowledge> getKnowledges() {
        ArrayList<Knowledge> knowledges = new ArrayList<>();

        List<Element> knowledgesElements = document.getRootElement().getChildren(KNOWLEDGE_TAG);

        for ( int i = 0; i< knowledgesElements.size(); i++) {
            Element knowledgeElement = knowledgesElements.get(i);

            Element hypothesisLineElement = knowledgeElement.getChild(HYPOTHESIS_LINE_TAG);
            Element verificationLineElement = knowledgeElement.getChild(VERIFICATION_LINE_TAG);
            Element problemElement = knowledgeElement.getChild(PROBLEM_TAG);
            Element solutionElement = knowledgeElement.getChild(SOLUTION_TAG);

            List<Element> symptomsElements = knowledgeElement.getChildren(SYMPTOM_TAG);

            String id = knowledgeElement.getAttributeValue("id");
            String hypothesisLine = hypothesisLineElement.getText();
            String verificationLine = verificationLineElement.getText();
            String problem = problemElement.getText();
            String solution = solutionElement.getText();
            ArrayList<String> symptoms = new ArrayList<>();

            for ( int j = 0; j< symptomsElements.size(); j++) {
                symptoms.add( symptomsElements.get(i).getText() );
            }
            knowledges.add(new Knowledge(id, hypothesisLine, verificationLine, problem, solution, symptoms));
        }

        return knowledges;
    }

    public static void delete(Knowledge knowledge) {
        List<Element> rootChildren =  document.getRootElement().getChildren();

        for ( int i=0; i< rootChildren.size(); i++ ) {
            Element element = rootChildren.get(i);

            if (element.getAttribute("id").getValue().equals(knowledge.getId())) {

                String hypothesisLine = getNextHypothesisLine();
                String verificationLine = getNextVerificationLine();
                int nHypothesisLine = Integer.parseInt(hypothesisLine)-1;
                int nVerificationLine = Integer.parseInt(verificationLine)-2;

                rootChildren.remove(element);
                setNextLines(""+nHypothesisLine, ""+nVerificationLine);
                setDoc();


                ArrayList<String> bytesFile = read();
                bytesFile.remove(Integer.parseInt(hypothesisLine));
                bytesFile.remove(Integer.parseInt(verificationLine)-1);
                write(bytesFile);

                break;
            }

        }

    }

    // =========================== Private method =============================
    private static Knowledge record(String problem, String solution, ArrayList<String> symptoms) {
        String id = getNextId();
        String hypothesisLine = getNextHypothesisLine();
        String verificationLine = getNextVerificationLine();

        Element knowledgeElement = new Element(KNOWLEDGE_TAG).setAttribute("id", id);
        Element hypothesisLineElement = new Element(HYPOTHESIS_LINE_TAG).setText(hypothesisLine);
        Element verificationLineElement = new Element(VERIFICATION_LINE_TAG).setText(verificationLine);
        Element problemElement = new Element(PROBLEM_TAG).setText(problem);
        Element solutionElement =  new Element(SOLUTION_TAG).setText(solution);

        ArrayList<Element> symptomElements = new ArrayList<>();
        for ( int i = 0; i < symptoms.size(); i++) {
            symptomElements.add(
                    new Element(SYMPTOM_TAG).setText(symptoms.get(i))
            );
        }

        knowledgeElement.getChildren().add(hypothesisLineElement);
        knowledgeElement.getChildren().add(verificationLineElement);
        knowledgeElement.getChildren().add(problemElement);
        knowledgeElement.getChildren().add(solutionElement);
        for ( int i = 0; i< symptomElements.size(); i++ ) {
            knowledgeElement.getChildren().add(symptomElements.get(i));
        }
        document.getRootElement().getChildren().add(knowledgeElement);

        String nextId = ""+( Integer.parseInt(id)+1 );
        String nextHypothesisLine = ""+ (Integer.parseInt(hypothesisLine)+1);
        String nextVerificationLine = ""+ (Integer.parseInt(verificationLine)+2);

        setNextLines(nextId, nextHypothesisLine, nextVerificationLine);

        setOutput();

        return new Knowledge(id, hypothesisLine, verificationLine, problem, solution, symptoms);
    }

    private static String getNextId() {
        return document.getRootElement().getChild(NEXT_ID_TAG).getText();
    }

    private static String getNextHypothesisLine() {
        return document.getRootElement().getChild(NEXT_HYPOTHESIS_LINE_TAG).getText();
    }

    private static String getNextVerificationLine() {
        return document.getRootElement().getChild(NEXT_VERIFICATION_LINE_TAG).getText();
    }

    private static void setNextLines(String id, String hLine, String vLine) {
        document.getRootElement().getChild(NEXT_ID_TAG).setText(id);
        document.getRootElement().getChild(NEXT_HYPOTHESIS_LINE_TAG).setText(hLine);
        document.getRootElement().getChild(NEXT_VERIFICATION_LINE_TAG).setText(vLine);
    }

    private static void setNextLines(String hLine, String vLine) {
        document.getRootElement().getChild(NEXT_HYPOTHESIS_LINE_TAG).setText(hLine);
        document.getRootElement().getChild(NEXT_VERIFICATION_LINE_TAG).setText(vLine);
    }



    private static void setDoc()
    {
        try
        {
            document = sax.build(file);

        } catch (JDOMException | IOException ex)
        {
            ex.printStackTrace();
        }
    }

    private static void setOutput()
    {
        try {
            xmlOutput.output(document, new FileOutputStream(file));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }



    private static ArrayList<String> read() {
        ArrayList<String> bytesFile = new ArrayList<String>();
        File file = new File(BRAIN_FILE);

        try {
            FileInputStream io = new FileInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(io));

            String line = null;

            while ( (line = reader.readLine()) != null ) {
                bytesFile.add(line);
            }
            reader.close();
            io.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytesFile;
    }

    private static void write(ArrayList<String> bytesFile) {
        File file = new File(BRAIN_FILE);
        try {
            FileOutputStream io = new FileOutputStream(file);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(io));
            for (int i = 0; i< bytesFile.size(); i++) {
                writer.write(bytesFile.get(i));
                writer.newLine();
            }
            writer.close();
            io.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}