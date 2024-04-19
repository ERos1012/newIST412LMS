package Model;

import java.util.List;

public class MultipleChoiceQuestion implements Question{
    private String questionText;
    private List<String> options;

    public MultipleChoiceQuestion(String questionText, List<String> options) {
        this.questionText = questionText;
        this.options = options;
    }

    @Override
    public String displayQuestion() {
        StringBuilder displayText = new StringBuilder("Multiple Choice: " + questionText + "\nOptions:\n");
        char optionLabel = 'A';
        for (String option : options) {
            displayText.append(optionLabel++).append(": ").append(option).append("\n");
        }
        return displayText.toString();
    }

    // Getters and setters
    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    @Override
    public String getType() {
        return "Multiple Choice";
    }

    @Override
    public String getText() {
        return questionText;
    }

    @Override
    public String getCorrectAnswer() {
        return null;
    }

    @Override
    public void setAnswer(String answer) {
        // Do nothing
    }
    
}
