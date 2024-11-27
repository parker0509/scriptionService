package mall.shopping.mall.service.user;

import mall.shopping.mall.domain.User;
import mall.shopping.mall.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //CRUD


    //create ( 만들기 )
    public User createUser(User user){

        Optional<User> existUser = userRepository.findByEmail(user.getEmail());
        if(existUser.isPresent()){
           throw new IllegalArgumentException("존재하는 아이디가 있습니다.");
        }
       return userRepository.save(user);
    }

    //Read ( 읽기 )
    public List<User> readUser(User user){
        return userRepository.findAll();
    }

    //Update ( 갱신 )
    public void updateUser(Long id, User userDetails){

        Optional<User>userOpt = userRepository.findById(id);

        if(userOpt.isPresent()){
           User user = userOpt.get();
           user.setName(userDetails.getName());
           user.setEmail(userDetails.getEmail());
           user.setPhone(userDetails.getPhone());
           user.setPassword(userDetails.getPassword());
        }

    }

    //Delete (삭제)
    public void deleteUser(Long id){

        Optional<User>userOpt = userRepository.findById(id);

        if (userOpt.isPresent()) {

            User user = userOpt.get();
            userRepository.delete(user);

        }else{

            //유저가 없을 경우 예외 처리
            throw new RuntimeException("User not found with id : "+id);

        }
    }

}
