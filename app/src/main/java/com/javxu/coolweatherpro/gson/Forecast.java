package com.javxu.coolweatherpro.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Jav-Xu on 2016/12/11.
 */

public class Forecast {
    public String date;
    @SerializedName("cond")
    public More more;
    @SerializedName("tmp")
    public Temperature temperature;

    public class More {
        @SerializedName("txt_d")
        public String info;
    }

    public class Temperature {
        @SerializedName("max")
        public String max;
        @SerializedName("min")
        public String min;
    }
}
