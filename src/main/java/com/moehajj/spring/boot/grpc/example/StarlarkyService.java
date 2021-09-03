package com.moehajj.spring.boot.grpc.example;

import com.moehajj.starlarky.example.StarlarkyServiceExampleGrpc;
import com.moehajj.starlarky.example.StarlarkyReplyExample;
import com.moehajj.starlarky.example.StarlarkyRequestExample;
import com.verygood.security.larkyapi.LarkyRuntime;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;

@GRpcService
public class StarlarkyService extends StarlarkyServiceExampleGrpc.StarlarkyServiceExampleImplBase {

    private final String INPUT_BINDING_KEY = "script_input";
    private final String OUTPUT_BINDING_KEY = "script_output";
    private final String FUNCTION_HANDLER = "handle";
    private final String INVOKER = String.format("%s = %s(%s)",
        OUTPUT_BINDING_KEY, FUNCTION_HANDLER, INPUT_BINDING_KEY);

    @Override
    public void compute(StarlarkyRequestExample request, StreamObserver<StarlarkyReplyExample> responseObserver) {

        // Extract input from request
        String script = String.format("%s\n%s", request.getScript(), INVOKER);
        String input = request.getInput();

        // Create Engine
        final LarkyRuntime engine = new LarkyRuntime();

        // Set script input bindings & context

        try {
            // Invoke Engine to Execute Script
            String output = "script output here";
            StarlarkyReplyExample reply = StarlarkyReplyExample.newBuilder().setOutput(output).build();

            responseObserver.onNext(reply);
            responseObserver.onCompleted();
        } catch(Exception e) {
            responseObserver.onError(Status.INTERNAL.withDescription(e.getMessage()).asException());
        }
    }

}
