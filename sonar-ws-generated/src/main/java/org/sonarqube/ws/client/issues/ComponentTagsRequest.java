/*
 * SonarQube
 * Copyright (C) 2009-2017 SonarSource SA
 * mailto:info AT sonarsource DOT com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.sonarqube.ws.client.issues;

import java.util.List;
import javax.annotation.Generated;

/**
 * List tags for the issues under a given component (including issues on the descendants of the component)
 *
 * This is part of the internal API.
 * This is a POST request.
 * @see <a href="https://next.sonarqube.com/sonarqube/web_api/api/issues/component_tags">Further information about this action online (including a response example)</a>
 * @since 5.1
 */
@Generated("https://github.com/SonarSource/sonar-ws-generator")
public class ComponentTagsRequest {

  private String componentUuid;
  private String createdAfter;
  private String ps;

  /**
   * A component UUID
   *
   * This is a mandatory parameter.
   * Example value: "7d8749e8-3070-4903-9188-bdd82933bb92"
   */
  public ComponentTagsRequest setComponentUuid(String componentUuid) {
    this.componentUuid = componentUuid;
    return this;
  }

  public String getComponentUuid() {
    return componentUuid;
  }

  /**
   * To retrieve tags on issues created after the given date (inclusive). <br>Either a date (server timezone) or datetime can be provided.
   *
   * Example value: "2017-10-19 or 2017-10-19T13:00:00+0200"
   */
  public ComponentTagsRequest setCreatedAfter(String createdAfter) {
    this.createdAfter = createdAfter;
    return this;
  }

  public String getCreatedAfter() {
    return createdAfter;
  }

  /**
   * The maximum size of the list to return
   *
   * Example value: "25"
   */
  public ComponentTagsRequest setPs(String ps) {
    this.ps = ps;
    return this;
  }

  public String getPs() {
    return ps;
  }
}
