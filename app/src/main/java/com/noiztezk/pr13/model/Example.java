package com.noiztezk.pr13.model;

/**
 * Created by noiz354 on 4/27/16.
 */
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Example {

    @SerializedName("version")
    @Expose
    private String version;
    @SerializedName("version_created")
    @Expose
    private String versionCreated;
    @SerializedName("created_by")
    @Expose
    private String createdBy;
    @SerializedName("dzkir")
    @Expose
    private List<Dzikir> dzkir = new ArrayList<Dzikir>();

    /**
     *
     * @return
     * The version
     */
    public String getVersion() {
        return version;
    }

    /**
     *
     * @param version
     * The version
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     *
     * @return
     * The versionCreated
     */
    public String getVersionCreated() {
        return versionCreated;
    }

    /**
     *
     * @param versionCreated
     * The version_created
     */
    public void setVersionCreated(String versionCreated) {
        this.versionCreated = versionCreated;
    }

    /**
     *
     * @return
     * The createdBy
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     *
     * @param createdBy
     * The created_by
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     *
     * @return
     * The dzkir
     */
    public List<Dzikir> getDzkir() {
        return dzkir;
    }

    /**
     *
     * @param dzkir
     * The dzkir
     */
    public void setDzkir(List<Dzikir> dzkir) {
        this.dzkir = dzkir;
    }

}
