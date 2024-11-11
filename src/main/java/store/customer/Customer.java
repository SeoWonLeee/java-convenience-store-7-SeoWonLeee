package store.customer;

public class Customer {
    private final boolean isMember;

    public Customer(boolean isMember) {
        this.isMember = isMember;
    }

    public boolean isMember() {
        return isMember;
    }
}
