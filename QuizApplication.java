import java.util.*;

class Question {
    String questionText;
    String[] options;
    int correctAnswerIndex;

    // Constructor
    public Question(String questionText, String[] options, int correctAnswerIndex) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
    }
}

public class QuizApplication {
    private List<Question> questions;
    private int score;
    private Scanner scanner;

    // Constructor initializes quiz questions
    public QuizApplication() {
        scanner = new Scanner(System.in);
        questions = new ArrayList<>();

        // Adding quiz questions
        questions.add(new Question("What is the capital of France?",
                new String[] { "1. London", "2. Paris", "3. Berlin", "4. Rome" }, 1));
        questions.add(new Question("Which data structure uses LIFO?",
                new String[] { "1. Queue", "2. Stack", "3. Linked List", "4. Heap" }, 1));
        questions.add(new Question("What is 5 + 3?",
                new String[] { "1. 6", "2. 8", "3. 9", "4. 10" }, 1));
        questions.add(new Question("Which planet is known as the Red Planet?",
                new String[] { "1. Earth", "2. Venus", "3. Mars", "4. Jupiter" }, 2));
        questions.add(new Question("What is the full form of CPU?",
                new String[] { "1. Central Process Unit", "2. Central Processing Unit",
                        "3. Computer Processing Unit", "4. Core Processing Unit" },
                2));
    }

    // Start Quiz
    public void startQuiz() {
        System.out.println("===== Welcome to the Quiz! =====");
        System.out.println("You have 10 seconds to answer each question.\n");

        score = 0;

        for (Question question : questions) {
            boolean answered = askQuestionWithTimer(question);
            if (!answered) {
                System.out.println(" Time's up! Moving to the next question...\n");
            }
        }

        // Display final score
        displayResults();
    }

    // Display question with a timer
    private boolean askQuestionWithTimer(Question question) {
        Timer timer = new Timer();
        final boolean[] answered = { false };

        // Timer task to handle timeout
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (!answered[0]) {
                    System.out.println("\n Time's up! You didn't answer in time.");
                    scanner.nextLine(); // Clear input buffer
                }
            }
        };

        // Start the timer (10 seconds)
        timer.schedule(task, 10000);

        // Ask question
        System.out.println(question.questionText);
        for (String option : question.options) {
            System.out.println(option);
        }
        System.out.print("Enter your choice (1-4): ");

        // Read user input
        if (scanner.hasNextInt()) {
            int userAnswer = scanner.nextInt();
            answered[0] = true;
            timer.cancel(); // Cancel timer as the user has answered

            // Check answer correctness
            if (userAnswer - 1 == question.correctAnswerIndex) {
                System.out.println(" Correct!\n");
                score++;
            } else {
                System.out.println(" Incorrect! The correct answer was: "
                        + (question.correctAnswerIndex + 1) + "\n");
            }
            return true;
        } else {
            scanner.nextLine(); // Clear invalid input
            return false;
        }
    }

    // Display final results
    private void displayResults() {
        System.out.println("===== Quiz Over! =====");
        System.out.println("Your final score: " + score + " out of " + questions.size());

        if (score == questions.size()) {
            System.out.println(" Perfect score! Well done!");
        } else if (score >= questions.size() / 2) {
            System.out.println(" Good job!");
        } else {
            System.out.println(" Keep practicing!");
        }
    }

    // Main method
    public static void main(String[] args) {
        QuizApplication quiz = new QuizApplication();
        quiz.startQuiz();
    }
}
