package gkalapis.scorerui.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import gkalapis.scorerui.BuildConfig;
import gkalapis.scorerui.ui.main.MainPresenter;
import gkalapis.scorerui.ui.main.MainScreen;

import static gkalapis.scorerui.TestHelper.setTestInjector;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class MainTest {

    private MainPresenter mainPresenter;
    private MainScreen mainScreen;

    @Before
    public void setup() throws Exception {
        setTestInjector();
        mainScreen = mock(MainScreen.class);
        mainPresenter = new MainPresenter();
        mainPresenter.attachScreen(mainScreen);
    }

    @Test
    public void testShowLiveMatches() {
        mainPresenter.startLiveMatchesActivity();
        verify(mainScreen).showLiveMatches();
    }


    @After
    public void tearDown() {
        mainPresenter.detachScreen();
    }
}
