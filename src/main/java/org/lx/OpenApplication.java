package org.lx;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class OpenApplication {

    public static void main(String[] args) {
        SpringApplication.run(OpenApplication.class, args);
    }

//    @Bean
//    public Tracer tracer() {
//        JaegerGrpcSpanExporter exporter = JaegerGrpcSpanExporter.builder()
//                .setServiceName("my-service")
//                .setEndpoint("http://localhost:14250") // Jaeger Agent 默认端口
//                .build();
//
//        SdkTracerProvider tracerProvider = SdkTracerProvider.builder()
//                .setResource(Resource.create(Attributes.builder().put(SemanticAttributes.SERVICE_NAME, "my-service").build()))
//                .addSpanProcessor(BatchSpanProcessor.builder(exporter).build())
//                .build();
//        GlobalOpenTelemetry.set(tracerProvider);
//
//        return GlobalOpenTelemetry.getTracer("my-tracer");
//    }
}
