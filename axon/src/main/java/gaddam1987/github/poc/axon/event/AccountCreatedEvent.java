package gaddam1987.github.poc.axon.event;

public class AccountCreatedEvent {

    private final String accountNo;

    public AccountCreatedEvent(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getAccountNo() {
        return accountNo;
    }
}
