package ao.matondosantiago.todolist.user;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, UUID> {
    UserModel findByUsername(String username); //procura se já existe um usuário com o mesmo username, caso exista retorna o usuário, caso não exista retorna null
}
