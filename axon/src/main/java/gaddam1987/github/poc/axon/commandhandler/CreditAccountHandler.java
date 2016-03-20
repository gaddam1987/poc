package gaddam1987.github.poc.axon.commandhandler;

import gaddam1987.github.poc.axon.command.CreditAccountCommand;
import gaddam1987.github.poc.axon.model.Account;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreditAccountHandler {

    private Repository repository;

    @Autowired
    public CreditAccountHandler(Repository repository) {
        this.repository = repository;
    }

    @CommandHandler
    public void handle(CreditAccountCommand creditAccountCommand){
        Account accountToCredit = (Account) repository.load(creditAccountCommand.getAccount());
        accountToCredit.credit(creditAccountCommand.getAmount());
    }
}
