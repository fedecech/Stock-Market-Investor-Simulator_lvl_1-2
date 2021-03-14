public enum Constants {
    EXTENDED("extended", "e", ""),
    AUTO("auto", "a", ""),
    PRINT("print", "p", ""),
    BUY("buy", "b", ""),
    SELL("sell", "s", ""),
    HELP("help", "h", ""),
    ADMIN_KEY("admin123", "", ""),
    ADMIN_SUFFIX(".admin@admin", "", ""),
    DELETE("delete", "d", "");
    



    private final String full;
    private final String abbr;
    private final String desc;

    private Constants(String full, String abbr, String desc) {
        this.full = full;
        this.abbr = abbr;
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public String getAbbr() {
        return abbr;
    }

    public String getFull() {
        return full;
    }
}
