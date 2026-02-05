import java.util.Scanner;

public class GradeSingleStudent {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int score;
        int grade = 0;
        String remark = "";

        System.out.print("Enter student's score out of 100: ");
        score = input.nextInt();

        if (score >= 80 && score <= 100) {
            grade = 1;
            remark = "D1";
        } else if (score >= 75) {
            grade = 2;
            remark = "D2";
        } else if (score >= 66) {
            grade = 3;
            remark = "C3";
        } else if (score >= 60) {
            grade = 4;
            remark = "C4";
        } else if (score >= 50) {
            grade = 5;
            remark = "C5";
        } else if (score >= 45) {
            grade = 6;
            remark = "C6";
        } else if (score >= 35) {
            grade = 7;
            remark = "P7";
        } else if (score >= 30) {
            grade = 8;
            remark = "P8";
        } else if (score >= 0) {
            grade = 9;
            remark = "F";
        } else {
            System.out.println("Invalid score entered");
            return;
        }

        System.out.println("\n--- Student Result ---");
        System.out.println("Score: " + score);
        System.out.println("Grade: " + grade);
        System.out.println("Remark: " + remark);
    }
}
