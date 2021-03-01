package guru.springframework;

public class Money implements Expression {
    protected int amount;
    protected String currency;
    public Money(int amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public String currency() {
        return currency;
    }


    public static Money dollar(int amount) {
        return new Money(amount,"USD");
    }

    public static Money franc(int amount){
        return new Money(amount,"CHF");
    }

    public boolean equals(Object object) {
        Money money = (Money) object;
        return amount == money.amount
                && this.currency == money.currency;
    }

    @Override
    public String toString() {
        return "Money{" +
                "amount=" + amount +
                ", currency='" + currency + '\'' +
                '}';
    }

    public Money times(int multiplier){
        return new Money(amount * multiplier,this.currency);
    }

    public Expression plus(Expression addend){
        return new Sum(this, addend);
    }
    @Override
    public Money reduce(Bank bank,String to){
        //int rate = (currency.equals("CHF") && to.equals("USD")) ? 2:1;
        return new Money(amount/bank.rate(this.currency,to),to);
    }
}
