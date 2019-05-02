package com.itu.keystroke.dto.keystroke;

public class KeystrokeRequestDTO {

    private long timeStamp;
    private String keyTyped;
    private String keystrokeEvent;

    public KeystrokeRequestDTO() {
    }

    public String getKeyTyped() {
        return keyTyped;
    }

    public void setKeyTyped(String keyTyped) {
        this.keyTyped = keyTyped;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getKeystrokeEvent() {
        return keystrokeEvent;
    }

    public void setKeystrokeEvent(String keystrokeEvent) {
        this.keystrokeEvent = keystrokeEvent;
    }
}