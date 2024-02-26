package hello.advenced.app.v4;

import hello.advenced.trace.TraceStatus;
import hello.advenced.trace.logtrace.LogTrace;
import hello.advenced.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV4 {
    private final LogTrace trace;

    public void save(String itemId){

        AbstractTemplate<Void> template = new AbstractTemplate<>(trace) {
            @Override
            protected Void call() {
                if(itemId.equals("ex")) {
                    throw new IllegalStateException("예외 발생!");
                }
                sleep(1000);
                return null;
            }
        };
        template.execute("OrderRepository.save()");

//        TraceStatus status = null;
//        try{
//            status = trace.begin("OrderRepository.save()");
//            //저장로직
//            if(itemId.equals("ex")) {
//                throw new IllegalStateException("예외 발생!");
//            }
//            sleep(1000);
//
//            trace.end(status);
//        }catch(Exception e){
//            trace.exception(status, e);
//            throw e;
//        }
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}