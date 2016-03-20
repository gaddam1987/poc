package gaddam1987.github.poc.axon.commandhandler;

import gaddam1987.github.poc.axon.command.DebitAccountCommand;
import gaddam1987.github.poc.axon.model.Account;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class DebitAccountHandler {

    private Repository repository;

    @Autowired
    public DebitAccountHandler(Repository repository) {
        this.repository = repository;
    }

    @CommandHandler
    public void handle(DebitAccountCommand debitAccountCommandCommand){
        Account accountToDebit = (Account) repository.load(debitAccountCommandCommand.getAccount());
        accountToDebit.debit(debitAccountCommandCommand.getAmount());
    }
}
