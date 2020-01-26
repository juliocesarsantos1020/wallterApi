package br.com.jcsistemas.wallter.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto implements Serializable {

	private Long id;
	@Email(message = "Email inválido")
	private String email;
	@Length(min = 3, max = 50, message = "O nome deve conter de 3 a 50 caracteres")
	private String name;
	@NotNull
	@Length(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
	private String password;
}
