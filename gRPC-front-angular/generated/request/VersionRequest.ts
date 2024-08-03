// @generated by protobuf-ts 2.9.4
// @generated from protobuf file "request/VersionRequest.proto" (package "request", syntax proto3)
// tslint:disable
import type { BinaryWriteOptions } from "@protobuf-ts/runtime";
import type { IBinaryWriter } from "@protobuf-ts/runtime";
import { WireType } from "@protobuf-ts/runtime";
import type { BinaryReadOptions } from "@protobuf-ts/runtime";
import type { IBinaryReader } from "@protobuf-ts/runtime";
import { UnknownFieldHandler } from "@protobuf-ts/runtime";
import type { PartialMessage } from "@protobuf-ts/runtime";
import { reflectionMergePartial } from "@protobuf-ts/runtime";
import { MessageType } from "@protobuf-ts/runtime";
import { Type } from "./type";
/**
 * @generated from protobuf message request.VersionRequest
 */
export interface VersionRequest {
    /**
     * @generated from protobuf field: request.Type type = 1;
     */
    type: Type;
    /**
     * @generated from protobuf field: int32 productionYear = 2;
     */
    productionYear: number;
}
// @generated message type with reflection information, may provide speed optimized methods
class VersionRequest$Type extends MessageType<VersionRequest> {
    constructor() {
        super("request.VersionRequest", [
            { no: 1, name: "type", kind: "enum", T: () => ["request.Type", Type] },
            { no: 2, name: "productionYear", kind: "scalar", T: 5 /*ScalarType.INT32*/ }
        ]);
    }
    create(value?: PartialMessage<VersionRequest>): VersionRequest {
        const message = globalThis.Object.create((this.messagePrototype!));
        message.type = 0;
        message.productionYear = 0;
        if (value !== undefined)
            reflectionMergePartial<VersionRequest>(this, message, value);
        return message;
    }
    internalBinaryRead(reader: IBinaryReader, length: number, options: BinaryReadOptions, target?: VersionRequest): VersionRequest {
        let message = target ?? this.create(), end = reader.pos + length;
        while (reader.pos < end) {
            let [fieldNo, wireType] = reader.tag();
            switch (fieldNo) {
                case /* request.Type type */ 1:
                    message.type = reader.int32();
                    break;
                case /* int32 productionYear */ 2:
                    message.productionYear = reader.int32();
                    break;
                default:
                    let u = options.readUnknownField;
                    if (u === "throw")
                        throw new globalThis.Error(`Unknown field ${fieldNo} (wire type ${wireType}) for ${this.typeName}`);
                    let d = reader.skip(wireType);
                    if (u !== false)
                        (u === true ? UnknownFieldHandler.onRead : u)(this.typeName, message, fieldNo, wireType, d);
            }
        }
        return message;
    }
    internalBinaryWrite(message: VersionRequest, writer: IBinaryWriter, options: BinaryWriteOptions): IBinaryWriter {
        /* request.Type type = 1; */
        if (message.type !== 0)
            writer.tag(1, WireType.Varint).int32(message.type);
        /* int32 productionYear = 2; */
        if (message.productionYear !== 0)
            writer.tag(2, WireType.Varint).int32(message.productionYear);
        let u = options.writeUnknownFields;
        if (u !== false)
            (u == true ? UnknownFieldHandler.onWrite : u)(this.typeName, message, writer);
        return writer;
    }
}
/**
 * @generated MessageType for protobuf message request.VersionRequest
 */
export const VersionRequest = new VersionRequest$Type();
