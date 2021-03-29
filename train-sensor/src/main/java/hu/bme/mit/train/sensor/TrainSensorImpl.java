package hu.bme.mit.train.sensor;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;

public class TrainSensorImpl implements TrainSensor {

    private TrainController controller;
    private TrainUser user;
    private int speedLimit = 5;
    private boolean alarmState = false;

    public TrainSensorImpl(TrainController controller, TrainUser user) {
        this.controller = controller;
        this.user = user;
    }

    @Override
    public int getSpeedLimit() {
        return speedLimit;
    }

    @Override
    public void overrideSpeedLimit(int speedLimit) {
        setAlarmState(
                speedLimit < 0 ||
                speedLimit > 500 ||
                speedLimit < (controller.getReferenceSpeed() - controller.getReferenceSpeed()*0.5)
        );

        this.speedLimit = speedLimit;
        controller.setSpeedLimit(speedLimit);
    }

    @Override
    public boolean getAlarmState() { return alarmState; }

    @Override
    public void setAlarmState(boolean alarmState) { this.alarmState = alarmState; }
}
