package com.itu.keystroke.model.core;

import javax.persistence.*;
import com.itu.keystroke.enums.KeystrokeType;
import com.itu.keystroke.enums.KeystrokeEvent;
import com.itu.keystroke.model.BaseDomainModel;

@Entity
@Table(name = "keystroke")
public class Keystroke extends BaseDomainModel {

    @Column(name = "key_typed", nullable = false)
    private String keyTyped;

    @Column(name = "record_number")
    private int recordNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "event", nullable = false)
    private KeystrokeEvent keystrokeEvent;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private KeystrokeType keystrokeType;

    @Column(name = "time_stamp")
    private long timeStamp;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    public Keystroke() {
    }

    public String getKeyTyped() {
        return keyTyped;
    }

    public void setKeyTyped(String keyTyped) {
        this.keyTyped = keyTyped;
    }

    public int getRecordNumber() {
        return recordNumber;
    }

    public void setRecordNumber(int recordNumber) {
        this.recordNumber = recordNumber;
    }

    public KeystrokeEvent getKeystrokeEvent() {
        return keystrokeEvent;
    }

    public void setKeystrokeEvent(KeystrokeEvent keystrokeEvent) {
        this.keystrokeEvent = keystrokeEvent;
    }

    public KeystrokeType getKeystrokeType() {
        return keystrokeType;
    }

    public void setKeystrokeType(KeystrokeType keystrokeType) {
        this.keystrokeType = keystrokeType;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}