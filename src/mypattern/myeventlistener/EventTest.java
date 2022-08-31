package mypattern.myeventlistener;

/**
 * �¼�����ģʽ
 * ʲô���¼�������
 * �������������Լ�����Ȥ���¼�һ�����¼���������ı䣬�����õ�֪ͨ��������Ӧ�����磺android�����е�Button�¼���

 * java���¼��������ƿɸ���Ϊ3�㣺

 * java���¼����������漰�� �¼�Դ���¼����������¼����� ���������
 * �¼�Դ����������б�
 * �¼�������һ���ǽӿڣ�����Լ�����÷�ʽ���¼�Դ�����Ϸ�������ʱ������������¼���������һ�����������ڵ��ø÷���ʱ�����¼������ȥ
 * �¼�������ʵ����,ͨ�����ɿ�����Ա��д��������Աͨ���¼������õ��¼�Դ���Ӷ����¼�Դ�ϵĲ������д���
 * �¼�������¼�Դ�ϵĲ������д���
 *
 */
public class EventTest {
    
    public static void main(String[] args) {
        
        EventSource eventSource = new EventSource();

        EventObject event = new EventObject("closeWindows");
        EventListener eventListener = new CloseEventListener(event);
        eventSource.addListener(eventListener);

        /*
         * ����openWindows�¼���֪ͨlistener���¼���������
         * ��open�¼�����Ȥ��listener����ִ��
         *
         */
        eventSource.notifyListenerEvents(event);
        
    }
}
