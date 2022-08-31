package mypattern.mystate;

/**
 * 奖品发放完毕状态
 * 说明，当我们 activity 改变成 DispenseOutState， 抽奖活动结束
 * @author zhaozhaohai
 *
 */
public class DispenseOutState4 extends State {

    // 初始化时传入活动引用 
    RaffleActivity activity;
    public DispenseOutState4(RaffleActivity activity) { 
        this.activity = activity;
    }
    @Override
    public void deductMoney() {
        System.out.println("奖品发送完了，请下次再参加"); 
    }

    @Override
    public boolean raffle() {
        System.out.println("奖品发送完了，请下次再参加");
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void dispensePrize() {
        // TODO Auto-generated method stub
        System.out.println("奖品发送完了，请下次再参加");
    }

}