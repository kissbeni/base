package hu.bme.mit.train.sensor;

import hu.bme.mit.train.interfaces.TrainController;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TrainSensorTest {

    private TrainSensorImpl mSensor;
    private TrainController mController;

    @Before
    public void before() {
        mController = mock(TrainController.class);
        when(mController.getReferenceSpeed()).thenReturn(150);
        mSensor = new TrainSensorImpl(mController, null);
    }

    @Test
    public void ThisIsAnExampleTestStub() {
        Assert.assertEquals(5, mSensor.getSpeedLimit());
    }

    @Test
    public void AlarmTestNegativeSpeed() {
        mSensor.overrideSpeedLimit(-40);
        assertTrue(mSensor.getAlarmState());
    }

    @Test
    public void AlarmTestSupersonicSpeed() {
        mSensor.overrideSpeedLimit(1234);
        assertTrue(mSensor.getAlarmState());
    }

    @Test
    public void AlarmTestJustAt50Percent() {
        mSensor.overrideSpeedLimit(75);
        assertFalse(mSensor.getAlarmState());
    }

    @Test
    public void AlarmTestBelow50Percent() {
        mSensor.overrideSpeedLimit(74);
        assertTrue(mSensor.getAlarmState());
    }
}
