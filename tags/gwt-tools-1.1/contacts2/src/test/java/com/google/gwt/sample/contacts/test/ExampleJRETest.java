package com.google.gwt.sample.contacts.test;

import static org.easymock.EasyMock.createStrictMock;

import java.util.ArrayList;

import junit.framework.TestCase;

import org.tbiq.gwt.tools.rpcservice.browser.RpcServiceAsync;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.sample.contacts.client.presenter.ContactsPresenter;
import com.google.gwt.sample.contacts.shared.ContactDetails;

public class ExampleJRETest
  extends TestCase
{
  private ContactsPresenter contactsPresenter;
  private RpcServiceAsync mockRpcService;
  private HandlerManager eventBus;
  private ContactsPresenter.Display mockDisplay;

  protected void setUp()
  {
    mockRpcService = createStrictMock(RpcServiceAsync.class);
    eventBus = new HandlerManager(null);
    mockDisplay = createStrictMock(ContactsPresenter.Display.class);
    contactsPresenter = new ContactsPresenter(mockRpcService, eventBus, mockDisplay);
  }

  public void testContactSort()
  {
    ArrayList<ContactDetails> contactDetails = new ArrayList<ContactDetails>();
    contactDetails.add(new ContactDetails("0", "c_contact"));
    contactDetails.add(new ContactDetails("1", "b_contact"));
    contactDetails.add(new ContactDetails("2", "a_contact"));
    contactsPresenter.setContactDetails(contactDetails);
    contactsPresenter.sortContactDetails();
    assertTrue(contactsPresenter.getContactDetail(0).getDisplayName().equals("a_contact"));
    assertTrue(contactsPresenter.getContactDetail(1).getDisplayName().equals("b_contact"));
    assertTrue(contactsPresenter.getContactDetail(2).getDisplayName().equals("c_contact"));
  }
}
