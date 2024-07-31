package com.gini.interceptor;

import io.grpc.CallCredentials;
import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.ClientCall;
import io.grpc.ClientInterceptor;
import io.grpc.MethodDescriptor;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.client.interceptor.GrpcGlobalClientInterceptor;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;

@RequiredArgsConstructor
@GrpcGlobalClientInterceptor
public class JwtTokenInterceptor implements ClientInterceptor {

    private final JwtCredentials jwtCredentials;

    @Override
    public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(MethodDescriptor<ReqT, RespT> methodDescriptor,
                                                               CallOptions callOptions,
                                                               Channel channel) {

        var newCallOptions = callOptions.withCallCredentials(jwtCredentials);

        return channel.newCall(methodDescriptor, newCallOptions);
    }
}
