package mypattern.myadapter;

//������
public class Client {
    public static void main(String[] args) {
        // ʹ����ͨ������
        Target concreteTarget = new ConcreteTarget();
        concreteTarget.request();

        // ʹ�����⹦���࣬�������࣬
        // ��Ҫ�ȴ���һ����������Ķ�����Ϊ����
        Target adapter = new Adapter(new Adaptee());
        adapter.request();
    }
}
