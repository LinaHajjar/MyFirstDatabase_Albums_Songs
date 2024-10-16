public class RecordCompany {
    private int company_id;
    private String company_name;
    private String company_address;
    private String company_phone_number;

    public RecordCompany(int company_id, String company_name, String company_address, String company_phone_number) {
        this.company_id = company_id;
        this.company_name = company_name;
        this.company_address = company_address;
        this.company_phone_number = company_phone_number;
    }

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getCompany_address() {
        return company_address;
    }

    public void setCompany_address(String company_address) {
        this.company_address = company_address;
    }

    public String getCompany_phone_number() {
        return company_phone_number;
    }

    public void setCompany_phone_number(String company_phone_number) {
        this.company_phone_number = company_phone_number;
    }
}
