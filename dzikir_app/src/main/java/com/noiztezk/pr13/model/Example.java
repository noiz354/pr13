package com.noiztezk.pr13.model;

/**
 * Created by noiz354 on 4/27/16.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Example {


    @SerializedName("version")
    @Expose
    String version;
    @SerializedName("version_created")
    @Expose
    List<String> versionCreated = new ArrayList<>();
    @SerializedName("created_by")
    @Expose
    String createdBy;
    @SerializedName("dzkir")
    @Expose
    List<Dzikir> dzkir = new ArrayList<Dzikir>();

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

    public List<String> getVersionCreated() {
        return versionCreated;
    }

    public void setVersionCreated(List<String> versionCreated) {
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
