package pl.pjwstk.woloappapi.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="user_event")
public class User_event {
    private long user_id;
    private long event_id;
}
