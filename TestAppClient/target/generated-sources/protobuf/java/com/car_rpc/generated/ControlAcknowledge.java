// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Controller_Interface.proto

package com.car_rpc.generated;

/**
 * <pre>
 * For when discovery doesnt work and you want to hard-code
 * IP and Port.
 * </pre>
 *
 * Protobuf type {@code controller.ControlAcknowledge}
 */
public  final class ControlAcknowledge extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:controller.ControlAcknowledge)
    ControlAcknowledgeOrBuilder {
  // Use ControlAcknowledge.newBuilder() to construct.
  private ControlAcknowledge(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private ControlAcknowledge() {
    address_ = "";
    port_ = 0;
    controllerKey_ = 0;
    name_ = "";
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private ControlAcknowledge(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    int mutable_bitField0_ = 0;
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          default: {
            if (!parseUnknownField(input, unknownFields,
                                   extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
          case 10: {
            com.google.protobuf.ByteString bs = input.readBytes();
            bitField0_ |= 0x00000001;
            address_ = bs;
            break;
          }
          case 16: {
            bitField0_ |= 0x00000002;
            port_ = input.readInt32();
            break;
          }
          case 24: {
            bitField0_ |= 0x00000004;
            controllerKey_ = input.readInt32();
            break;
          }
          case 34: {
            com.google.protobuf.ByteString bs = input.readBytes();
            bitField0_ |= 0x00000008;
            name_ = bs;
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.car_rpc.generated.ControllerProto.internal_static_controller_ControlAcknowledge_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.car_rpc.generated.ControllerProto.internal_static_controller_ControlAcknowledge_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.car_rpc.generated.ControlAcknowledge.class, com.car_rpc.generated.ControlAcknowledge.Builder.class);
  }

  private int bitField0_;
  public static final int ADDRESS_FIELD_NUMBER = 1;
  private volatile java.lang.Object address_;
  /**
   * <code>required string address = 1;</code>
   */
  public boolean hasAddress() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <code>required string address = 1;</code>
   */
  public java.lang.String getAddress() {
    java.lang.Object ref = address_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      if (bs.isValidUtf8()) {
        address_ = s;
      }
      return s;
    }
  }
  /**
   * <code>required string address = 1;</code>
   */
  public com.google.protobuf.ByteString
      getAddressBytes() {
    java.lang.Object ref = address_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      address_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int PORT_FIELD_NUMBER = 2;
  private int port_;
  /**
   * <code>required int32 port = 2;</code>
   */
  public boolean hasPort() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <code>required int32 port = 2;</code>
   */
  public int getPort() {
    return port_;
  }

  public static final int CONTROLLERKEY_FIELD_NUMBER = 3;
  private int controllerKey_;
  /**
   * <code>required int32 controllerKey = 3;</code>
   */
  public boolean hasControllerKey() {
    return ((bitField0_ & 0x00000004) == 0x00000004);
  }
  /**
   * <code>required int32 controllerKey = 3;</code>
   */
  public int getControllerKey() {
    return controllerKey_;
  }

  public static final int NAME_FIELD_NUMBER = 4;
  private volatile java.lang.Object name_;
  /**
   * <code>required string name = 4;</code>
   */
  public boolean hasName() {
    return ((bitField0_ & 0x00000008) == 0x00000008);
  }
  /**
   * <code>required string name = 4;</code>
   */
  public java.lang.String getName() {
    java.lang.Object ref = name_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      if (bs.isValidUtf8()) {
        name_ = s;
      }
      return s;
    }
  }
  /**
   * <code>required string name = 4;</code>
   */
  public com.google.protobuf.ByteString
      getNameBytes() {
    java.lang.Object ref = name_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      name_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    if (!hasAddress()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasPort()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasControllerKey()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasName()) {
      memoizedIsInitialized = 0;
      return false;
    }
    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, address_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      output.writeInt32(2, port_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      output.writeInt32(3, controllerKey_);
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 4, name_);
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, address_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, port_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(3, controllerKey_);
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(4, name_);
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  private static final long serialVersionUID = 0L;
  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof com.car_rpc.generated.ControlAcknowledge)) {
      return super.equals(obj);
    }
    com.car_rpc.generated.ControlAcknowledge other = (com.car_rpc.generated.ControlAcknowledge) obj;

    boolean result = true;
    result = result && (hasAddress() == other.hasAddress());
    if (hasAddress()) {
      result = result && getAddress()
          .equals(other.getAddress());
    }
    result = result && (hasPort() == other.hasPort());
    if (hasPort()) {
      result = result && (getPort()
          == other.getPort());
    }
    result = result && (hasControllerKey() == other.hasControllerKey());
    if (hasControllerKey()) {
      result = result && (getControllerKey()
          == other.getControllerKey());
    }
    result = result && (hasName() == other.hasName());
    if (hasName()) {
      result = result && getName()
          .equals(other.getName());
    }
    result = result && unknownFields.equals(other.unknownFields);
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    if (hasAddress()) {
      hash = (37 * hash) + ADDRESS_FIELD_NUMBER;
      hash = (53 * hash) + getAddress().hashCode();
    }
    if (hasPort()) {
      hash = (37 * hash) + PORT_FIELD_NUMBER;
      hash = (53 * hash) + getPort();
    }
    if (hasControllerKey()) {
      hash = (37 * hash) + CONTROLLERKEY_FIELD_NUMBER;
      hash = (53 * hash) + getControllerKey();
    }
    if (hasName()) {
      hash = (37 * hash) + NAME_FIELD_NUMBER;
      hash = (53 * hash) + getName().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.car_rpc.generated.ControlAcknowledge parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.car_rpc.generated.ControlAcknowledge parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.car_rpc.generated.ControlAcknowledge parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.car_rpc.generated.ControlAcknowledge parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.car_rpc.generated.ControlAcknowledge parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.car_rpc.generated.ControlAcknowledge parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.car_rpc.generated.ControlAcknowledge parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.car_rpc.generated.ControlAcknowledge parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.car_rpc.generated.ControlAcknowledge parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.car_rpc.generated.ControlAcknowledge parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.car_rpc.generated.ControlAcknowledge prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * <pre>
   * For when discovery doesnt work and you want to hard-code
   * IP and Port.
   * </pre>
   *
   * Protobuf type {@code controller.ControlAcknowledge}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:controller.ControlAcknowledge)
      com.car_rpc.generated.ControlAcknowledgeOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.car_rpc.generated.ControllerProto.internal_static_controller_ControlAcknowledge_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.car_rpc.generated.ControllerProto.internal_static_controller_ControlAcknowledge_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.car_rpc.generated.ControlAcknowledge.class, com.car_rpc.generated.ControlAcknowledge.Builder.class);
    }

    // Construct using com.car_rpc.generated.ControlAcknowledge.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    public Builder clear() {
      super.clear();
      address_ = "";
      bitField0_ = (bitField0_ & ~0x00000001);
      port_ = 0;
      bitField0_ = (bitField0_ & ~0x00000002);
      controllerKey_ = 0;
      bitField0_ = (bitField0_ & ~0x00000004);
      name_ = "";
      bitField0_ = (bitField0_ & ~0x00000008);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.car_rpc.generated.ControllerProto.internal_static_controller_ControlAcknowledge_descriptor;
    }

    public com.car_rpc.generated.ControlAcknowledge getDefaultInstanceForType() {
      return com.car_rpc.generated.ControlAcknowledge.getDefaultInstance();
    }

    public com.car_rpc.generated.ControlAcknowledge build() {
      com.car_rpc.generated.ControlAcknowledge result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public com.car_rpc.generated.ControlAcknowledge buildPartial() {
      com.car_rpc.generated.ControlAcknowledge result = new com.car_rpc.generated.ControlAcknowledge(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.address_ = address_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      result.port_ = port_;
      if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
        to_bitField0_ |= 0x00000004;
      }
      result.controllerKey_ = controllerKey_;
      if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
        to_bitField0_ |= 0x00000008;
      }
      result.name_ = name_;
      result.bitField0_ = to_bitField0_;
      onBuilt();
      return result;
    }

    public Builder clone() {
      return (Builder) super.clone();
    }
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        Object value) {
      return (Builder) super.setField(field, value);
    }
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return (Builder) super.clearField(field);
    }
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return (Builder) super.clearOneof(oneof);
    }
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, Object value) {
      return (Builder) super.setRepeatedField(field, index, value);
    }
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        Object value) {
      return (Builder) super.addRepeatedField(field, value);
    }
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.car_rpc.generated.ControlAcknowledge) {
        return mergeFrom((com.car_rpc.generated.ControlAcknowledge)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.car_rpc.generated.ControlAcknowledge other) {
      if (other == com.car_rpc.generated.ControlAcknowledge.getDefaultInstance()) return this;
      if (other.hasAddress()) {
        bitField0_ |= 0x00000001;
        address_ = other.address_;
        onChanged();
      }
      if (other.hasPort()) {
        setPort(other.getPort());
      }
      if (other.hasControllerKey()) {
        setControllerKey(other.getControllerKey());
      }
      if (other.hasName()) {
        bitField0_ |= 0x00000008;
        name_ = other.name_;
        onChanged();
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      if (!hasAddress()) {
        return false;
      }
      if (!hasPort()) {
        return false;
      }
      if (!hasControllerKey()) {
        return false;
      }
      if (!hasName()) {
        return false;
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      com.car_rpc.generated.ControlAcknowledge parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.car_rpc.generated.ControlAcknowledge) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private java.lang.Object address_ = "";
    /**
     * <code>required string address = 1;</code>
     */
    public boolean hasAddress() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required string address = 1;</code>
     */
    public java.lang.String getAddress() {
      java.lang.Object ref = address_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          address_ = s;
        }
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>required string address = 1;</code>
     */
    public com.google.protobuf.ByteString
        getAddressBytes() {
      java.lang.Object ref = address_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        address_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>required string address = 1;</code>
     */
    public Builder setAddress(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000001;
      address_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>required string address = 1;</code>
     */
    public Builder clearAddress() {
      bitField0_ = (bitField0_ & ~0x00000001);
      address_ = getDefaultInstance().getAddress();
      onChanged();
      return this;
    }
    /**
     * <code>required string address = 1;</code>
     */
    public Builder setAddressBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000001;
      address_ = value;
      onChanged();
      return this;
    }

    private int port_ ;
    /**
     * <code>required int32 port = 2;</code>
     */
    public boolean hasPort() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>required int32 port = 2;</code>
     */
    public int getPort() {
      return port_;
    }
    /**
     * <code>required int32 port = 2;</code>
     */
    public Builder setPort(int value) {
      bitField0_ |= 0x00000002;
      port_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>required int32 port = 2;</code>
     */
    public Builder clearPort() {
      bitField0_ = (bitField0_ & ~0x00000002);
      port_ = 0;
      onChanged();
      return this;
    }

    private int controllerKey_ ;
    /**
     * <code>required int32 controllerKey = 3;</code>
     */
    public boolean hasControllerKey() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <code>required int32 controllerKey = 3;</code>
     */
    public int getControllerKey() {
      return controllerKey_;
    }
    /**
     * <code>required int32 controllerKey = 3;</code>
     */
    public Builder setControllerKey(int value) {
      bitField0_ |= 0x00000004;
      controllerKey_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>required int32 controllerKey = 3;</code>
     */
    public Builder clearControllerKey() {
      bitField0_ = (bitField0_ & ~0x00000004);
      controllerKey_ = 0;
      onChanged();
      return this;
    }

    private java.lang.Object name_ = "";
    /**
     * <code>required string name = 4;</code>
     */
    public boolean hasName() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    /**
     * <code>required string name = 4;</code>
     */
    public java.lang.String getName() {
      java.lang.Object ref = name_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          name_ = s;
        }
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>required string name = 4;</code>
     */
    public com.google.protobuf.ByteString
        getNameBytes() {
      java.lang.Object ref = name_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        name_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>required string name = 4;</code>
     */
    public Builder setName(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000008;
      name_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>required string name = 4;</code>
     */
    public Builder clearName() {
      bitField0_ = (bitField0_ & ~0x00000008);
      name_ = getDefaultInstance().getName();
      onChanged();
      return this;
    }
    /**
     * <code>required string name = 4;</code>
     */
    public Builder setNameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000008;
      name_ = value;
      onChanged();
      return this;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:controller.ControlAcknowledge)
  }

  // @@protoc_insertion_point(class_scope:controller.ControlAcknowledge)
  private static final com.car_rpc.generated.ControlAcknowledge DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.car_rpc.generated.ControlAcknowledge();
  }

  public static com.car_rpc.generated.ControlAcknowledge getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<ControlAcknowledge>
      PARSER = new com.google.protobuf.AbstractParser<ControlAcknowledge>() {
    public ControlAcknowledge parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new ControlAcknowledge(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<ControlAcknowledge> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<ControlAcknowledge> getParserForType() {
    return PARSER;
  }

  public com.car_rpc.generated.ControlAcknowledge getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

