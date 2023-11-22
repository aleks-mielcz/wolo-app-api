package pl.pjwstk.woloappapi.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class ShiftDTO {

    private Long id;
    private Long addressToEventId;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate date;
    private int capacity;
    private boolean isLeaderRequired;
    private int requiredMinAge;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAddressToEventId() {
        return addressToEventId;
    }

    public void setAddressToEventId(Long addressToEventId) {
        this.addressToEventId = addressToEventId;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isLeaderRequired() {
        return isLeaderRequired;
    }

    public void setLeaderRequired(boolean leaderRequired) {
        isLeaderRequired = leaderRequired;
    }

    public int getRequiredMinAge() {
        return requiredMinAge;
    }

    public void setRequiredMinAge(int requiredMinAge) {
        this.requiredMinAge = requiredMinAge;
    }
}
