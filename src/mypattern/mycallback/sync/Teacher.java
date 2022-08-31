package mypattern.mycallback.sync;

/**
 * 老师对象
 */
public class Teacher implements CallBack {

    private Student student;
    
    public Teacher(Student student) {
        this.student = student;
    }
    
    public void askQuestion() {
        student.resolveQuestion(this);
    }
    
    @Override
    public void tellAnswer(int answer) {
        System.out.println("知道了，你的答案是" + answer);
    }
    
}