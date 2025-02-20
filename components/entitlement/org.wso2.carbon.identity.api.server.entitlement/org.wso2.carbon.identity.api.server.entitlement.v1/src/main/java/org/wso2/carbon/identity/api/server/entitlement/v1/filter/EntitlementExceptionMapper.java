/*
 * Copyright (c) 2024, WSO2 LLC. (http://www.wso2.com).
 *
 * WSO2 LLC. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.carbon.identity.api.server.entitlement.v1.filter;

import org.wso2.carbon.identity.api.server.entitlement.v1.exception.RequestParseException;
import org.wso2.carbon.identity.api.server.entitlement.v1.exception.AbstractEntitlementException;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Custom Exception Mapper
 * Centralized controlling of exceptions occurred within the service
 * Every exception will be thrown from the service methods to be caught
 * by this class and an appropriate ExceptionBean will be created and
 * be sent as the response
 */
@Provider
@Produces(MediaType.TEXT_PLAIN)
public class EntitlementExceptionMapper implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception e) {
        //If the exception occurred was a known EntitlementEndpoint exception
        if (e instanceof AbstractEntitlementException) {
            AbstractEntitlementException entitlementException = (AbstractEntitlementException) e;
            return Response.status(Response.Status.BAD_REQUEST)
                           .entity(entitlementException.getExceptioBean())
                           .build();
        }
        //Any unknown exception occurred, return status 500
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                       .entity(new RequestParseException().getExceptioBean())
                       .build();
    }
}
