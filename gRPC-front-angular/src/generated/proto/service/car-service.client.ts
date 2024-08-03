// @generated by protobuf-ts 2.9.4
// @generated from protobuf file "service/car-service.proto" (syntax proto3)
// tslint:disable
import type { RpcTransport } from "@protobuf-ts/runtime-rpc";
import type { ServiceInfo } from "@protobuf-ts/runtime-rpc";
import { CarService } from "./car-service";
import type { ServerStreamingCall } from "@protobuf-ts/runtime-rpc";
import type { Empty } from "../google/protobuf/empty";
import type { CarUpdateVersion } from "../request/update/car-update";
import type { CarGetResponse } from "../request/get/car-get-response";
import type { CarId } from "../request/get/car-id";
import { stackIntercept } from "@protobuf-ts/runtime-rpc";
import type { CarResponse } from "../response/car-response";
import type { CarRequest } from "../request/car-request";
import type { UnaryCall } from "@protobuf-ts/runtime-rpc";
import type { RpcOptions } from "@protobuf-ts/runtime-rpc";
/**
 * @generated from protobuf service CarService
 */
export interface ICarServiceClient {
    /**
     * unary
     *
     * @generated from protobuf rpc: CreateCar(request.CarRequest) returns (response.CarResponse);
     */
    createCar(input: CarRequest, options?: RpcOptions): UnaryCall<CarRequest, CarResponse>;
    /**
     * @generated from protobuf rpc: GetCar(request.get.CarId) returns (request.get.CarGetResponse);
     */
    getCar(input: CarId, options?: RpcOptions): UnaryCall<CarId, CarGetResponse>;
    /**
     * we don't return nothing
     *
     * @generated from protobuf rpc: UpdateCarVersion(request.update.CarUpdateVersion) returns (google.protobuf.Empty);
     */
    updateCarVersion(input: CarUpdateVersion, options?: RpcOptions): UnaryCall<CarUpdateVersion, Empty>;
    /**
     * server stream
     *
     * @generated from protobuf rpc: getAllCarsAsStream(google.protobuf.Empty) returns (stream request.get.CarGetResponse);
     */
    getAllCarsAsStream(input: Empty, options?: RpcOptions): ServerStreamingCall<Empty, CarGetResponse>;
}
/**
 * @generated from protobuf service CarService
 */
export class CarServiceClient implements ICarServiceClient, ServiceInfo {
    typeName = CarService.typeName;
    methods = CarService.methods;
    options = CarService.options;
    constructor(private readonly _transport: RpcTransport) {
    }
    /**
     * unary
     *
     * @generated from protobuf rpc: CreateCar(request.CarRequest) returns (response.CarResponse);
     */
    createCar(input: CarRequest, options?: RpcOptions): UnaryCall<CarRequest, CarResponse> {
        const method = this.methods[0], opt = this._transport.mergeOptions(options);
        return stackIntercept<CarRequest, CarResponse>("unary", this._transport, method, opt, input);
    }
    /**
     * @generated from protobuf rpc: GetCar(request.get.CarId) returns (request.get.CarGetResponse);
     */
    getCar(input: CarId, options?: RpcOptions): UnaryCall<CarId, CarGetResponse> {
        const method = this.methods[1], opt = this._transport.mergeOptions(options);
        return stackIntercept<CarId, CarGetResponse>("unary", this._transport, method, opt, input);
    }
    /**
     * we don't return nothing
     *
     * @generated from protobuf rpc: UpdateCarVersion(request.update.CarUpdateVersion) returns (google.protobuf.Empty);
     */
    updateCarVersion(input: CarUpdateVersion, options?: RpcOptions): UnaryCall<CarUpdateVersion, Empty> {
        const method = this.methods[2], opt = this._transport.mergeOptions(options);
        return stackIntercept<CarUpdateVersion, Empty>("unary", this._transport, method, opt, input);
    }
    /**
     * server stream
     *
     * @generated from protobuf rpc: getAllCarsAsStream(google.protobuf.Empty) returns (stream request.get.CarGetResponse);
     */
    getAllCarsAsStream(input: Empty, options?: RpcOptions): ServerStreamingCall<Empty, CarGetResponse> {
        const method = this.methods[3], opt = this._transport.mergeOptions(options);
        return stackIntercept<Empty, CarGetResponse>("serverStreaming", this._transport, method, opt, input);
    }
}
