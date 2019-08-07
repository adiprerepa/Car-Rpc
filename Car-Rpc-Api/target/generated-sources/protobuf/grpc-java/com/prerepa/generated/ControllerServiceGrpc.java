package com.prerepa.generated;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 * <pre>
 * Main Service
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.20.0)",
    comments = "Source: controller_proto/Controller_Interface.proto")
public final class ControllerServiceGrpc {

  private ControllerServiceGrpc() {}

  public static final String SERVICE_NAME = "controller.ControllerService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.prerepa.generated.ControlRequest,
      com.prerepa.generated.ControlResponse> getControlServiceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ControlService",
      requestType = com.prerepa.generated.ControlRequest.class,
      responseType = com.prerepa.generated.ControlResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.prerepa.generated.ControlRequest,
      com.prerepa.generated.ControlResponse> getControlServiceMethod() {
    io.grpc.MethodDescriptor<com.prerepa.generated.ControlRequest, com.prerepa.generated.ControlResponse> getControlServiceMethod;
    if ((getControlServiceMethod = ControllerServiceGrpc.getControlServiceMethod) == null) {
      synchronized (ControllerServiceGrpc.class) {
        if ((getControlServiceMethod = ControllerServiceGrpc.getControlServiceMethod) == null) {
          ControllerServiceGrpc.getControlServiceMethod = getControlServiceMethod = 
              io.grpc.MethodDescriptor.<com.prerepa.generated.ControlRequest, com.prerepa.generated.ControlResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "controller.ControllerService", "ControlService"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.prerepa.generated.ControlRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.prerepa.generated.ControlResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new ControllerServiceMethodDescriptorSupplier("ControlService"))
                  .build();
          }
        }
     }
     return getControlServiceMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.prerepa.generated.Control_Esp8266Address,
      com.prerepa.generated.Control_Esp8266Acknowledge> getControlAcknowledgeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ControlAcknowledge",
      requestType = com.prerepa.generated.Control_Esp8266Address.class,
      responseType = com.prerepa.generated.Control_Esp8266Acknowledge.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.prerepa.generated.Control_Esp8266Address,
      com.prerepa.generated.Control_Esp8266Acknowledge> getControlAcknowledgeMethod() {
    io.grpc.MethodDescriptor<com.prerepa.generated.Control_Esp8266Address, com.prerepa.generated.Control_Esp8266Acknowledge> getControlAcknowledgeMethod;
    if ((getControlAcknowledgeMethod = ControllerServiceGrpc.getControlAcknowledgeMethod) == null) {
      synchronized (ControllerServiceGrpc.class) {
        if ((getControlAcknowledgeMethod = ControllerServiceGrpc.getControlAcknowledgeMethod) == null) {
          ControllerServiceGrpc.getControlAcknowledgeMethod = getControlAcknowledgeMethod = 
              io.grpc.MethodDescriptor.<com.prerepa.generated.Control_Esp8266Address, com.prerepa.generated.Control_Esp8266Acknowledge>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "controller.ControllerService", "ControlAcknowledge"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.prerepa.generated.Control_Esp8266Address.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.prerepa.generated.Control_Esp8266Acknowledge.getDefaultInstance()))
                  .setSchemaDescriptor(new ControllerServiceMethodDescriptorSupplier("ControlAcknowledge"))
                  .build();
          }
        }
     }
     return getControlAcknowledgeMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ControllerServiceStub newStub(io.grpc.Channel channel) {
    return new ControllerServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ControllerServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new ControllerServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ControllerServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new ControllerServiceFutureStub(channel);
  }

  /**
   * <pre>
   * Main Service
   * </pre>
   */
  public static abstract class ControllerServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void controlService(com.prerepa.generated.ControlRequest request,
        io.grpc.stub.StreamObserver<com.prerepa.generated.ControlResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getControlServiceMethod(), responseObserver);
    }

    /**
     */
    public void controlAcknowledge(com.prerepa.generated.Control_Esp8266Address request,
        io.grpc.stub.StreamObserver<com.prerepa.generated.Control_Esp8266Acknowledge> responseObserver) {
      asyncUnimplementedUnaryCall(getControlAcknowledgeMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getControlServiceMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.prerepa.generated.ControlRequest,
                com.prerepa.generated.ControlResponse>(
                  this, METHODID_CONTROL_SERVICE)))
          .addMethod(
            getControlAcknowledgeMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.prerepa.generated.Control_Esp8266Address,
                com.prerepa.generated.Control_Esp8266Acknowledge>(
                  this, METHODID_CONTROL_ACKNOWLEDGE)))
          .build();
    }
  }

  /**
   * <pre>
   * Main Service
   * </pre>
   */
  public static final class ControllerServiceStub extends io.grpc.stub.AbstractStub<ControllerServiceStub> {
    private ControllerServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ControllerServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ControllerServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ControllerServiceStub(channel, callOptions);
    }

    /**
     */
    public void controlService(com.prerepa.generated.ControlRequest request,
        io.grpc.stub.StreamObserver<com.prerepa.generated.ControlResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getControlServiceMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void controlAcknowledge(com.prerepa.generated.Control_Esp8266Address request,
        io.grpc.stub.StreamObserver<com.prerepa.generated.Control_Esp8266Acknowledge> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getControlAcknowledgeMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Main Service
   * </pre>
   */
  public static final class ControllerServiceBlockingStub extends io.grpc.stub.AbstractStub<ControllerServiceBlockingStub> {
    private ControllerServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ControllerServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ControllerServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ControllerServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.prerepa.generated.ControlResponse controlService(com.prerepa.generated.ControlRequest request) {
      return blockingUnaryCall(
          getChannel(), getControlServiceMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.prerepa.generated.Control_Esp8266Acknowledge controlAcknowledge(com.prerepa.generated.Control_Esp8266Address request) {
      return blockingUnaryCall(
          getChannel(), getControlAcknowledgeMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Main Service
   * </pre>
   */
  public static final class ControllerServiceFutureStub extends io.grpc.stub.AbstractStub<ControllerServiceFutureStub> {
    private ControllerServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ControllerServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ControllerServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ControllerServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.prerepa.generated.ControlResponse> controlService(
        com.prerepa.generated.ControlRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getControlServiceMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.prerepa.generated.Control_Esp8266Acknowledge> controlAcknowledge(
        com.prerepa.generated.Control_Esp8266Address request) {
      return futureUnaryCall(
          getChannel().newCall(getControlAcknowledgeMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CONTROL_SERVICE = 0;
  private static final int METHODID_CONTROL_ACKNOWLEDGE = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final ControllerServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(ControllerServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CONTROL_SERVICE:
          serviceImpl.controlService((com.prerepa.generated.ControlRequest) request,
              (io.grpc.stub.StreamObserver<com.prerepa.generated.ControlResponse>) responseObserver);
          break;
        case METHODID_CONTROL_ACKNOWLEDGE:
          serviceImpl.controlAcknowledge((com.prerepa.generated.Control_Esp8266Address) request,
              (io.grpc.stub.StreamObserver<com.prerepa.generated.Control_Esp8266Acknowledge>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class ControllerServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ControllerServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.prerepa.generated.ControllerProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ControllerService");
    }
  }

  private static final class ControllerServiceFileDescriptorSupplier
      extends ControllerServiceBaseDescriptorSupplier {
    ControllerServiceFileDescriptorSupplier() {}
  }

  private static final class ControllerServiceMethodDescriptorSupplier
      extends ControllerServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    ControllerServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (ControllerServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ControllerServiceFileDescriptorSupplier())
              .addMethod(getControlServiceMethod())
              .addMethod(getControlAcknowledgeMethod())
              .build();
        }
      }
    }
    return result;
  }
}
