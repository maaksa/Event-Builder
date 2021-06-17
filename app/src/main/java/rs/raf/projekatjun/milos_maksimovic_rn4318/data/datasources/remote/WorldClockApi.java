package rs.raf.projekatjun.milos_maksimovic_rn4318.data.datasources.remote;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rs.raf.projekatjun.milos_maksimovic_rn4318.data.models.api.EasternStandardTimeModel;

public interface WorldClockApi {

    @GET("/api/timezone/{region}/{city}")
    Call<EasternStandardTimeModel> getEasternStandardTimeForRegionAndCity(@Path(value = "region") String myRegion, @Path(value = "city") String myCity);

}
