package softuni.jsonexer.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.jsonexer.domain.dto.UserSeedDto;
import softuni.jsonexer.domain.entity.User;
import softuni.jsonexer.repository.UserRepository;
import softuni.jsonexer.service.UserService;
import softuni.jsonexer.util.ValidatorUtil;

import java.util.Random;

@Service
public class UserServiceImpl implements UserService {

    private final ValidatorUtil validatorUtil;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(ValidatorUtil validatorUtil, ModelMapper modelMapper, UserRepository userRepository) {
        this.validatorUtil = validatorUtil;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    @Override
    public void seedUsers(UserSeedDto[] userSeedDtos) {
        for (UserSeedDto userSeedDto : userSeedDtos) {
            if (!validatorUtil.isValid(userSeedDto)){
                this.validatorUtil.violations(userSeedDto)
                        .forEach(v -> System.out.println(v.getMessage()));

                continue;
            }

            User user = this.modelMapper.map(userSeedDto, User.class);
            ;
            this.userRepository.saveAndFlush(user);
        }
    }
}
