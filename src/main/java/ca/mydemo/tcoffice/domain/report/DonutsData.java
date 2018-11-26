package ca.mydemo.tcoffice.domain.report;


public class DonutsData implements Comparable<DonutsData> {
    private String currency;
    private int total;
    private long value;
    private String label;

    public DonutsData(String currency, int total) {
        setCurrency(currency);
        setTotal(total);
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public int compareTo(DonutsData o) {

        int result = 0;
        if (value > o.getValue()) {
            result = -1;
        } else if (value == o.getValue()) {
            result = 0;
        } else if (value < o.getValue()) {

            result = 1;
        }

        return result;
    }
}
