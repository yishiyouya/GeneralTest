package mypattern.mystate;

public class RaffleActivity {
    // state 表示活动当前的状态，是变化 
    State state = null;
    
    int integral = 0;
    // 奖品数量 
    int count = 0;
    // 四个属性，表示四种状态
    State noRafflleState = new NoRaffleState1(this); 
    State canRaffleState = new CanRaffleState2(this);
    State dispenseState = new DispenseState3(this); 
    State dispensOutState = new DispenseOutState4(this);
    //构造器
    //1. 初始化当前的状态为 noRafflleState(即不能抽奖的状态) 
    //2. 初始化奖品的数量
    public RaffleActivity(int integral, int count) {
        this.state = getNoRafflleState();
        this.integral = integral;
        this.count = count; 
    }
    
    //扣分, 调用当前状态的 deductMoney 
    public void debuctMoney(){
        state.deductMoney(); 
    }
    //抽奖
    public void raffle(){
        // 如果当前的状态是抽奖成功
        if(state.raffle()){ 
            //领取奖品
            state.dispensePrize(); 
        }
    }
    public State getState() { 
        return state;
    }
    public void setState(State state) { 
        this.state = state;
    }

    public int getIntegral() {
        return integral;
    }

    public void decrIntegral(int integral) {
        this.integral -= integral;
    }
    
    public void setIntegral(int integral) {
        this.integral = integral;
    }
    
    //这里请大家注意，每领取一次奖品，count-- 
    public int getCount() {
        int curCount = count; 
        count--;
        return curCount; 
    }
    public void setCount(int count) { 
        this.count = count;
    }
    
    public State getNoRafflleState() { 
        return noRafflleState;
    }
    public void setNoRafflleState(State noRafflleState) { 
        this.noRafflleState = noRafflleState;
    }
    public State getCanRaffleState() { 
        return canRaffleState;
    }
    public void setCanRaffleState(State canRaffleState) { 
        this.canRaffleState = canRaffleState;
    }
    public State getDispenseState() { 
        return dispenseState;
    }
    public void setDispenseState(State dispenseState) { 
        this.dispenseState = dispenseState;
    }
    public State getDispensOutState() { 
        return dispensOutState;
    }
    public void setDispensOutState(State dispensOutState) { 
        this.dispensOutState = dispensOutState;
    }   
}
