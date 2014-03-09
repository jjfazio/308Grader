package model.spreadsheet;

public enum AssignView
{
    POINTS("Points (Default)"), PERCENTAGES("Percentages"), SYMBOLS("Symbols");
    
    private String val;
    
    private AssignView(String val)
    {
        this.val = val;
    }
    
    @Override
    public String toString() {
        return val;
    }
}
