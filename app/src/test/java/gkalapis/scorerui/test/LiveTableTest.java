package gkalapis.scorerui.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.List;

import gkalapis.scorerui.BuildConfig;
import gkalapis.scorerui.ui.livetable.LiveTablePresenter;
import gkalapis.scorerui.ui.livetable.LiveTableScreen;

import static gkalapis.scorerui.TestHelper.setTestInjector;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class LiveTableTest {

    private LiveTablePresenter liveTablePresenter;
    private LiveTableScreen liveTableScreen;

    @Before
    public void setup() throws Exception {
        setTestInjector();
        liveTableScreen = mock(LiveTableScreen.class);
        liveTablePresenter = new LiveTablePresenter();
        liveTablePresenter.attachScreen(liveTableScreen);
    }

    @Test
    public void testShowLiveTable() {
        liveTablePresenter.refreshTable();

        ArgumentCaptor<List> teamCaptor = ArgumentCaptor.forClass(List.class);
        verify(liveTableScreen).showLiveTable(teamCaptor.capture());

        assertTrue(teamCaptor.getValue().size() > 0);
    }

    @After
    public void tearDown() {
        liveTablePresenter.detachScreen();
    }
}
