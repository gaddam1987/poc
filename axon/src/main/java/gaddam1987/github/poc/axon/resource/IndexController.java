package gaddam1987.github.poc.axon.resource;

import gaddam1987.github.poc.axon.command.CreditAccountCommand;
import gaddam1987.github.poc.axon.command.DebitAccountCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {
    @Autowired
    private CommandGateway commandGateway;

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("name", "dadepo");
        return "index";
    }


    @RequestMapping("/debit")
    @Transactional
    @ResponseBody
    public void doDebit(@RequestParam("acc") String accountNumber, @RequestParam("amount") double amount) {
        DebitAccountCommand debitAccountCommandCommand = new DebitAccountCommand(accountNumber, amount);
        commandGateway.send(debitAccountCommandCommand);
    }

    @RequestMapping("/credit")
    @Transactional
    @ResponseBody
    public void doCredit(@RequestParam("acc") String accountNumber, @RequestParam("amount") double amount) {
        CreditAccountCommand creditAccountCommandCommand = new CreditAccountCommand(accountNumber, amount);
        commandGateway.send(creditAccountCommandCommand);
    }
}
