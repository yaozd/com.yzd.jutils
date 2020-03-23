package com.yzd.jutils.OpenTracing;

import io.opentracing.*;
import io.opentracing.propagation.Format;
import io.opentracing.util.GlobalTracer;
import org.junit.Test;

public class _MainTest {
    /**
     * https://github.com/dougEfresh/opentracing-netty
     */
    @Test
    public void test(){

      /*  Tracer tracer= GlobalTracer.get();
        SpanContext extractedContext = tracer.extract(Format.Builtin.HTTP_HEADERS,
                new HttpServletRequestExtractAdapter());*/
    }

    private class HttpServletRequestExtractAdapter {
        public HttpServletRequestExtractAdapter(Object p0) {
        }
    }
}
