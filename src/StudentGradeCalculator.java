import java.util.Scanner;

public class StudentGradeCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        final double[] GRADE_THRESHOLDS = {90.0, 80.0, 70.0, 60.0};
        final String[] GRADE_LABELS = {"A", "B", "C", "D", "F"};

        int numSubjects;
        int[] marks;
        int totalMarks = 0;
        double averagePercentage;
        String grade;

        System.out.print("Enter the number of subjects: ");
        numSubjects = scanner.nextInt();

        marks = new int[numSubjects];

        for (int i = 0; i < numSubjects; i++) {
            System.out.print("Enter marks obtained in subject " + (i + 1) + " (out of 100): ");
            marks[i] = scanner.nextInt();

            while (marks[i] < 0 || marks[i] > 100) {
                System.out.println("Invalid input! Marks should be between 0 and 100. Please enter again: ");
                marks[i] = scanner.nextInt();
            }

            totalMarks += marks[i];
        }

        averagePercentage = (double) totalMarks / numSubjects;

        grade = calculateGrade(averagePercentage, GRADE_THRESHOLDS, GRADE_LABELS);

        System.out.println("\nResults:");
        System.out.println("Total Marks: " + totalMarks);
        System.out.println("Average Percentage: " + averagePercentage + "%");
        System.out.println("Grade: " + grade);

        scanner.close();
    }

    public static String calculateGrade(double averagePercentage, double[] thresholds, String[] labels) {
        String grade = "F";

        for (int i = 0; i < thresholds.length; i++) {
            if (averagePercentage >= thresholds[i]) {
                grade = labels[i];
                break;
            }
        }

        return grade;
    }
}
