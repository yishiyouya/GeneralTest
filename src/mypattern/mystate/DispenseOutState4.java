package mypattern.mystate;

/**
 * ��Ʒ�������״̬
 * ˵���������� activity �ı�� DispenseOutState�� �齱�����
 * @author zhaozhaohai
 *
 */
public class DispenseOutState4 extends State {

    // ��ʼ��ʱ�������� 
    RaffleActivity activity;
    public DispenseOutState4(RaffleActivity activity) { 
        this.activity = activity;
    }
    @Override
    public void deductMoney() {
        System.out.println("��Ʒ�������ˣ����´��ٲμ�"); 
    }

    @Override
    public boolean raffle() {
        System.out.println("��Ʒ�������ˣ����´��ٲμ�");
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void dispensePrize() {
        // TODO Auto-generated method stub
        System.out.println("��Ʒ�������ˣ����´��ٲμ�");
    }

}