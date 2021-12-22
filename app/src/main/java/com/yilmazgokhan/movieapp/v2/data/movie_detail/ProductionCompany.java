package com.yilmazgokhan.movieapp.v2.data.movie_detail;

import com.google.gson.annotations.SerializedName;

public class ProductionCompany {
    @SerializedName("id")
    private Integer id;
    @SerializedName("logo_path")
    private Object logoPath;
    @SerializedName("name")
    private String name;
    @SerializedName("origin_country")
    private String originCountry;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Object getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(Object logoPath) {
        this.logoPath = logoPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }
}