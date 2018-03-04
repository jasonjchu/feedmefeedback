package starterhacks.jwj.starterhacks;

/**
 * Created by jason on 04/03/18.
 */

public class Coupon {
    private String description;
    private String headline;
    private String code;

    public String getDescription(){
        return description;
    }

    public String getHeadline(){
        return headline;
    }

    public String getCode(){
        return code;
    }

    public void setDescription(String desc){
        description = desc;
    }

    public void setHeadline(String head){
        headline = head;
    }

    public void setCode(String c){
        code = c;
    }
}
