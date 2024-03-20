package ad.chat2.dto;

import ad.chat2.model.User;

public class DtoUsers {

    private Long id;
    private User us1;
    private User us2;

    public DtoUsers() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUs1() {
        return this.us1;
    }

    public void setUs1(User us1) {
        this.us1 = us1;
    }

    public User getUs2() {
        return this.us2;
    }

    public void setUs2(User us2) {
        this.us2 = us2;
    }

}
