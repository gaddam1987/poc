package gaddam1987.github.poc.axon.command;


public class CreditAccountCommand {
    private final String account;
    private final Double amount;

    public CreditAccountCommand(String account, Double amount) {
        this.account = account;
        this.amount = amount;
    }

    public Double getAmount() {
        return amount;
    }

    public String getAccount() {
        return account;
    }
}
