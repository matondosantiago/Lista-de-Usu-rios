package ao.matondosantiago.todolist.user;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "tb_users") // nome da tabela no DB H2
public class UserModel {
    @Id //primary key
    @GeneratedValue(generator = "UUID") // para o proprio DB gerar o ID
    private UUID id;

    private String name;
    @Column(unique = true)
    private String username;
    private String password;

    @CreationTimestamp // para o mostrar a data de criação
    private LocalDateTime createdAt; //
}
