package br.com.davireis.projectUsers.producers;

import br.com.davireis.projectUsers.Dto.EmailDTO;
import br.com.davireis.projectUsers.entity.User;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.UUID;

//Dentro dessa classe que vai ser feita o publicacao das mensagens para o broker

@Component
public class UserProducer {

    final RabbitTemplate rabbitTemplate;

    public UserProducer(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
    }


    @Value(value = "${broker.queue.email.name}")
    private String routingKey;

    public void publishMessageEmail(User user){
        var emailDTO = new EmailDTO();
        emailDTO.setUserId(user.getId());
        emailDTO.setEmailTo(user.getEmail());
        emailDTO.setSubject("Cadastro realizado com sucesso!");
        emailDTO.setText(user.getName() + "seja bem vindo(a)! \nAgradecemos o seu cadastro, aproveite!");

        rabbitTemplate.convertAndSend("", routingKey, emailDTO);
    }

    public void changePasswordMessageEmail(UUID id, String email){
        var emailDTO = new EmailDTO();
        emailDTO.setUserId(id);
        emailDTO.setEmailTo(email);
        emailDTO.setSubject("Senha alterada com sucesso!");
        emailDTO.setText("Senha alterada com sucesso. Caso não tenha sido você clique aqui.");

        rabbitTemplate.convertAndSend("",routingKey,emailDTO);
    }
}
