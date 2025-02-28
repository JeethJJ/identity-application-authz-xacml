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

package org.wso2.carbon.identity.entitlement.policy.finder.registry;

import org.wso2.carbon.registry.core.Resource;
import org.wso2.carbon.registry.core.exceptions.RegistryException;
import org.wso2.carbon.registry.core.jdbc.handlers.RequestContext;
import org.wso2.carbon.registry.core.jdbc.handlers.filters.MediaTypeMatcher;

/**
 * Registry policy media type matcher
 */
public class RegistryPolicyMediaTypeMatcher extends MediaTypeMatcher {


    @Override
    public boolean handlePut(RequestContext requestContext) throws RegistryException {
        Resource resource = requestContext.getResource();
        if (resource != null) {
            String mType = resource.getMediaType();
            return mType != null && (invert != mType.equals(getMediaType()));
        }
        return false;
    }

    @Override
    public boolean handleDelete(RequestContext requestContext) throws RegistryException {
        Resource resource = requestContext.getResource();
        if (resource != null) {
            String mType = resource.getMediaType();
            return mType != null && (invert != mType.equals(getMediaType()));
        } else if (requestContext.getResourcePath() != null) {
            resource = requestContext.getRegistry().get(requestContext.
                    getResourcePath().getCompletePath());
            if (resource != null) {
                String mType = resource.getMediaType();
                return mType != null && (invert != mType.equals(getMediaType()));
            }
        }
        return false;
    }
}
