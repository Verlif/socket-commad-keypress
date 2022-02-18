package idea.verlif.socket.commad.keypress;

import idea.verlif.socket.command.config.ConfigAdapter;

/**
 * @author Verlif
 * @version 1.0
 * @date 2022/2/18 10:36
 */
public class KeyPressConfig implements ConfigAdapter {

    /**
     * 每个按键执行间隔
     */
    private long interval = 20;

    /**
     * 单次能接收的按键最大数量
     */
    private long max = 20;

    @Override
    public String key() {
        return "keyPress";
    }

    public long getInterval() {
        return interval;
    }

    public void setInterval(long interval) {
        if (interval > 0) {
            this.interval = interval;
        }
    }

    public long getMax() {
        return max;
    }

    public void setMax(long max) {
        if (max > 0) {
            this.max = max;
        }
    }
}
