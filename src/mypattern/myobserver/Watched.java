package mypattern.myobserver;

import java.util.Observable;

/**
 * 
 * ��Watched.java��ʵ��������
 * ���۲��ߣ��൱���¼��������¼�Դ���¼����������Ϊ���ĵĶ���
 * ��Ҫְ��ע��/�����۲��ߣ�����������������������¼����󣩴��ݸ��۲��ߣ����������������ɸ���Ȥ�Ĺ۲��ߣ���������ִ��
 */
public class Watched extends Observable {

    public void notifyObservers(Object arg) {
        
        /**
         * Ϊ���Ⲣ����ͻ��������changed��־λchanged =true��
         * ��ǰ�߳̿���֪ͨ���й۲��ߣ��ڲ�ͬ��������˻�����Ϊfalse��
         * ֪ͨ�����У�������ע��ĺͳ������޷�֪ͨ����
         */
        super.setChanged();
        /**
         * �¼�������֪ͨ���и���Ȥ�Ĺ۲���
         */
        super.notifyObservers(arg);
       
    }
    
}
