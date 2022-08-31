package mypattern.mycallback.sync;

/**
 * 回调测试
 * 回调的核心就是回调方将本身即this传递给调用方
 * 有多种数据需要处理且数据有主次之分，使用回调会是一种更加合适的选择，优先处理的数据放在回调方法中先处理掉。
 */

public class CallBackTest {
    public void testCallback() {
        Student student = new Ricky();
        Teacher teacher = new Teacher(student);
        
        teacher.askQuestion();
    }
    
}
