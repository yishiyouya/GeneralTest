package mypattern.mycallback.sync;

/**
 * 回调者
 * 一个名叫Ricky的同学解决老师提出的问题
 */
public class Ricky implements Student {

    @Override
    public void resolveQuestion(CallBack callback) {
        // 模拟解决问题
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            
        }
        
        // 回调，告诉老师作业答案
        callback.tellAnswer(3);
    }

}
