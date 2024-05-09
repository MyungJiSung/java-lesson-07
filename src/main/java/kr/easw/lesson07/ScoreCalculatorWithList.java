package kr.easw.lesson07;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 이전 2개의 예제에서 소개된 List 컬렉션과 제너릭스를 이용해 평균 점수 계산기를 만들어보세요.
 * **반드시** CalculatorImpl 클래스만 수정하여 문제를 풀어야 합니다.
 * 해당 문제는 다음과 같은 제한 사항이 있습니다 :
 * - CalculatorImpl 클래스는 Calculator 인터페이스를 구현해야 합니다.
 * - CalculatorImpl 클래스는 List를 반드시 사용해야 합니다.
 * - 구동시 오류가 발생하지 않아야 합니다.
 * - 입력된 점수가 없을 경우, RuntimeException을 발생시켜야 합니다.
 */
public class ScoreCalculatorWithList {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Calculator calculator = new CalculatorImpl();

        while (true) {
            System.out.println("Enter the subject and score.");
            System.out.print("> ");
            String subject = scanner.next();
            if (subject.equals("exit")) {
                break;
            }
            int score = scanner.nextInt();
            calculator.addScore(subject, score);
        }
        System.out.printf("Subject Counts: %d; Average: %.2f; ", calculator.getSubjectCount(), calculator.getAverage());
    }
    interface Calculator {
        void addScore(String subject, int score);
        double getAverage();
        int getSubjectCount();
    }
    static class CalculatorImpl implements Calculator {
        private final List<Score> scores = new ArrayList<>();
        @Override
        public void addScore(String subject, int score) {       // 점수의 총합
            scores.add(new Score(subject, score));
        }
        @Override
        public double getAverage() {        // 평균 구하는
            if (scores.isEmpty()) {
                throw new RuntimeException("입력된 점수가 없습니다.");
            }
            int totalsum = 0;
            for (Score s : scores) {        // scores 리스트를 순회
                totalsum += s.getScore();
            }
            return (double) totalsum / scores.size();
        }
        @Override
        public int getSubjectCount() {      // 과목의 개수를 카운트
            return scores.size();
        }
    }
    static class Score {
        private final String subject;
        private final int score;
        public Score(String subject, int score) {
            this.subject = subject;
            this.score = score;
        }
        public String getSubject() {
            return subject;
        }
        public int getScore() {
            return score;
        }
    }
}
