package mypattern.mycallback.sync;

/**
 * �ص���
 * һ������Ricky��ͬѧ�����ʦ���������
 */
public class Ricky implements Student {

    @Override
    public void resolveQuestion(CallBack callback) {
        // ģ��������
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            
        }
        
        // �ص���������ʦ��ҵ��
        callback.tellAnswer(3);
    }

}
