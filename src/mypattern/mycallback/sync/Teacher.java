package mypattern.mycallback.sync;

/**
 * ��ʦ����
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
        System.out.println("֪���ˣ���Ĵ���" + answer);
    }
    
}