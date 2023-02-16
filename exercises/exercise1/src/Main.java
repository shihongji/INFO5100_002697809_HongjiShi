import java.util.Arrays;

// Create a class hierarchy with base class as Student
class Student {
    protected String name;
    private int[] quizScores;

//    The constructor
    public Student(String name) {
        this.name = name;
        quizScores = new int[15];
    }
// Use array to hold 15 quizzes scores for each student
    public void setQuizScores(int[] quizScores) {
        this.quizScores = quizScores;
    }
//    Calculate average quiz scores for the student object.
    public double getAvgScore() {
        double sum = 0.0;
        double avgScore = 0.0;
        for (int quizScore : quizScores) {
            sum += quizScore;
        }
        avgScore = sum/quizScores.length;
        return avgScore;
    }
//    Print the quiz scores for the student object in ascending order.
    public String printScore() {
        // 1. sort the scores
        // 2. print the scores
        int[] sortedScores = Arrays.copyOf(quizScores, quizScores.length);
        Arrays.sort(sortedScores);
        return Arrays.toString(sortedScores);
    }

//   return the name of the student
    @Override
    public String toString() {
        return name;
    }
}
// Derived classes are PartTimeStudent and FullTime
class PartTimeStudent extends Student {

    public PartTimeStudent(String name) {
        super(name);
    }
}
class FullTimeStudent extends Student {
//    Full-time students use Array to hold two Exam scores
    private int[] extraExam = new int[2];

    public FullTimeStudent(String name, int[] extraExam) {
        super(name);
        this.extraExam = extraExam;
    }
//    return the exam scores of the FullTimeStudent

    @Override
    public String toString() {
        return this.name + " : " + Arrays.toString(extraExam);
    }
}
class Session {
//    Use array to hold 20 students in a Session
    public Student[] students = new Student[20];
}
public class Main {
    public static void main(String[] args) {
        Session session = new Session();
        int[] statusRandom = new int[20];
        for (int i=0; i<20; i++) {
            statusRandom[i] = (int)(Math.random()*20) +1;
//            System.out.println(statusRandom[i]);
        }
//        Make sure that at least 1 part-time student is in session.
        session.students[0] = new PartTimeStudent("HongjiPT");
        for (int i=1; i<20; i++) {
            if (statusRandom[i] % 3 ==0) {
                session.students[i] = new PartTimeStudent("PTstu"+i);
            } else {
                session.students[i] = new FullTimeStudent("FTstr"+i,new int[] {(int)(Math.random()*100+1), (int)(Math.random()*100+1)});
            }
        }
//        Set quiz scores of all students

        for (int i=0; i<20; i++) {
            int[] quizScores = new int[15];
            for (int j = 0; j <15 ; j++) {
                quizScores[j] = (int)(Math.random()*100) +1;
            }
            session.students[i].setQuizScores(quizScores);
        }
//        average quiz scores per student
        int[] avgScores = new int[20];
        for (int i = 0; i <20 ; i++) {
            avgScores[i] = (int)session.students[i].getAvgScore();
        }
        System.out.println("\nThe average quiz scores per student for the whole class are: \n"+ Arrays.toString(avgScores));

//        print the list of quiz scores in ascending order
        System.out.println("\nPrint the list of quiz scores in ascending order: ");
        for (int i = 0; i < 20; i++) {
            System.out.println(session.students[i].printScore());
        }

//        names of part-time students
        System.out.println("\nThe name of the part-time student is : ");
        for (int i = 0; i < 20; i++) {
            if (session.students[i] instanceof PartTimeStudent) {
                System.out.print(session.students[i].toString() + ", ");
            }
        }

//        exam scores of full-time students
        System.out.println("\n\nThe exam score of full-time student is: ");
        for (int i = 0; i < 20; i++) {
            if (session.students[i] instanceof FullTimeStudent) {
                System.out.println(session.students[i].toString());
            }
        }
    }
}