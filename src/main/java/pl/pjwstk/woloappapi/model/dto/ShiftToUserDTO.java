package pl.pjwstk.woloappapi.model.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShiftToUserDTO {
    private Long id;
    private UserDTO user;
    private ShiftDTO shift;
    private boolean isOnReserveList;
    private boolean isLeader;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public ShiftDTO getShift() {
        return shift;
    }

    public void setShift(ShiftDTO shift) {
        this.shift = shift;
    }

    public boolean isOnReserveList() {
        return isOnReserveList;
    }

    public void setOnReserveList(boolean onReserveList) {
        isOnReserveList = onReserveList;
    }

    public boolean isLeader() {
        return isLeader;
    }

    public void setLeader(boolean leader) {
        isLeader = leader;
    }
}
