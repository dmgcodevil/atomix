/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.kuujo.copycat.protocol.impl;

import net.kuujo.copycat.cluster.MemberConfig;
import net.kuujo.copycat.protocol.Protocol;
import net.kuujo.copycat.protocol.ProtocolClient;
import net.kuujo.copycat.protocol.ProtocolServer;

import java.util.HashMap;
import java.util.Map;

/**
 * Local protocol implementation.
 *
 * @author <a href="http://github.com/kuujo">Jordan Halterman</a>
 */
public class LocalProtocol implements Protocol<MemberConfig> {
  private String address;
  private final Map<String, LocalProtocolServer> registry = new HashMap<>();

  public LocalProtocol() {
  }

  /**
   * Sets the protocol address.
   *
   * @param address The protocol address.
   */
  public void setAddress(String address) {
    this.address = address;
  }

  /**
   * Returns the protocol address.
   *
   * @return The protocol address.
   */
  public String getAddress() {
    return address;
  }

  /**
   * Sets the protocol address, returning the protocol for method chaining.
   *
   * @param address The protocol address.
   * @return The protocol instance.
   */
  public LocalProtocol withAddress(String address) {
    this.address = address;
    return this;
  }

  @Override
  public ProtocolServer createServer(MemberConfig member) {
    return new LocalProtocolServer(address, registry);
  }

  @Override
  public ProtocolClient createClient(MemberConfig member) {
    return new LocalProtocolClient(address, registry);
  }

}
