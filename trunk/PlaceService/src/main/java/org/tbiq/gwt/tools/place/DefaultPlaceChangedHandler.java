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
package org.tbiq.gwt.tools.place;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;

/**
 * DefaultPlaceChangedHandler class is the default implementation of the
 * {@link PlaceChangedEventHandler} interface.
 * 
 * @author Yaakov Chaikin (yaakov.chaikin@gmail.com)
 */
public class DefaultPlaceChangedHandler
  implements PlaceChangedEventHandler
{
  /** Container to add the corresponding to this place view into. */
  private final HasWidgets container;

  /** Application-wide event bus. */
  private final HandlerManager eventBus;

  /**
   * Constructor.
   * 
   * @param container Container to add the corresponding to this place view into.
   * @param eventBus Application-wide event bus.
   */
  public DefaultPlaceChangedHandler(final HasWidgets container,
                                    final HandlerManager eventBus)
  {
    this.container = container;
    this.eventBus = eventBus;
  }

  /*
   * (non-Javadoc)
   * 
   * @seeorg.tbiq.gwt.place.PlaceChangedEventHandler#onPlaceChange(org.tbiq.gwt.place.
   * PlaceChangedEvent)
   */
  @Override
  public void onPlaceChange(PlaceChangedEvent event)
  {
    // Retrieve wrapped place and show it
    Place requestedPlace = event.getPlace();
    requestedPlace.show(container, eventBus);
  }
}
