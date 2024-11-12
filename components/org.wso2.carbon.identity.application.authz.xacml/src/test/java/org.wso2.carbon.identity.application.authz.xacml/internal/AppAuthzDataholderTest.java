/*
 * Copyright (c) 2017, WSO2 LLC. (https://www.wso2.com).
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.carbon.identity.application.authz.xacml.internal;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.wso2.carbon.identity.entitlement.EntitlementService;

import static org.mockito.Mockito.mock;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;
import static org.testng.AssertJUnit.assertEquals;

/**
 * AppAuthzDataholderTest defines unit tests for AppAuthzDataholder class.
 */
public class AppAuthzDataholderTest {

    AppAuthzDataholder appAuthzDataholder;

    @BeforeClass
    public void init() {

        appAuthzDataholder = AppAuthzDataholder.getInstance();
    }

    @Test
    public void testGetInstance() {

        assertNotNull(appAuthzDataholder);
        assertEquals(appAuthzDataholder, AppAuthzDataholder.getInstance());
    }

    @Test
    public void testGetAndSetEntitlementService() {

        assertNull(appAuthzDataholder.getEntitlementService());
        appAuthzDataholder.setEntitlementService(mock(EntitlementService.class));
        assertNotNull(appAuthzDataholder.getEntitlementService());
        appAuthzDataholder.setEntitlementService(null);
    }
}
