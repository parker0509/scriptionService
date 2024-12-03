package mall.shopping.mall.service.user;

import mall.shopping.mall.domain.User;
import mall.shopping.mall.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {


    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;

    }

   /*
      비밀번호 비밀화 시켜야함
      private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();*//*
    */

/*    List :  public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    */


    //CRUD


    //create ( 만들기 )
    public User createUser(User user) {

        User existUser = userRepository.findByEmail(user.getEmail());

        if (existUser != null) {
            throw new IllegalArgumentException("이미 존재하는 이메일이 있습니다. 확인 부탁드립니다.");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);

    }

    //Read ( 읽기 )
    public List<User> readUser(User user) {
        return userRepository.findAll();
    }

    //Update ( 갱신 )
    public void updateUser(Long id, User userDetails) {

        Optional<User> userOpt = userRepository.findById(id);

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setName(userDetails.getName());
            user.setEmail(userDetails.getEmail());
            user.setPhone(userDetails.getPhone());
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }

    }

    //Delete (삭제)
    public void deleteUser(Long id) {

        Optional<User> userOpt = userRepository.findById(id);

        if (userOpt.isPresent()) {

            User user = userOpt.get();
            userRepository.delete(user);

        } else {

            //유저가 없을 경우 예외 처리
            throw new RuntimeException("User not found with id : " + id);

        }
    }

}
