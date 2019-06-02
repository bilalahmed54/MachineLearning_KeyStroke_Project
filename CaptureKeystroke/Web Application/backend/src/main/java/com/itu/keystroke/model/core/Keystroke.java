package com.itu.keystroke.model.core;

import com.itu.keystroke.enums.KeystrokeMode;
import com.itu.keystroke.enums.KeystrokeType;

import javax.persistence.*;

@Entity
@Table(name = "keystroke")
public class Keystroke {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "record_number")
    private int recordNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private KeystrokeType keystrokeType;

    @Enumerated(EnumType.STRING)
    @Column(name = "mode", nullable = false)
    private KeystrokeMode keyStrokeMode;

    //Supports 65,535 characters - 64 KB
    @Column(name = "key_strokes_data", columnDefinition = "TEXT")
    private String keyStrokesData;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    public Keystroke() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getRecordNumber() {
        return recordNumber;
    }

    public void setRecordNumber(int recordNumber) {
        this.recordNumber = recordNumber;
    }

    public KeystrokeType getKeystrokeType() {
        return keystrokeType;
    }

    public void setKeystrokeType(KeystrokeType keystrokeType) {
        this.keystrokeType = keystrokeType;
    }

    public KeystrokeMode getKeyStrokeMode() {
        return keyStrokeMode;
    }

    public void setKeyStrokeMode(KeystrokeMode keyStrokeMode) {
        this.keyStrokeMode = keyStrokeMode;
    }

    public String getKeyStrokesData() {
        return keyStrokesData;
    }

    public void setKeyStrokesData(String keyStrokesData) {
        this.keyStrokesData = keyStrokesData;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}