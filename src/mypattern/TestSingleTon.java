package mypattern;

public class TestSingleTon {

    /***
     * Lazy initialization holder classģʽ
                ���ģʽ�ۺ�ʹ����Java���༶�ڲ���Ͷ��߳�ȱʡͬ������֪ʶ���������ͬʱʵ�����ӳټ��غ��̰߳�ȫ��

    1.��Ӧ�Ļ���֪ʶ
            ʲô���༶�ڲ��ࣿ
            �򵥵�˵���༶�ڲ���ָ���ǣ���static���εĳ�Աʽ�ڲ��ࡣ
            ���û��static���εĳ�Աʽ�ڲ��౻��Ϊ�����ڲ��ࡣ
        
            �༶�ڲ����൱�����ⲿ���static�ɷ֣����Ķ������ⲿ�����䲻����������ϵ����˿�ֱ�Ӵ�����
            �������ڲ����ʵ�����ǰ����ⲿ����ʵ���еġ�
        
            �༶�ڲ����У����Զ��徲̬�ķ������ھ�̬������ֻ�ܹ������ⲿ���еľ�̬��Ա�������߳�Ա������
        
            �༶�ڲ����൱�����ⲿ��ĳ�Ա��ֻ���ڵ�һ�α�ʹ�õ�ʱ��ű���װ�ء�
        
            ���߳�ȱʡͬ������֪ʶ
            ��Ҷ�֪�����ڶ��߳̿����У�Ϊ�˽���������⣬��Ҫ��ͨ��ʹ��synchronized���ӻ���������ͬ�����ơ�
            ������ĳЩ����У�JVM�Ѿ�������Ϊ��ִ����ͬ������Щ����¾Ͳ����Լ���������ͬ�������ˡ���Щ���������

    1.�ɾ�̬��ʼ�������ھ�̬�ֶ��ϻ�static{}���еĳ�ʼ��������ʼ������ʱ

    2.����final�ֶ�ʱ

    3.�ڴ����߳�֮ǰ��������ʱ

    4.�߳̿��Կ�������Ҫ����Ķ���ʱ

    2.���������˼·
                Ҫ��ܼ򵥵�ʵ���̰߳�ȫ�����Բ��þ�̬��ʼ�����ķ�ʽ����������JVM����֤�̵߳İ�ȫ�ԡ�����ǰ��Ķ���ʽʵ�ַ�ʽ��
                ��������һ�������ǻ��˷�һ���Ŀռ�����Ϊ����ʵ�ַ�ʽ��������װ�ص�ʱ��ͳ�ʼ�����󣬲������費��Ҫ��
            
                ���������һ�ַ����ܹ�����װ�ص�ʱ��ȥ��ʼ�������ǲ��ͽ�������ˣ�һ�ֿ��еķ�ʽ���ǲ����༶�ڲ��࣬������༶�ڲ�������ȥ��������ʵ����
                ����һ����ֻҪ��ʹ�õ�����༶�ڲ��࣬�ǾͲ��ᴴ������ʵ�����Ӷ�ͬʱʵ���ӳټ��غ��̰߳�ȫ��
            
                ʾ���������£�
    
    public class Singleton {
    
    private Singleton(){}
    /**
     *    �༶���ڲ��࣬Ҳ���Ǿ�̬�ĳ�Աʽ�ڲ��࣬���ڲ����ʵ�����ⲿ���ʵ��
     *    û�а󶨹�ϵ������ֻ�б����õ�ʱ�Ż�װ�أ��Ӷ�ʵ�����ӳټ��ء�
     */
    /*
    private static class SingletonHolder{
     *//**
       * ��̬��ʼ��������JVM����֤�̰߳�ȫ
       */
    /*
    private static Singleton instance = new Singleton();
    }
    
    public static Singleton getInstance(){
    return SingletonHolder.instance;
    }
    }
        ��getInstance������һ�α����õ�ʱ������һ�ζ�ȡSingletonHolder.instance������SingletonHolder��õ���ʼ����
        ���������װ�ز�����ʼ����ʱ�򣬻��ʼ�����ľ�̬�򣬴Ӷ�����Singleton��ʵ���������Ǿ�̬�������ֻ���������װ�����ʱ���ʼ��һ�Σ��������������֤�����̰߳�ȫ�ԡ�
    
        ���ģʽ���������ڣ�getInstance������û�б�ͬ��������ֻ��ִ��һ����ķ��ʣ�����ӳٳ�ʼ����û�������κη��ʳɱ���
    * @author admin
    *
    */

    /*��̬�ڲ���ʵ�ֵ���ģʽ*/
    private static class TestSingleTonHolder {
        static final TestSingleTon INSTANCE = new TestSingleTon();
    }

    public static TestSingleTon getInstance() {
        return TestSingleTonHolder.INSTANCE;
    }

    public static void main(String args[]) {
        TestSingleTon st = TestSingleTon.getInstance();
        TestSingleTon st1 = TestSingleTon.getInstance();
        System.out.format("%s, \n%s", st.hashCode(), st1.hashCode());
    }

}
