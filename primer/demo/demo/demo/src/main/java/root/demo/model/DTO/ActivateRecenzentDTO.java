package root.demo.model.DTO;

import java.io.Serializable;

public class ActivateRecenzentDTO implements Serializable {
    private Long userId;
    private boolean activate;

    public ActivateRecenzentDTO() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public boolean isActivate() {
        return activate;
    }

    public void setActivate(boolean activate) {
        this.activate = activate;
    }
}
