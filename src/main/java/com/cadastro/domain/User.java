package com.cadastro.domain;

import com.cadastro.dtos.UserDto;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.bind.annotation.RequestBody;

@Entity(name ="users")
@Table(name = "users")
@EqualsAndHashCode(of ="id")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String document;
    private Boolean active;

    public User(UserDto userDto){
        this.id = userDto.id();
        this.firstName = userDto.firstName();
        this.lastName = userDto.lastName();
        this.document = userDto.document();
        this.active = true;

    }
}
