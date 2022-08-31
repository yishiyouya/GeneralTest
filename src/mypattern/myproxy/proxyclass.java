package mypattern.myproxy;

class proxyclass implements buy_car {

    private people people;
    public people getPeople() {
        return people;
    }
    public void setPeople(people people) {
        this.people = people;
    }

    @Override
    public void buy_mycar() {
        // TODO Auto-generated method stub

        if (people.getVip() == "vip") {
            people.buy_mycar();
            return;
        }
        if (people.getCash() >= 50000) {
            System.out.println(people.getUsername() + "买了新车，交易结束！");
        } else {
            System.out.println(people.getUsername() + "钱不够，不能买车，继续比赛！");
        }
    }
}
