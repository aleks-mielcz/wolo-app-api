package pl.pjwstk.woloappapi.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryToEventDTO {
    private Long id;
    private CategoryDTO category;
    private EventDTO event;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }

    public EventDTO getEvent() {
        return event;
    }

    public void setEvent(EventDTO event) {
        this.event = event;
    }
}
