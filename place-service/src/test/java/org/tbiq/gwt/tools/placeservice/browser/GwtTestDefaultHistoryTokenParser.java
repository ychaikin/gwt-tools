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

import java.util.List;
import java.util.Map;

import org.tbiq.gwt.tools.placeservice.browser.DefaultHistoryTokenParser;
import org.tbiq.gwt.tools.placeservice.browser.PlaceServiceUtil;

import com.google.gwt.junit.client.GWTTestCase;

/**
 * GwtTestDefaultHistoryTokenParser class is a GWT test class for
 * {@link DefaultHistoryTokenParser}.
 * 
 * @author Yaakov Chaikin (yaakov.chaikin@gmail.com)
 */
public class GwtTestDefaultHistoryTokenParser
  extends GWTTestCase
{
  private DefaultHistoryTokenParser parser;

  /*
   * (non-Javadoc)
   * 
   * @see com.google.gwt.junit.client.GWTTestCase#getModuleName()
   */
  @Override
  public String getModuleName()
  {
    return "org.tbiq.gwt.tools.placeservice.PlaceService";
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.google.gwt.junit.client.GWTTestCase#gwtSetUp()
   */
  @Override
  protected void gwtSetUp()
    throws Exception
  {
    parser = new DefaultHistoryTokenParser();
  }

  /**
   * Test method for {@link DefaultHistoryTokenParser#parse(java.lang.String)}.
   */
  public void testParse()
  {
    Map<String, List<String>> nameValuePairs = parser.parse("view");
    assertEquals(null, nameValuePairs);

    nameValuePairs = parser.parse("view=");
    List<String> values = nameValuePairs.get("view");
    assertEquals(null, values);

    nameValuePairs = parser.parse("view=list");
    values = nameValuePairs.get("view");
    assertEquals(1, values.size());
    assertEquals("list", values.get(0));

    nameValuePairs = parser.parse("view=list&id=20");
    values = nameValuePairs.get("view");
    assertEquals(1, values.size());
    assertEquals("list", values.get(0));
    values = nameValuePairs.get("id");
    assertEquals(1, values.size());
    assertEquals("20", values.get(0));

    nameValuePairs = parser.parse("view=list&id=20&id=23");
    values = nameValuePairs.get("view");
    assertEquals(1, values.size());
    assertEquals("list", values.get(0));
    values = nameValuePairs.get("id");
    assertEquals(2, values.size());
    assertEquals("20", values.get(0));
    assertEquals("23", values.get(1));

    // Test decoding
    nameValuePairs = parser.parse("view=add&name=Yaakov%20Chaikin");
    String value = PlaceServiceUtil.getParamValue(nameValuePairs, "view", null);
    assertEquals("add", value);
    value = PlaceServiceUtil.getParamValue(nameValuePairs, "name", null);
    assertEquals("Yaakov Chaikin", value);
  }

  /**
   * Test method for
   * {@link DefaultHistoryTokenParser#isValidHistoryToken(java.lang.String)} .
   */
  public void testIsValidHistoryToken()
  {
    assertTrue(parser.isValidHistoryToken("view="));
    assertTrue(parser.isValidHistoryToken("view=12"));
    assertTrue(parser.isValidHistoryToken("view=Bla_Bla"));
    assertTrue(parser.isValidHistoryToken("view=Bla+bla"));
    assertTrue(parser.isValidHistoryToken("view=Bla(bla)"));
    assertTrue(parser.isValidHistoryToken("view=bla&name="));
    assertTrue(parser.isValidHistoryToken("view=bla&name=123*."));

    assertFalse(parser.isValidHistoryToken("view"));
    assertFalse(parser.isValidHistoryToken("view=&"));
    assertFalse(parser.isValidHistoryToken("view=bla&"));
    assertFalse(parser.isValidHistoryToken("1view=list"));
    assertFalse(parser.isValidHistoryToken("view=list&bla=test?ing"));
    assertFalse(parser.isValidHistoryToken(""));
    assertFalse(parser.isValidHistoryToken(null));
  }

  /**
   * Test method for
   * {@link DefaultHistoryTokenParser#buildHistoryToken(String, String, String)} .
   */
  public void testBuildHistoryToken()
  {
    String currentHistoryToken = null;
    currentHistoryToken = parser.buildHistoryToken(currentHistoryToken, "param1", "");
    assertEquals("param1=", currentHistoryToken);

    currentHistoryToken = parser.buildHistoryToken(" ", "param1", "value1");
    assertEquals("param1=value1", currentHistoryToken);

    currentHistoryToken = parser.buildHistoryToken(currentHistoryToken,
                                                   "name",
                                                   "Yaakov Chaikin");
    assertEquals("param1=value1&name=Yaakov%20Chaikin", currentHistoryToken);
  }
}
