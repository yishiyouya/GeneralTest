package mypattern.mystate;

/**
 * 1���ڲ��仯״̬���ۻ��ֺ�齱���н��󷢽�Ʒ��
 *
 */
public class ClientTest {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        // ��������󣬽�Ʒ�� 1 ����Ʒ
        RaffleActivity activity = new RaffleActivity(1000, 1);
        // ���������� 300 �ν� 
        for (int i = 0; i < 30; i++) {
            
            System.out.println("--------��" + (i + 1) + "�γ齱----------"); 
            // 1���μӳ齱����һ������۳�����
            activity.debuctMoney();
            // �ڶ����齱
            activity.raffle();
        }
    }

}
