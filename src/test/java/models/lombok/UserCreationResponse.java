package models.lombok;

import lombok.Data;

@Data
public class UserCreationResponse {

    private String name, job, id, createdAt;
}

