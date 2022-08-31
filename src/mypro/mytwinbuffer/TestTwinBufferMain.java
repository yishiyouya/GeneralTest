package mypro.mytwinbuffer;

public class TestTwinBufferMain {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        long start = System.currentTimeMillis();
        for (int i = 0; i < 320000; i++) {
            TestTwinBufferService.getInstance().put(i);
        }
        long stop = System.currentTimeMillis();
        System.out.println("ok"+(stop-start));
    }

}
