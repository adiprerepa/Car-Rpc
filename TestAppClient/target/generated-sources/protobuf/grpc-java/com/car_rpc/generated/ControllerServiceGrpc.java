package com.car_rpc.generated;

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
 * Main Service: Communcation between phone and API
 * ControlAcknowledge: Acknowledge a connection between the chip, phone, and API. Once
 * 	this rpc is called, the server takes the host and port given by the app and tries to make
 * 	a connection. If sucessful, the Socket will be stored in a ValueStore and later
 * 	accessed by the {&#64;link ControlService} rpc, where the request contains the key, for accessing the socket.
 * ControlService: The main rpc that sends commands to the server, and recieves metrics after these commands
 * 	are carried out by the esp8266. The command is a three-dimentional vector, and the response contains metrics
 * 	that are displayed on the app.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.20.0)",
    comments = "Source: Controller_Interface.proto")
public final class ControllerServiceGrpc {

  private ControllerServiceGrpc() {}

  public static final String SERVICE_NAME = "controller.ControllerService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.car_rpc.generated.ControlAcknowledge,
      com.car_rpc.generated.ControlAcknowledgeResponse> getControlAcknowledgeServiceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ControlAcknowledgeService",
      requestType = com.car_rpc.generated.ControlAcknowledge.class,
      responseType = com.car_rpc.generated.ControlAcknowledgeResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.car_rpc.generated.ControlAcknowledge,
      com.car_rpc.generated.ControlAcknowledgeResponse> getControlAcknowledgeServiceMethod() {
    io.grpc.MethodDescriptor<com.car_rpc.generated.ControlAcknowledge, com.car_rpc.generated.ControlAcknowledgeResponse> getControlAcknowledgeServiceMethod;
    if ((getControlAcknowledgeServiceMethod = ControllerServiceGrpc.getControlAcknowledgeServiceMethod) == null) {
      synchronized (ControllerServiceGrpc.class) {
        if ((getControlAcknowledgeServiceMethod = ControllerServiceGrpc.getControlAcknowledgeServiceMethod) == null) {
          ControllerServiceGrpc.getControlAcknowledgeServiceMethod = getControlAcknowledgeServiceMethod = 
              io.grpc.MethodDescriptor.<com.car_rpc.generated.ControlAcknowledge, com.car_rpc.generated.ControlAcknowledgeResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "controller.ControllerService", "ControlAcknowledgeService"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.car_rpc.generated.ControlAcknowledge.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.car_rpc.generated.ControlAcknowledgeResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new ControllerServiceMethodDescriptorSupplier("ControlAcknowledgeService"))
                  .build();
          }
        }
     }
     return getControlAcknowledgeServiceMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.car_rpc.generated.ServerAcknowledge,
      com.car_rpc.generated.ServerAcknowledgeResponse> getServerAcknowledgeServiceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ServerAcknowledgeService",
      requestType = com.car_rpc.generated.ServerAcknowledge.class,
      responseType = com.car_rpc.generated.ServerAcknowledgeResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.car_rpc.generated.ServerAcknowledge,
      com.car_rpc.generated.ServerAcknowledgeResponse> getServerAcknowledgeServiceMethod() {
    io.grpc.MethodDescriptor<com.car_rpc.generated.ServerAcknowledge, com.car_rpc.generated.ServerAcknowledgeResponse> getServerAcknowledgeServiceMethod;
    if ((getServerAcknowledgeServiceMethod = ControllerServiceGrpc.getServerAcknowledgeServiceMethod) == null) {
      synchronized (ControllerServiceGrpc.class) {
        if ((getServerAcknowledgeServiceMethod = ControllerServiceGrpc.getServerAcknowledgeServiceMethod) == null) {
          ControllerServiceGrpc.getServerAcknowledgeServiceMethod = getServerAcknowledgeServiceMethod = 
              io.grpc.MethodDescriptor.<com.car_rpc.generated.ServerAcknowledge, com.car_rpc.generated.ServerAcknowledgeResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "controller.ControllerService", "ServerAcknowledgeService"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.car_rpc.generated.ServerAcknowledge.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.car_rpc.generated.ServerAcknowledgeResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new ControllerServiceMethodDescriptorSupplier("ServerAcknowledgeService"))
                  .build();
          }
        }
     }
     return getServerAcknowledgeServiceMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.car_rpc.generated.Esp8266DiscoveryRequest,
      com.car_rpc.generated.Esp8266DiscoveryResponse> getDiscoveryServiceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DiscoveryService",
      requestType = com.car_rpc.generated.Esp8266DiscoveryRequest.class,
      responseType = com.car_rpc.generated.Esp8266DiscoveryResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.car_rpc.generated.Esp8266DiscoveryRequest,
      com.car_rpc.generated.Esp8266DiscoveryResponse> getDiscoveryServiceMethod() {
    io.grpc.MethodDescriptor<com.car_rpc.generated.Esp8266DiscoveryRequest, com.car_rpc.generated.Esp8266DiscoveryResponse> getDiscoveryServiceMethod;
    if ((getDiscoveryServiceMethod = ControllerServiceGrpc.getDiscoveryServiceMethod) == null) {
      synchronized (ControllerServiceGrpc.class) {
        if ((getDiscoveryServiceMethod = ControllerServiceGrpc.getDiscoveryServiceMethod) == null) {
          ControllerServiceGrpc.getDiscoveryServiceMethod = getDiscoveryServiceMethod = 
              io.grpc.MethodDescriptor.<com.car_rpc.generated.Esp8266DiscoveryRequest, com.car_rpc.generated.Esp8266DiscoveryResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "controller.ControllerService", "DiscoveryService"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.car_rpc.generated.Esp8266DiscoveryRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.car_rpc.generated.Esp8266DiscoveryResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new ControllerServiceMethodDescriptorSupplier("DiscoveryService"))
                  .build();
          }
        }
     }
     return getDiscoveryServiceMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.car_rpc.generated.ControlRequest,
      com.car_rpc.generated.ControlResponse> getControlCommandServiceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ControlCommandService",
      requestType = com.car_rpc.generated.ControlRequest.class,
      responseType = com.car_rpc.generated.ControlResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.car_rpc.generated.ControlRequest,
      com.car_rpc.generated.ControlResponse> getControlCommandServiceMethod() {
    io.grpc.MethodDescriptor<com.car_rpc.generated.ControlRequest, com.car_rpc.generated.ControlResponse> getControlCommandServiceMethod;
    if ((getControlCommandServiceMethod = ControllerServiceGrpc.getControlCommandServiceMethod) == null) {
      synchronized (ControllerServiceGrpc.class) {
        if ((getControlCommandServiceMethod = ControllerServiceGrpc.getControlCommandServiceMethod) == null) {
          ControllerServiceGrpc.getControlCommandServiceMethod = getControlCommandServiceMethod = 
              io.grpc.MethodDescriptor.<com.car_rpc.generated.ControlRequest, com.car_rpc.generated.ControlResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "controller.ControllerService", "ControlCommandService"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.car_rpc.generated.ControlRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.car_rpc.generated.ControlResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new ControllerServiceMethodDescriptorSupplier("ControlCommandService"))
                  .build();
          }
        }
     }
     return getControlCommandServiceMethod;
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
   * Main Service: Communcation between phone and API
   * ControlAcknowledge: Acknowledge a connection between the chip, phone, and API. Once
   * 	this rpc is called, the server takes the host and port given by the app and tries to make
   * 	a connection. If sucessful, the Socket will be stored in a ValueStore and later
   * 	accessed by the {&#64;link ControlService} rpc, where the request contains the key, for accessing the socket.
   * ControlService: The main rpc that sends commands to the server, and recieves metrics after these commands
   * 	are carried out by the esp8266. The command is a three-dimentional vector, and the response contains metrics
   * 	that are displayed on the app.
   * </pre>
   */
  public static abstract class ControllerServiceImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Acknowledge RPC - Hard Coded
     * </pre>
     */
    public void controlAcknowledgeService(com.car_rpc.generated.ControlAcknowledge request,
        io.grpc.stub.StreamObserver<com.car_rpc.generated.ControlAcknowledgeResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getControlAcknowledgeServiceMethod(), responseObserver);
    }

