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
            System.out.println(people.getUsername() + "�����³������׽�����");
        } else {
            System.out.println(people.getUsername() + "Ǯ�����������򳵣�����������");
        }
    }
}
