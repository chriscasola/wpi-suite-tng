package edu.wpi.cs.wpisuitetng.network.configuration;

import static org.junit.Assert.*;

import java.util.Iterator;
import org.junit.*;

import edu.wpi.cs.wpisuitetng.network.RequestObserver;
import edu.wpi.cs.wpisuitetng.network.models.IRequest;

/**
 * Test NetworkConfiguration class.
 */
public class TestNetworkConfiguration {
	class MockObserver implements RequestObserver {

		public MockObserver() {}

		@Override
		public void responseSuccess(IRequest iReq) {
			// Do nothing
		}

		@Override
		public void responseError(IRequest iReq) {
			// Do nothing
		}

		@Override
		public void fail(IRequest iReq, Exception exception) {
			// Do nothing
		}
	}

	private NetworkConfiguration config;
	private String url = "http://wpi.edu";

	@Before
	public void setUp() {
		config = new NetworkConfiguration(url);
	}

	@Test
	public void testGetApiUrl() {
		assertTrue(url.equals(config.getApiUrl()));
	}

	@Test(expected = NullPointerException.class)
	public void testAddObserverNullObserverException() {
		config.addObserver(null);
	}

	@Test
	public void testAddObserver() {
		assertEquals(0, config.getObservers().size());
		config.addObserver(new MockObserver());
		config.addObserver(new MockObserver());
		assertEquals(2, config.getObservers().size());

		Iterator<RequestObserver> observersI = config.getObservers().iterator();

		while (observersI.hasNext()) {
			assertTrue(observersI.next() instanceof MockObserver);
		}
	}

	@Test(expected = NullPointerException.class)
	public void testAddRequestHeaderNullKeyException() {
		config.addRequestHeader(null, "value");
	}

	@Test(expected = NullPointerException.class)
	public void testAddRequestHeaderNullValueException() {
			config.addRequestHeader("header", null);
	}

	@Test
	public void testAddRequestHeader() {
		config.addRequestHeader("header1", "value1");
		config.addRequestHeader("header2", "value2a");
		config.addRequestHeader("header2", "value2b");
		config.addRequestHeader("header3", "value3");

		assertTrue(config.getRequestHeaders().containsKey("header1"));
		assertTrue(config.getRequestHeaders().containsKey("header2"));
		assertTrue(config.getRequestHeaders().containsKey("header3"));

		assertEquals(1, config.getRequestHeaders().get("header1").size());
		assertEquals(2, config.getRequestHeaders().get("header2").size());
		assertEquals(1, config.getRequestHeaders().get("header3").size());

		assertTrue(config.getRequestHeaders().get("header1").contains("value1"));
		assertTrue(config.getRequestHeaders().get("header2").contains("value2a"));
		assertTrue(config.getRequestHeaders().get("header2").contains("value2b"));
		assertTrue(config.getRequestHeaders().get("header3").contains("value3"));
	}

	@Test
	public void testCloneConstructor() {
		config.addRequestHeader("header1", "value1");
		config.addRequestHeader("header2", "value2a");
		config.addRequestHeader("header2", "value2b");
		config.addRequestHeader("header3", "value3");

		config.addObserver(new MockObserver());
		config.addObserver(new MockObserver());

		NetworkConfiguration config2 = new NetworkConfiguration(config);

		assertTrue(url.equals(config2.getApiUrl()));
		assertEquals(2, config2.getObservers().size());

		assertTrue(config2.getRequestHeaders().containsKey("header1"));
		assertTrue(config2.getRequestHeaders().containsKey("header2"));
		assertTrue(config2.getRequestHeaders().containsKey("header3"));

		assertEquals(1, config2.getRequestHeaders().get("header1").size());
		assertEquals(2, config2.getRequestHeaders().get("header2").size());
		assertEquals(1, config2.getRequestHeaders().get("header3").size());

		assertTrue(config2.getRequestHeaders().get("header1").contains("value1"));
		assertTrue(config2.getRequestHeaders().get("header2").contains("value2a"));
		assertTrue(config2.getRequestHeaders().get("header2").contains("value2b"));
		assertTrue(config2.getRequestHeaders().get("header3").contains("value3"));
	}
}
