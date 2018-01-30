/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.ambari.server.controller;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class ServiceGroupRequest {

  private String clusterName; // REF
  private String serviceGroupName; // GET/CREATE/UPDATE/DELETE
  private Set<String> mpackNames; // Associated mpack names

  public ServiceGroupRequest(String clusterName, String serviceGroupName) {
    this.clusterName = clusterName;
    this.serviceGroupName = serviceGroupName;
    mpackNames = new HashSet<>();
  }

  /**
   * @return the clusterName
   */
  public String getClusterName() {
    return clusterName;
  }

  /**
   * @param clusterName the clusterName to set
   */
  public void setClusterName(String clusterName) {
    this.clusterName = clusterName;
  }

  /**
   * @return the serviceGroupName
   */
  public String getServiceGroupName() {
    return serviceGroupName;
  }

  /**
   * @param serviceGroupName the service group name to set
   */
  public void setServiceGroupName(String serviceGroupName) {
    this.serviceGroupName = serviceGroupName;
  }

  /**
   * @return a list of associated mpack names
   */
  public Set<String> getMpackNames() {
    return mpackNames;
  }

  /**
   * @param mpackNames a list of associated mpack names
   */
  public void setMpackNames(Set<String> mpackNames) {
    this.mpackNames.addAll(mpackNames);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("clusterName="+clusterName+",serviceGroupName="+serviceGroupName);
    if (!mpackNames.isEmpty()) {
      sb.append(",mpackNames=");
      mpackNames.stream().map(mpackName -> sb.append(mpackName + ","));
      sb.deleteCharAt(sb.length()-1);
    }
    return sb.toString();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }

    ServiceGroupRequest other = (ServiceGroupRequest) obj;

    // ignore mpackNames, even if they are different, we still consider sgrequests are the same

    return Objects.equals(clusterName, other.clusterName) &&
      Objects.equals(serviceGroupName, other.serviceGroupName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(clusterName, serviceGroupName);
  }
}