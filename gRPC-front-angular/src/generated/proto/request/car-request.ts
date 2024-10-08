// @generated by protobuf-ts 2.9.4
// @generated from protobuf file "request/car-request.proto" (package "request", syntax proto3)
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
import { VersionRequest } from "./VersionRequest";
import { Manufacturer } from "./manufacturer";
/**
 * @generated from protobuf message request.CarRequest
 */
export interface CarRequest {
    /**
     * @generated from protobuf field: string model = 1;
     */
    model: string;
    /**
     * @generated from protobuf field: request.Manufacturer manufacturer = 3;
     */
    manufacturer?: Manufacturer;
    /**
     * @generated from protobuf field: request.VersionRequest version = 4;
     */
    version?: VersionRequest;
}
// @generated message type with reflection information, may provide speed optimized methods
class CarRequest$Type extends MessageType<CarRequest> {
    constructor() {
        super("request.CarRequest", [
            { no: 1, name: "model", kind: "scalar", T: 9 /*ScalarType.STRING*/ },
            { no: 3, name: "manufacturer", kind: "message", T: () => Manufacturer },
            { no: 4, name: "version", kind: "message", T: () => VersionRequest }
        ]);
    }
    create(value?: PartialMessage<CarRequest>): CarRequest {
        const message = globalThis.Object.create((this.messagePrototype!));
        message.model = "";
        if (value !== undefined)
            reflectionMergePartial<CarRequest>(this, message, value);
        return message;
    }
    internalBinaryRead(reader: IBinaryReader, length: number, options: BinaryReadOptions, target?: CarRequest): CarRequest {
        let message = target ?? this.create(), end = reader.pos + length;
        while (reader.pos < end) {
            let [fieldNo, wireType] = reader.tag();
            switch (fieldNo) {
                case /* string model */ 1:
                    message.model = reader.string();
                    break;
                case /* request.Manufacturer manufacturer */ 3:
                    message.manufacturer = Manufacturer.internalBinaryRead(reader, reader.uint32(), options, message.manufacturer);
                    break;
                case /* request.VersionRequest version */ 4:
                    message.version = VersionRequest.internalBinaryRead(reader, reader.uint32(), options, message.version);
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
    internalBinaryWrite(message: CarRequest, writer: IBinaryWriter, options: BinaryWriteOptions): IBinaryWriter {
        /* string model = 1; */
        if (message.model !== "")
            writer.tag(1, WireType.LengthDelimited).string(message.model);
        /* request.Manufacturer manufacturer = 3; */
        if (message.manufacturer)
            Manufacturer.internalBinaryWrite(message.manufacturer, writer.tag(3, WireType.LengthDelimited).fork(), options).join();
        /* request.VersionRequest version = 4; */
        if (message.version)
            VersionRequest.internalBinaryWrite(message.version, writer.tag(4, WireType.LengthDelimited).fork(), options).join();
        let u = options.writeUnknownFields;
        if (u !== false)
            (u == true ? UnknownFieldHandler.onWrite : u)(this.typeName, message, writer);
        return writer;
    }
}
/**
 * @generated MessageType for protobuf message request.CarRequest
 */
export const CarRequest = new CarRequest$Type();
