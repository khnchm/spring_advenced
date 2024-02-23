package hello.advenced.app.v3;

import hello.advenced.trace.TraceStatus;
import hello.advenced.trace.hellotrace.HelloTraceV2;
import hello.advenced.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV3 {
    private final OrderServiceV3 orderService;
    private final LogTrace trace;

    @GetMapping("/v3/request")
    public String request(@RequestParam(name="itemId") String itemId){
        TraceStatus status = null;
        try{
            status = trace.begin("OrderController.request()");
            orderService.orderItem(itemId);
            trace.end(status);
            return "ok";
        }catch(Exception e){
            trace.exception(status, e);
            throw e;
        }
    }
}
