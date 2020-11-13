package gkalapis.scorerui.model.api;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Objects;
import java.util.ArrayList;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "")
public class Table   {

    @SerializedName("matchday")
    private Integer matchday = null;

    @SerializedName("standing")
    private List<Team> standing = new ArrayList<Team>();

    @ApiModelProperty(value = "")
    public Integer getMatchday() {
        return matchday;
    }
    public void setMatchday(Integer matchday) {
        this.matchday = matchday;
    }

    @ApiModelProperty(value = "")
    public List<Team> getStanding() {
        return standing;
    }
    public void setStanding(List<Team> standing) {
        this.standing = standing;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Table table = (Table) o;
        return Objects.equals(matchday, table.matchday) &&
                Objects.equals(standing, table.standing);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matchday, standing);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Table {\n");

        sb.append("    matchday: ").append(toIndentedString(matchday)).append("\n");
        sb.append("    standing: ").append(toIndentedString(standing)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}

