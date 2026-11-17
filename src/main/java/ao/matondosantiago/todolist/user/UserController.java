package ao.matondosantiago.todolist.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.favre.lib.crypto.bcrypt.BCrypt;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@RestController 
@RequestMapping("/users")
public class UserController {

    @Autowired //permite instanciar a classe UserRepository e usar seus metodos sem o new
    private UserRepository userRepository;
    
   @PostMapping
    public ResponseEntity<?> create(@RequestBody UserModel userModel) {
        var user = this.userRepository.findByUsername(userModel.getUsername());

        if (user != null) {
            log.warn("Tentativa de cadastro com username já existente: {}", userModel.getUsername());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já existe");
        }

       userModel.setPassword(BCrypt.withDefaults().hashToString(10, userModel.getPassword().toCharArray())); //pra criptografar a senha do usuário antes de salvar no banco de dados

        var userCreated = this.userRepository.save(userModel);
        log.info("Usuário criado com sucesso: {}", userCreated.getUsername());
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
    }
    }