package bases.actions;

import bases.FrameCounter;
import bases.GameObject;

public class WaitAction implements Action {
    FrameCounter framedelay;
    public WaitAction(int framedelay) {
        this.framedelay = new FrameCounter(framedelay);
    }

    @Override
    public boolean run(GameObject owner) {
        return (framedelay.run());
    }

    @Override
    public void reset() {
        framedelay.reset();
    }
}
