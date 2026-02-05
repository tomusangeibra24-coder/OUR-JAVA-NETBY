import java.util.Scanner;

public class GradeFiveStudents {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int count = 1;
        int score;
        int grade;

        int[] gradeCount = new int[10]; // index 1 to 9

        while (count <= 5) {
            System.out.print("Enter score for student " + count + ": ");
            score = input.nextInt();

            if (score >= 80 && score <= 100) {
                grade = 1;
            } else if (score >= 75) {
                grade = 2;
            } else if (score >= 66) {
                grade = 3;
            } else if (score >= 60) {
                grade = 4;
            } else if (score >= 50) {
                grade = 5;
            } else if (score >= 45) {
                grade = 6;
            } else if (score >= 35) {
                grade = 7;
            } else if (score >= 30) {
                grade = 8;
            } else {
                grade = 9;
            }

            gradeCount[grade]++;
            count++;
        }

        System.out.println("\n--- Grade Summary ---");
        for (int i = 1; i <= 9; i++) {
            System.out.println("Grade " + i + ": " + gradeCount[i] + " student(s)");
        }
    }
}