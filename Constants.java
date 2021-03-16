// constants used in all program
public enum Constants {

    // file const
    MARKET_FILE_NAME("stock_market"),
    FILE_ACC_EXTENTION(".account"),
    FILE_MARK_EXTENTION(".market"),

    // admin const
    ADMIN_KEY("admin123"),
    ADMIN_SUFFIX(".admin");

    private final String full;


    private Constants(String full) {
        this.full = full;
    }

    public String getFull() {
        return full;
    }
}
