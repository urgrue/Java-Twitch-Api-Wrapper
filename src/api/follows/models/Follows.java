package api.follows.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Follows {

    @JsonProperty("_total") private int total;
    private List<Follow> follows;

    @Override
    public String toString() {
        return "Follows{" +
                "total=" + total +
                ", follows=" + follows +
                '}';
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Follow> getFollows() {
        return follows;
    }

    public void setFollows(List<Follow> follows) {
        this.follows = follows;
    }
}
