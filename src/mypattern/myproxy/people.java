package mypattern.myproxy;

class people implements buy_car {

    private int cash;
    private String vip;
    private String username;

    @Override
    public void buy_mycar() {
        // TODO Auto-generated method stub
        System.out.print(username + "��vip �ͻ�������ֱ�ӹ����³���");
    }

    public int getCash() {
        return cash;
    }
    public void setCash(int cash) {
        this.cash = cash;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getVip() {
        return vip;
    }
    public void setVip(String vip) {
        this.vip = vip;
    }
}
