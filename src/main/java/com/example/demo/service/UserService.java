public interface UserService {
    User saveUser(User user);
    User findByEmail(String email);
}
