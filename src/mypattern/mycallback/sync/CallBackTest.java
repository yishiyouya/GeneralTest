package mypattern.mycallback.sync;

/**
 * �ص�����
 * �ص��ĺ��ľ��ǻص���������this���ݸ����÷�
 * �ж���������Ҫ����������������֮�֣�ʹ�ûص�����һ�ָ��Ӻ��ʵ�ѡ�����ȴ�������ݷ��ڻص��������ȴ������
 */

public class CallBackTest {
    public void testCallback() {
        Student student = new Ricky();
        Teacher teacher = new Teacher(student);
        
        teacher.askQuestion();
    }
    
}
