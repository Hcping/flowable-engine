/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.flowable.app.filter;

import java.util.Collection;

import javax.annotation.PostConstruct;

import org.flowable.app.properties.FlowableRemoteIdmProperties;
import org.flowable.app.service.idm.RemoteIdmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;

/**
 * @author Filip Hrisafov
 */
public class FlowableCookieFilterRegistrationBean extends FilterRegistrationBean {

    protected final RemoteIdmService remoteIdmService;

    protected final FlowableRemoteIdmProperties properties;

    protected FlowableCookieFilterCallback filterCallback;
    protected Collection<String> requiredPrivileges;

    public FlowableCookieFilterRegistrationBean(RemoteIdmService remoteIdmService, FlowableRemoteIdmProperties properties) {
        this.remoteIdmService = remoteIdmService;
        this.properties = properties;
    }

    @PostConstruct
    protected void initializeFilter() {
        if (getFilter() == null) {
            FlowableCookieFilter flowableCookieFilter = new FlowableCookieFilter(remoteIdmService, properties);
            flowableCookieFilter.setFilterCallback(filterCallback);
            flowableCookieFilter.setRequiredPrivileges(requiredPrivileges);
            flowableCookieFilter.initCaches();
            setFilter(flowableCookieFilter);
        }
    }

    @Autowired(required = false)
    public void setFilterCallback(FlowableCookieFilterCallback filterCallback) {
        this.filterCallback = filterCallback;
    }

    public void setRequiredPrivileges(Collection<String> requiredPrivileges) {
        this.requiredPrivileges = requiredPrivileges;
    }
}
