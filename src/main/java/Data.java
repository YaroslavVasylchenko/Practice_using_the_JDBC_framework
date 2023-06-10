public class Data {

    private int id;
    private String uuid;
    private String type;
    private int carrier_offer_id;
    private long msisdn;

    public Data(int id, String uuid, String type, int carrier_offer_id, long msisdn) {
        this.id = id;
        this.uuid = uuid;
        this.type = type;
        this.carrier_offer_id = carrier_offer_id;
        this.msisdn = msisdn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCarrier_offer_id() {
        return carrier_offer_id;
    }

    public void setCarrier_offer_id(int carrier_offer_id) {
        this.carrier_offer_id = carrier_offer_id;
    }

    public long getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(long msisdn) {
        this.msisdn = msisdn;
    }

}