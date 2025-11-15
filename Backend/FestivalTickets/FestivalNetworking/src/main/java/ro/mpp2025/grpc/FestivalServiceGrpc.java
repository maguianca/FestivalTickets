package ro.mpp2025.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.52.1)",
    comments = "Source: FestivalgRPC.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class FestivalServiceGrpc {

  private FestivalServiceGrpc() {}

  public static final String SERVICE_NAME = "ro.mpp2025.grpc.FestivalService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<Festivalgrpc.LoginRequest,
      Festivalgrpc.LoginResponse> getLoginMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Login",
      requestType = Festivalgrpc.LoginRequest.class,
      responseType = Festivalgrpc.LoginResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<Festivalgrpc.LoginRequest,
      Festivalgrpc.LoginResponse> getLoginMethod() {
    io.grpc.MethodDescriptor<Festivalgrpc.LoginRequest, Festivalgrpc.LoginResponse> getLoginMethod;
    if ((getLoginMethod = FestivalServiceGrpc.getLoginMethod) == null) {
      synchronized (FestivalServiceGrpc.class) {
        if ((getLoginMethod = FestivalServiceGrpc.getLoginMethod) == null) {
          FestivalServiceGrpc.getLoginMethod = getLoginMethod =
              io.grpc.MethodDescriptor.<Festivalgrpc.LoginRequest, Festivalgrpc.LoginResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Login"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  Festivalgrpc.LoginRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  Festivalgrpc.LoginResponse.getDefaultInstance()))
              .setSchemaDescriptor(new FestivalServiceMethodDescriptorSupplier("Login"))
              .build();
        }
      }
    }
    return getLoginMethod;
  }

  private static volatile io.grpc.MethodDescriptor<Festivalgrpc.GetSpectacoleRequest,
      Festivalgrpc.GetSpectacoleResponse> getGetSpectacoleMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetSpectacole",
      requestType = Festivalgrpc.GetSpectacoleRequest.class,
      responseType = Festivalgrpc.GetSpectacoleResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<Festivalgrpc.GetSpectacoleRequest,
      Festivalgrpc.GetSpectacoleResponse> getGetSpectacoleMethod() {
    io.grpc.MethodDescriptor<Festivalgrpc.GetSpectacoleRequest, Festivalgrpc.GetSpectacoleResponse> getGetSpectacoleMethod;
    if ((getGetSpectacoleMethod = FestivalServiceGrpc.getGetSpectacoleMethod) == null) {
      synchronized (FestivalServiceGrpc.class) {
        if ((getGetSpectacoleMethod = FestivalServiceGrpc.getGetSpectacoleMethod) == null) {
          FestivalServiceGrpc.getGetSpectacoleMethod = getGetSpectacoleMethod =
              io.grpc.MethodDescriptor.<Festivalgrpc.GetSpectacoleRequest, Festivalgrpc.GetSpectacoleResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetSpectacole"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  Festivalgrpc.GetSpectacoleRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  Festivalgrpc.GetSpectacoleResponse.getDefaultInstance()))
              .setSchemaDescriptor(new FestivalServiceMethodDescriptorSupplier("GetSpectacole"))
              .build();
        }
      }
    }
    return getGetSpectacoleMethod;
  }

  private static volatile io.grpc.MethodDescriptor<Festivalgrpc.FilterSpectacoleRequest,
      Festivalgrpc.FilterSpectacoleResponse> getFilterSpectacoleMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "FilterSpectacole",
      requestType = Festivalgrpc.FilterSpectacoleRequest.class,
      responseType = Festivalgrpc.FilterSpectacoleResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<Festivalgrpc.FilterSpectacoleRequest,
      Festivalgrpc.FilterSpectacoleResponse> getFilterSpectacoleMethod() {
    io.grpc.MethodDescriptor<Festivalgrpc.FilterSpectacoleRequest, Festivalgrpc.FilterSpectacoleResponse> getFilterSpectacoleMethod;
    if ((getFilterSpectacoleMethod = FestivalServiceGrpc.getFilterSpectacoleMethod) == null) {
      synchronized (FestivalServiceGrpc.class) {
        if ((getFilterSpectacoleMethod = FestivalServiceGrpc.getFilterSpectacoleMethod) == null) {
          FestivalServiceGrpc.getFilterSpectacoleMethod = getFilterSpectacoleMethod =
              io.grpc.MethodDescriptor.<Festivalgrpc.FilterSpectacoleRequest, Festivalgrpc.FilterSpectacoleResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "FilterSpectacole"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  Festivalgrpc.FilterSpectacoleRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  Festivalgrpc.FilterSpectacoleResponse.getDefaultInstance()))
              .setSchemaDescriptor(new FestivalServiceMethodDescriptorSupplier("FilterSpectacole"))
              .build();
        }
      }
    }
    return getFilterSpectacoleMethod;
  }

  private static volatile io.grpc.MethodDescriptor<Festivalgrpc.ReserveBiletRequest,
      Festivalgrpc.ReserveBiletResponse> getReserveBiletMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ReserveBilet",
      requestType = Festivalgrpc.ReserveBiletRequest.class,
      responseType = Festivalgrpc.ReserveBiletResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<Festivalgrpc.ReserveBiletRequest,
      Festivalgrpc.ReserveBiletResponse> getReserveBiletMethod() {
    io.grpc.MethodDescriptor<Festivalgrpc.ReserveBiletRequest, Festivalgrpc.ReserveBiletResponse> getReserveBiletMethod;
    if ((getReserveBiletMethod = FestivalServiceGrpc.getReserveBiletMethod) == null) {
      synchronized (FestivalServiceGrpc.class) {
        if ((getReserveBiletMethod = FestivalServiceGrpc.getReserveBiletMethod) == null) {
          FestivalServiceGrpc.getReserveBiletMethod = getReserveBiletMethod =
              io.grpc.MethodDescriptor.<Festivalgrpc.ReserveBiletRequest, Festivalgrpc.ReserveBiletResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ReserveBilet"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  Festivalgrpc.ReserveBiletRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  Festivalgrpc.ReserveBiletResponse.getDefaultInstance()))
              .setSchemaDescriptor(new FestivalServiceMethodDescriptorSupplier("ReserveBilet"))
              .build();
        }
      }
    }
    return getReserveBiletMethod;
  }

  private static volatile io.grpc.MethodDescriptor<Festivalgrpc.Angajat,
      Festivalgrpc.Spectacol> getSubscribeUpdatesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SubscribeUpdates",
      requestType = Festivalgrpc.Angajat.class,
      responseType = Festivalgrpc.Spectacol.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<Festivalgrpc.Angajat,
      Festivalgrpc.Spectacol> getSubscribeUpdatesMethod() {
    io.grpc.MethodDescriptor<Festivalgrpc.Angajat, Festivalgrpc.Spectacol> getSubscribeUpdatesMethod;
    if ((getSubscribeUpdatesMethod = FestivalServiceGrpc.getSubscribeUpdatesMethod) == null) {
      synchronized (FestivalServiceGrpc.class) {
        if ((getSubscribeUpdatesMethod = FestivalServiceGrpc.getSubscribeUpdatesMethod) == null) {
          FestivalServiceGrpc.getSubscribeUpdatesMethod = getSubscribeUpdatesMethod =
              io.grpc.MethodDescriptor.<Festivalgrpc.Angajat, Festivalgrpc.Spectacol>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SubscribeUpdates"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  Festivalgrpc.Angajat.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  Festivalgrpc.Spectacol.getDefaultInstance()))
              .setSchemaDescriptor(new FestivalServiceMethodDescriptorSupplier("SubscribeUpdates"))
              .build();
        }
      }
    }
    return getSubscribeUpdatesMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static FestivalServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<FestivalServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<FestivalServiceStub>() {
        @Override
        public FestivalServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new FestivalServiceStub(channel, callOptions);
        }
      };
    return FestivalServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static FestivalServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<FestivalServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<FestivalServiceBlockingStub>() {
        @Override
        public FestivalServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new FestivalServiceBlockingStub(channel, callOptions);
        }
      };
    return FestivalServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static FestivalServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<FestivalServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<FestivalServiceFutureStub>() {
        @Override
        public FestivalServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new FestivalServiceFutureStub(channel, callOptions);
        }
      };
    return FestivalServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class FestivalServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void login(Festivalgrpc.LoginRequest request,
                      io.grpc.stub.StreamObserver<Festivalgrpc.LoginResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getLoginMethod(), responseObserver);
    }

    /**
     */
    public void getSpectacole(Festivalgrpc.GetSpectacoleRequest request,
                              io.grpc.stub.StreamObserver<Festivalgrpc.GetSpectacoleResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetSpectacoleMethod(), responseObserver);
    }

    /**
     */
    public void filterSpectacole(Festivalgrpc.FilterSpectacoleRequest request,
                                 io.grpc.stub.StreamObserver<Festivalgrpc.FilterSpectacoleResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getFilterSpectacoleMethod(), responseObserver);
    }

    /**
     */
    public void reserveBilet(Festivalgrpc.ReserveBiletRequest request,
                             io.grpc.stub.StreamObserver<Festivalgrpc.ReserveBiletResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getReserveBiletMethod(), responseObserver);
    }

    /**
     */
    public void subscribeUpdates(Festivalgrpc.Angajat request,
                                 io.grpc.stub.StreamObserver<Festivalgrpc.Spectacol> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSubscribeUpdatesMethod(), responseObserver);
    }

    @Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getLoginMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                Festivalgrpc.LoginRequest,
                Festivalgrpc.LoginResponse>(
                  this, METHODID_LOGIN)))
          .addMethod(
            getGetSpectacoleMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                Festivalgrpc.GetSpectacoleRequest,
                Festivalgrpc.GetSpectacoleResponse>(
                  this, METHODID_GET_SPECTACOLE)))
          .addMethod(
            getFilterSpectacoleMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                Festivalgrpc.FilterSpectacoleRequest,
                Festivalgrpc.FilterSpectacoleResponse>(
                  this, METHODID_FILTER_SPECTACOLE)))
          .addMethod(
            getReserveBiletMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                Festivalgrpc.ReserveBiletRequest,
                Festivalgrpc.ReserveBiletResponse>(
                  this, METHODID_RESERVE_BILET)))
          .addMethod(
            getSubscribeUpdatesMethod(),
            io.grpc.stub.ServerCalls.asyncServerStreamingCall(
              new MethodHandlers<
                Festivalgrpc.Angajat,
                Festivalgrpc.Spectacol>(
                  this, METHODID_SUBSCRIBE_UPDATES)))
          .build();
    }
  }

  /**
   */
  public static final class FestivalServiceStub extends io.grpc.stub.AbstractAsyncStub<FestivalServiceStub> {
    private FestivalServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected FestivalServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new FestivalServiceStub(channel, callOptions);
    }

    /**
     */
    public void login(Festivalgrpc.LoginRequest request,
                      io.grpc.stub.StreamObserver<Festivalgrpc.LoginResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getLoginMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getSpectacole(Festivalgrpc.GetSpectacoleRequest request,
                              io.grpc.stub.StreamObserver<Festivalgrpc.GetSpectacoleResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetSpectacoleMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void filterSpectacole(Festivalgrpc.FilterSpectacoleRequest request,
                                 io.grpc.stub.StreamObserver<Festivalgrpc.FilterSpectacoleResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getFilterSpectacoleMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void reserveBilet(Festivalgrpc.ReserveBiletRequest request,
                             io.grpc.stub.StreamObserver<Festivalgrpc.ReserveBiletResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getReserveBiletMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void subscribeUpdates(Festivalgrpc.Angajat request,
                                 io.grpc.stub.StreamObserver<Festivalgrpc.Spectacol> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getSubscribeUpdatesMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class FestivalServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<FestivalServiceBlockingStub> {
    private FestivalServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected FestivalServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new FestivalServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public Festivalgrpc.LoginResponse login(Festivalgrpc.LoginRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getLoginMethod(), getCallOptions(), request);
    }

    /**
     */
    public Festivalgrpc.GetSpectacoleResponse getSpectacole(Festivalgrpc.GetSpectacoleRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetSpectacoleMethod(), getCallOptions(), request);
    }

    /**
     */
    public Festivalgrpc.FilterSpectacoleResponse filterSpectacole(Festivalgrpc.FilterSpectacoleRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getFilterSpectacoleMethod(), getCallOptions(), request);
    }

    /**
     */
    public Festivalgrpc.ReserveBiletResponse reserveBilet(Festivalgrpc.ReserveBiletRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getReserveBiletMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<Festivalgrpc.Spectacol> subscribeUpdates(
        Festivalgrpc.Angajat request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getSubscribeUpdatesMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class FestivalServiceFutureStub extends io.grpc.stub.AbstractFutureStub<FestivalServiceFutureStub> {
    private FestivalServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected FestivalServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new FestivalServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<Festivalgrpc.LoginResponse> login(
        Festivalgrpc.LoginRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getLoginMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<Festivalgrpc.GetSpectacoleResponse> getSpectacole(
        Festivalgrpc.GetSpectacoleRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetSpectacoleMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<Festivalgrpc.FilterSpectacoleResponse> filterSpectacole(
        Festivalgrpc.FilterSpectacoleRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getFilterSpectacoleMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<Festivalgrpc.ReserveBiletResponse> reserveBilet(
        Festivalgrpc.ReserveBiletRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getReserveBiletMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_LOGIN = 0;
  private static final int METHODID_GET_SPECTACOLE = 1;
  private static final int METHODID_FILTER_SPECTACOLE = 2;
  private static final int METHODID_RESERVE_BILET = 3;
  private static final int METHODID_SUBSCRIBE_UPDATES = 4;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final FestivalServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(FestivalServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_LOGIN:
          serviceImpl.login((Festivalgrpc.LoginRequest) request,
              (io.grpc.stub.StreamObserver<Festivalgrpc.LoginResponse>) responseObserver);
          break;
        case METHODID_GET_SPECTACOLE:
          serviceImpl.getSpectacole((Festivalgrpc.GetSpectacoleRequest) request,
              (io.grpc.stub.StreamObserver<Festivalgrpc.GetSpectacoleResponse>) responseObserver);
          break;
        case METHODID_FILTER_SPECTACOLE:
          serviceImpl.filterSpectacole((Festivalgrpc.FilterSpectacoleRequest) request,
              (io.grpc.stub.StreamObserver<Festivalgrpc.FilterSpectacoleResponse>) responseObserver);
          break;
        case METHODID_RESERVE_BILET:
          serviceImpl.reserveBilet((Festivalgrpc.ReserveBiletRequest) request,
              (io.grpc.stub.StreamObserver<Festivalgrpc.ReserveBiletResponse>) responseObserver);
          break;
        case METHODID_SUBSCRIBE_UPDATES:
          serviceImpl.subscribeUpdates((Festivalgrpc.Angajat) request,
              (io.grpc.stub.StreamObserver<Festivalgrpc.Spectacol>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @Override
    @SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class FestivalServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    FestivalServiceBaseDescriptorSupplier() {}

    @Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return Festivalgrpc.getDescriptor();
    }

    @Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("FestivalService");
    }
  }

  private static final class FestivalServiceFileDescriptorSupplier
      extends FestivalServiceBaseDescriptorSupplier {
    FestivalServiceFileDescriptorSupplier() {}
  }

  private static final class FestivalServiceMethodDescriptorSupplier
      extends FestivalServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    FestivalServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (FestivalServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new FestivalServiceFileDescriptorSupplier())
              .addMethod(getLoginMethod())
              .addMethod(getGetSpectacoleMethod())
              .addMethod(getFilterSpectacoleMethod())
              .addMethod(getReserveBiletMethod())
              .addMethod(getSubscribeUpdatesMethod())
              .build();
        }
      }
    }
    return result;
  }
}
