package org.example.lld.practice.iot_event_notification_system.models;

public class Event {
    private final String deviceId;
    private final DeviceType deviceType;
    private final Severity severity;
    private final long timestamp;
    private final String data;

    public Event(String deviceId, DeviceType deviceType, Severity severity, long timestamp, String data) {
        if (deviceId == null || deviceType == null || severity == null || timestamp < 0) {
            throw new IllegalArgumentException("Invalid IoT event data");
        }
        this.deviceId = deviceId;
        this.deviceType = deviceType;
        this.severity = severity;
        this.timestamp = timestamp;
        this.data = data != null ? data : "";
    }

    public String getDeviceId() {
        return deviceId;
    }

    public DeviceType getDeviceType() {
        return deviceType;
    }

    public Severity getSeverity() {
        return severity;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getData() {
        return data;
    }
}
