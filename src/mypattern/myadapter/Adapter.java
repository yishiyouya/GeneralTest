package mypattern.myadapter;


//�������ֱ࣬�ӹ����������࣬ͬʱʵ�ֱ�׼�ӿ�
class Adapter implements Target{
 // ֱ�ӹ�����������
 private Adaptee adaptee;
 
 // ����ͨ�����캯�����������Ҫ����ı����������
 public Adapter (Adaptee adaptee) {
     this.adaptee = adaptee;
 }
 
 public void request() {
     // ������ʹ��ί�еķ�ʽ������⹦��
     this.adaptee.specificRequest();
 }
}
