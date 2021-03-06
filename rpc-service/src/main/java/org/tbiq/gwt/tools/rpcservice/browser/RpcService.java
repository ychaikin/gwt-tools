/**
 * Copyright 2010 Yaakov Chaikin (yaakov.chaikin@gmail.com) Licensed under the Apache
 * License, Version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law or agreed
 * to in writing, software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under the
 * License.
 */
package org.tbiq.gwt.tools.rpcservice.browser;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * RpcService interface defines the single execute method (command pattern) to carry out
 * the RPC request.
 * 
 * @author Yaakov Chaikin (yaakov.chaikin@gmail.com)
 */
@RemoteServiceRelativePath("rpc-service")
public interface RpcService
  extends RemoteService
{
  /**
   * Executes some {@link RpcRequest} and returns some {@link RpcResponse}. This method
   * follows Command pattern.
   * 
   * @param rpcRequest RPC request object containing data which is needed in order to
   *          execute the request.
   * @return Response object which contains data which is the product of executing the RPC
   *         request.
   * @throws RpcServiceException If anything goes wrong with executing the RPC request.
   */
  public <RpcRequestT extends RpcRequest<RpcResponseT>, RpcResponseT extends RpcResponse> RpcResponseT execute(RpcRequestT rpcRequest)
    throws RpcServiceException;
}
