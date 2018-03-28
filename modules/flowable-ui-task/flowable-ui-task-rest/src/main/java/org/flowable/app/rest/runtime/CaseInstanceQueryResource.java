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
package org.flowable.app.rest.runtime;

import org.flowable.app.model.common.ResultListDataRepresentation;
import org.flowable.app.service.runtime.FlowableCaseInstanceQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
@RequestMapping("/app")
public class CaseInstanceQueryResource {

    @Autowired
    protected FlowableCaseInstanceQueryService caseInstanceQueryService;

    @RequestMapping(value = "/rest/query/case-instances", method = RequestMethod.POST, produces = "application/json")
    public ResultListDataRepresentation getCaseInstances(@RequestBody ObjectNode requestNode) {
        return caseInstanceQueryService.getCaseInstances(requestNode);
    }

}
