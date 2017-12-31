package cyan.nazgul.servant.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by DreamInSun on 2017/12/25.
 */
public class DefaultConfig {

    /*========== Properties ==========*/
    @JsonProperty("project")
    private ProjectConfig project;

    /*========== Getter & Setter ==========*/
    public ProjectConfig getProject() {
        return project;
    }

    public void setProject(ProjectConfig project) {
        this.project = project;
    }
}
