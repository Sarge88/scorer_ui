package gkalapis.scorerui.model.api;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "")
public class Result   {

    @SerializedName("goalsHomeTeam")
    private String goalsHomeTeam = null;

    @SerializedName("goalsAwayTeam")
    private String goalsAwayTeam = null;

    @SerializedName("halftime")
    private Result halftime = null;

    @ApiModelProperty(required = true, value = "")
    public String getGoalsHomeTeam() {
        return goalsHomeTeam;
    }
    public void setGoalsHomeTeam(String goalsHomeTeam) {
        this.goalsHomeTeam = goalsHomeTeam;
    }

    @ApiModelProperty(required = true, value = "")
    public String getGoalsAwayTeam() {
        return goalsAwayTeam;
    }
    public void setGoalsAwayTeam(String goalsAwayTeam) {
        this.goalsAwayTeam = goalsAwayTeam;
    }

    @ApiModelProperty(value = "")
    public Result getHalftime() {
        return halftime;
    }
    public void setHalftime(Result halftime) {
        this.halftime = halftime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Result result = (Result) o;
        return Objects.equals(goalsHomeTeam, result.goalsHomeTeam) &&
                Objects.equals(goalsAwayTeam, result.goalsAwayTeam) &&
                Objects.equals(halftime, result.halftime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(goalsHomeTeam, goalsAwayTeam, halftime);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Result {\n");

        sb.append("    goalsHomeTeam: ").append(toIndentedString(goalsHomeTeam)).append("\n");
        sb.append("    goalsAwayTeam: ").append(toIndentedString(goalsAwayTeam)).append("\n");
        sb.append("    halftime: ").append(toIndentedString(halftime)).append("\n");
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
