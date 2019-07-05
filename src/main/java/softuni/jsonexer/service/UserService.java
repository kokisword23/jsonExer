package softuni.jsonexer.service;

import softuni.jsonexer.domain.dto.UserSeedDto;

public interface UserService {

    void seedUsers(UserSeedDto[] userSeedDtos);
}
