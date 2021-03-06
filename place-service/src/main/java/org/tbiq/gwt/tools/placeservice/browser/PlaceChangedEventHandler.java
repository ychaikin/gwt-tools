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
package org.tbiq.gwt.tools.placeservice.browser;

import com.google.gwt.event.shared.EventHandler;

/**
 * PlaceChangedEventHandler class is an event handler for the {@link PlaceChangedEvent}.
 * 
 * @author Yaakov Chaikin (yaakov.chaikin@gmail.com)
 */
public interface PlaceChangedEventHandler
  extends EventHandler
{
  /**
   * This method is a handler method which is invoked whenever a registered implementation
   * of this interface detects that a {@link PlaceChangedEvent} has been fired.
   * 
   * @param event Event instance which contains the specifics about which place the
   *          application should switch to.
   */
  public void onPlaceChange(PlaceChangedEvent event);
}
