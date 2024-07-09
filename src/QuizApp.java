import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class QuizApp {
    private List<QuizQuestion> questions;
    private int currentQuestionIndex;
    private int score;
    private Scanner scanner;
    private Timer timer;

    public QuizApp(List<QuizQuestion> questions) {
        this.questions = questions;
        this.currentQuestionIndex = 0;
        this.score = 0;
        this.scanner = new Scanner(System.in);
        this.timer = new Timer();
    }

    public void startQuiz() {
        System.out.println("Welcome to the Quiz!");

        for (QuizQuestion question : questions) {
            displayQuestion(question);
            if (!answerQuestion(question)) {
                break; // Break if user wants to quit
            }
        }

        showResult();
        scanner.close();
    }

    private void displayQuestion(QuizQuestion question) {
        System.out.println("\nQuestion: " + question.getQuestion());
        List<String> options = question.getOptions();
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ". " + options.get(i));
        }
        System.out.print("Enter your choice (1-" + options.size() + "), or 0 to quit: ");
    }

    private boolean answerQuestion(QuizQuestion question) {
        int userChoice;
        try {
            TimerTask task = new TimerTask() {
                public void run() {
                    System.out.println("\nTime's up! Moving to the next question.");
                }
            };
            timer.schedule(task, 15000); // 15 seconds timer

            userChoice = scanner.nextInt();
            task.cancel(); // Cancel timer on user input
        } catch (Exception e) {
            System.out.println("\nInvalid input. Moving to the next question.");
            return true;
        }

        if (userChoice == 0) {
            return false; // User wants to quit
        }

        int correctAnswerIndex = question.getCorrectAnswerIndex();
        if (userChoice == correctAnswerIndex + 1) {
            System.out.println("Correct!");
            score++;
        } else {
            System.out.println("Incorrect. The correct answer is: " + (correctAnswerIndex + 1));
        }
        return true;
    }

    private void showResult() {
        System.out.println("\nQuiz Ended!");
        System.out.println("Your score: " + score + " / " + questions.size());
    }

    public static void main(String[] args) {
        // Example quiz questions
        List<QuizQuestion> quizQuestions = new ArrayList<>();
        quizQuestions.add(new QuizQuestion("What is the capital of France?",
                List.of("Paris", "Berlin", "London", "Rome"), 0));
        quizQuestions.add(new QuizQuestion("Who is the author of 'Harry Potter' series?",
                List.of("J.K. Rowling", "Stephen King", "George R.R. Martin", "Dan Brown"), 0));
        quizQuestions.add(new QuizQuestion("Which planet is known as the Red Planet?",
                List.of("Venus", "Mars", "Jupiter", "Saturn"), 1));

        QuizApp quizApp = new QuizApp(quizQuestions);
        quizApp.startQuiz();
    }
}
