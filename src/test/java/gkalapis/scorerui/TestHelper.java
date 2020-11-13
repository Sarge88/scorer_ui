package gkalapis.scorerui;


import org.robolectric.RuntimeEnvironment;
import org.robolectric.shadows.ShadowLog;

public class TestHelper {

	public static void setTestInjector() {
		ShadowLog.stream = System.out;
		PlScoresApplication application = (PlScoresApplication) RuntimeEnvironment.application;
		PlScoresApplicationComponent injector = DaggerTestComponent.builder().testModule(new TestModule(application.getApplicationContext())).build();
		application.injector = injector;
	}
}
