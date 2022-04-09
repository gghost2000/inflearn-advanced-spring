package hello.advanced.app.v1;


import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.hellotrace.HelloTraceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV1 {

    private final HelloTraceV1 trace;

    public void save(String itemId) {

        TraceStatus status = null;
        try {
            status = trace.begin("OrderRepositoryV.save()");

            if (itemId.equals("ex")) {
                throw new IllegalStateException("예외 발생!");
            }

            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }

        sleep(1000);
    }

    private void sleep(int milils) {
        try {
            Thread.sleep(milils);
        }  catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
