package mypro.mydisruptor;

import java.util.concurrent.ThreadFactory;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.ExceptionHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

public class DisruptorTest {
    /**
     * ��Ϣ�¼���
     */
    public static class MessageEvent{
        /**
         * ԭʼ��Ϣ
         */
        private String message;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    /**
     * ��Ϣ�¼�������
     */
    public static class MessageEventFactory implements EventFactory<MessageEvent>{
        @Override
        public MessageEvent newInstance() {
            return new MessageEvent();
        }
    }

    /**
     * ��Ϣת���࣬������Ϣת��Ϊ�¼�
     */
    public static class MessageEventTranslator implements EventTranslatorOneArg<MessageEvent,String> {
        @Override
        public void translateTo(MessageEvent messageEvent, long l, String s) {
            messageEvent.setMessage(s);
        }
    }

    /**
     * �������̹߳�����
     */
    public static class MessageThreadFactory implements ThreadFactory{
        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r,"Simple Disruptor Test Thread");
        }
    }

    /**
     * ��Ϣ�¼������࣬����ֻ��ӡ��Ϣ
     */
    public static class MessageEventHandler implements EventHandler<MessageEvent>{
        @Override
        public void onEvent(MessageEvent messageEvent, long l, boolean b) throws Exception {
            System.out.println(messageEvent.getMessage());
        }
    }

    /**
     * �쳣������
     */
    public static class MessageExceptionHandler implements ExceptionHandler<MessageEvent>{
        @Override
        public void handleEventException(Throwable ex, long sequence, MessageEvent event) {
            ex.printStackTrace();
        }

        @Override
        public void handleOnStartException(Throwable ex) {
            ex.printStackTrace();

        }

        @Override
        public void handleOnShutdownException(Throwable ex) {
            ex.printStackTrace();

        }
    }

    /**
     * ��Ϣ��������
     */
    public static class MessageEventProducer{
        private RingBuffer<MessageEvent> ringBuffer;

        public MessageEventProducer(RingBuffer<MessageEvent> ringBuffer) {
            this.ringBuffer = ringBuffer;
        }

        /**
         * �����յ�����Ϣ�����ringBuffer
         * @param message
         */
        public void onData(String message){
            EventTranslatorOneArg<MessageEvent,String> translator = new MessageEventTranslator();
            ringBuffer.publishEvent(translator,message);
        }
    }

    public static void main(String[] args) {
        String message = "Hello Disruptor!";
        int ringBufferSize = 1024;//������2��N�η�
        Disruptor<MessageEvent> disruptor = new Disruptor<MessageEvent>(new MessageEventFactory(),ringBufferSize,new MessageThreadFactory(),ProducerType.SINGLE,new BlockingWaitStrategy());
        disruptor.handleEventsWith(new MessageEventHandler());
        disruptor.setDefaultExceptionHandler(new MessageExceptionHandler());
        RingBuffer<MessageEvent> ringBuffer = disruptor.start();
        MessageEventProducer producer = new MessageEventProducer(ringBuffer);
        producer.onData(message);
    }
}
