package mypattern.mystate;

public class NoRaffleState1 extends State {

    // ��ʼ��ʱ�������ã��۳����ֺ�ı���״̬ 
    RaffleActivity activity;
    public NoRaffleState1(RaffleActivity activity) { 
        this.activity = activity;
    }
    // ��ǰ״̬���Կۻ��� , �۳��󣬽�״̬���óɿ��Գ齱״̬ 
    @Override
    public void deductMoney() {
        if (activity.getIntegral() >= 50) {
            activity.decrIntegral(50);
            System.out.println("�۳� 50 ���ֳɹ��������Գ齱��");
            activity.setState(activity.getCanRaffleState()); 
        } else {
            System.out.println("���ֲ��㣬�������Գ齱��");
        }
    }

    // ��ǰ״̬���ܳ齱 
    @Override
    public boolean raffle() {
        System.out.println("���˻��ֲ��ܳ齱�!");
        return false; 
    }
    // ��ǰ״̬���ܷ���Ʒ 
    @Override
    public void dispensePrize() {
        System.out.println("���ܷ��Ž�Ʒ"); 
    }

}