    /**
     * <pre>
     * Server Acknowledge RPC
     * </pre>
     */
    public void serverAcknowledgeService(com.car_rpc.generated.ServerAcknowledge request,
        io.grpc.stub.StreamObserver<com.car_rpc.generated.ServerAcknowledgeResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getServerAcknowledgeServiceMethod(), responseObserver);
    }

    /**
     * <pre>
     * mDNS - not started
     * </pre>
     */
    public void discoveryService(com.car_rpc.generated.Esp8266DiscoveryRequest request,
        io.grpc.stub.StreamObserver<com.car_rpc.generated.Esp8266DiscoveryResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDiscoveryServiceMethod(), responseObserver);
    }

    /**
     * <pre>
     * Control RPC
     * </pre>
     */
    public void controlCommandService(com.car_rpc.generated.ControlRequest request,
        io.grpc.stub.StreamObserver<com.car_rpc.generated.ControlResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getControlCommandServiceMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getControlAcknowledgeServiceMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.car_rpc.generated.ControlAcknowledge,
                com.car_rpc.generated.ControlAcknowledgeResponse>(
                  this, METHODID_CONTROL_ACKNOWLEDGE_SERVICE)))
          .addMethod(
            getServerAcknowledgeServiceMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.car_rpc.generated.ServerAcknowledge,
                com.car_rpc.generated.ServerAcknowledgeResponse>(
                  this, METHODID_SERVER_ACKNOWLEDGE_SERVICE)))
          .addMethod(
            getDiscoveryServiceMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.car_rpc.generated.Esp8266DiscoveryRequest,
                com.car_rpc.generated.Esp8266DiscoveryResponse>(
                  this, METHODID_DISCOVERY_SERVICE)))
          .addMethod(
            getControlCommandServiceMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.car_rpc.generated.ControlRequest,
                com.car_rpc.generated.ControlResponse>(
                  this, METHODID_CONTROL_COMMAND_SERVICE)))
          .build();
    }
  }

  /**
   * <pre>
   * Main Service: Communcation between phone and API
   * ControlAcknowledge: Acknowledge a connection between the chip, phone, and API. Once
   * 	this rpc is called, the server takes the host and port given by the app and tries to make
   * 	a connection. If sucessful, the Socket will be stored in a ValueStore and later
   * 	accessed by the {&#64;link ControlService} rpc, where the request contains the key, for accessing the socket.
   * ControlService: The main rpc that sends commands to the server, and recieves metrics after these commands
   * 	are carried out by the esp8266. The command is a three-dimentional vector, and the response contains metrics
   * 	that are displayed on the app.
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
     * <pre>
     * Acknowledge RPC - Hard Coded
     * </pre>
     */
    public void controlAcknowledgeService(com.car_rpc.generated.ControlAcknowledge request,
        io.grpc.stub.StreamObserver<com.car_rpc.generated.ControlAcknowledgeResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getControlAcknowledgeServiceMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Server Acknowledge RPC
     * </pre>
     */
    public void serverAcknowledgeService(com.car_rpc.generated.ServerAcknowledge request,
        io.grpc.stub.StreamObserver<com.car_rpc.generated.ServerAcknowledgeResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getServerAcknowledgeServiceMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * mDNS - not started
     * </pre>
     */
    public void discoveryService(com.car_rpc.generated.Esp8266DiscoveryRequest request,
        io.grpc.stub.StreamObserver<com.car_rpc.generated.Esp8266DiscoveryResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDiscoveryServiceMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Control RPC
     * </pre>
     */
    public void controlCommandService(com.car_rpc.generated.ControlRequest request,
        io.grpc.stub.StreamObserver<com.car_rpc.generated.ControlResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getControlCommandServiceMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Main Service: Communcation between phone and API
   * ControlAcknowledge: Acknowledge a connection between the chip, phone, and API. Once
   * 	this rpc is called, the server takes the host and port given by the app and tries to make
   * 	a connection. If sucessful, the Socket will be stored in a ValueStore and later
   * 	accessed by the {&#64;link ControlService} rpc, where the request contains the key, for accessing the socket.
   * ControlService: The main rpc that sends commands to the server, and recieves metrics after these commands
   * 	are carried out by the esp8266. The command is a three-dimentional vector, and the response contains metrics
   * 	that are displayed on the app.
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
     * <pre>
     * Acknowledge RPC - Hard Coded
     * </pre>
     */
    public com.car_rpc.generated.ControlAcknowledgeResponse controlAcknowledgeService(com.car_rpc.generated.ControlAcknowledge request) {
      return blockingUnaryCall(
          getChannel(), getControlAcknowledgeServiceMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Server Acknowledge RPC
     * </pre>
     */
    public com.car_rpc.generated.ServerAcknowledgeResponse serverAcknowledgeService(com.car_rpc.generated.ServerAcknowledge request) {
      return blockingUnaryCall(
          getChannel(), getServerAcknowledgeServiceMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * mDNS - not started
     * </pre>
     */
    public com.car_rpc.generated.Esp8266DiscoveryResponse discoveryService(com.car_rpc.generated.Esp8266DiscoveryRequest request) {
      return blockingUnaryCall(
          getChannel(), getDiscoveryServiceMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Control RPC
     * </pre>
     */
    public com.car_rpc.generated.ControlResponse controlCommandService(com.car_rpc.generated.ControlRequest request) {
      return blockingUnaryCall(
          getChannel(), getControlCommandServiceMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Main Service: Communcation between phone and API
   * ControlAcknowledge: Acknowledge a connection between the chip, phone, and API. Once
   * 	this rpc is called, the server takes the host and port given by the app and tries to make
   * 	a connection. If sucessful, the Socket will be stored in a ValueStore and later
   * 	accessed by the {&#64;link ControlService} rpc, where the request contains the key, for accessing the socket.
   * ControlService: The main rpc that sends commands to the server, and recieves metrics after these commands
   * 	are carried out by the esp8266. The command is a three-dimentional vector, and the response contains metrics
   * 	that are displayed on the app.
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
     * <pre>
     * Acknowledge RPC - Hard Coded
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.car_rpc.generated.ControlAcknowledgeResponse> controlAcknowledgeService(
        com.car_rpc.generated.ControlAcknowledge request) {
      return futureUnaryCall(
          getChannel().newCall(getControlAcknowledgeServiceMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Server Acknowledge RPC
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.car_rpc.generated.ServerAcknowledgeResponse> serverAcknowledgeService(
        com.car_rpc.generated.ServerAcknowledge request) {
      return futureUnaryCall(
          getChannel().newCall(getServerAcknowledgeServiceMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * mDNS - not started
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.car_rpc.generated.Esp8266DiscoveryResponse> discoveryService(
        com.car_rpc.generated.Esp8266DiscoveryRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getDiscoveryServiceMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Control RPC
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.car_rpc.generated.ControlResponse> controlCommandService(
        com.car_rpc.generated.ControlRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getControlCommandServiceMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CONTROL_ACKNOWLEDGE_SERVICE = 0;
  private static final int METHODID_SERVER_ACKNOWLEDGE_SERVICE = 1;
  private static final int METHODID_DISCOVERY_SERVICE = 2;
  private static final int METHODID_CONTROL_COMMAND_SERVICE = 3;

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
        case METHODID_CONTROL_ACKNOWLEDGE_SERVICE:
          serviceImpl.controlAcknowledgeService((com.car_rpc.generated.ControlAcknowledge) request,
              (io.grpc.stub.StreamObserver<com.car_rpc.generated.ControlAcknowledgeResponse>) responseObserver);
          break;
        case METHODID_SERVER_ACKNOWLEDGE_SERVICE:
          serviceImpl.serverAcknowledgeService((com.car_rpc.generated.ServerAcknowledge) request,
              (io.grpc.stub.StreamObserver<com.car_rpc.generated.ServerAcknowledgeResponse>) responseObserver);
          break;
        case METHODID_DISCOVERY_SERVICE:
          serviceImpl.discoveryService((com.car_rpc.generated.Esp8266DiscoveryRequest) request,
              (io.grpc.stub.StreamObserver<com.car_rpc.generated.Esp8266DiscoveryResponse>) responseObserver);
          break;
        case METHODID_CONTROL_COMMAND_SERVICE:
          serviceImpl.controlCommandService((com.car_rpc.generated.ControlRequest) request,
              (io.grpc.stub.StreamObserver<com.car_rpc.generated.ControlResponse>) responseObserver);
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
      return com.car_rpc.generated.ControllerProto.getDescriptor();
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
              .addMethod(getControlAcknowledgeServiceMethod())
              .addMethod(getServerAcknowledgeServiceMethod())
              .addMethod(getDiscoveryServiceMethod())
              .addMethod(getControlCommandServiceMethod())
              .build();
        }
      }
    }
    return result;
  }
}
