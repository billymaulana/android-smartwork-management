package com.smartworks.smartwork.base.config;

import com.smartworks.smartwork.base.config.request.RequestApply;
import com.smartworks.smartwork.base.config.response.GeneralCountAbsenResponse;
import com.smartworks.smartwork.base.config.response.GeneralCountResponse;
import com.smartworks.smartwork.base.config.response.GeneralResponse;
import com.smartworks.smartwork.base.config.response.GeneralResponseLogin;
import com.smartworks.smartwork.base.config.response.candidate.CandidateResponse;
import com.smartworks.smartwork.base.config.response.token.TokenResponse;
import com.smartworks.smartwork.base.config.response.work.WorkResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface SmartworkAPI {

    String apiloginpath                   = "auth/login";
    String apiRegistpath                  = "auth/register";
    String apiAddTaskpath                 = "user/task";
    String apiUpdateTaskpath              = "user/task";
    String apiCheckTaskpath               = "admin/cekTask";
    String apiAbsenpath                   = "user/absen";
    String checktoken                     = "admin/cekRole";
    String tasktoken                      = "admin/cekTask";
    String Absentoken                     = "admin/cekAbsen";
    String candidate                      = "admin/cekCareer";
    String deletetask                     = "user/task";
    String uploadCv                       = "user/career";


    @Headers("Content-Type: application/json")
    @GET(apiloginpath)
    Call<GeneralResponseLogin> login(@Query("email") String email,
                                     @Query("password") String password) ;

    @Headers("Content-Type: application/json")
    @GET(apiRegistpath)
    Call<GeneralResponse> regist(  @Query("nama") String nama,
                                   @Query("email") String email,
                                   @Query("password") String password,
                                   @Query("role") String role);

    @Headers("Content-Type: application/json")
    @GET(apiAddTaskpath)
    Call<GeneralResponse> Task    (@Query("token") String token,
                                   @Query("action") String action,
                                   @Query("nama") String nama,
                                   @Query("deskripsi") String deskripsi,
                                   @Query("status") String proses,
                                   @Query("created") String created);

    @Headers("Content-Type: application/json")
    @GET(apiCheckTaskpath)
    Call<WorkResponse> GetTask (@Query("token") String token,
                                @Query("status") String status);

    @Headers("Content-Type: application/json")
    @GET(apiUpdateTaskpath)
    Call<GeneralResponse> TaskUpdate  (@Query("token") String token,
                                       @Query("action") String action,
                                       @Query("nama") String nama,
                                       @Query("deskripsi") String deskripsi,
                                       @Query("status") String proses,
                                       @Query("updated") String updated,
                                       @Query("id_task") String id_task);

    @Headers("Content-Type: application/json")
    @GET(apiAbsenpath)
    Call<GeneralResponse> Absen (@Query("token") String token,
                                 @Query("ket") String ket);

    @Headers("Content-Type: application/json")
    @GET(checktoken)
    Call<TokenResponse> checktoken (@Query("token") String token);

    @Headers("Content-Type: application/json")
    @GET(tasktoken)
    Call<GeneralCountResponse> countTask (@Query("token") String token);

    @Headers("Content-Type: application/json")
    @GET(Absentoken)
    Call<GeneralCountAbsenResponse> countAbsen (@Query("token") String token);

    @Headers("Content-Type: application/json")
    @GET(candidate)
    Call<CandidateResponse> Candidate (@Query("token") String token);

    @Headers("Content-Type: application/json")
    @GET(deletetask)
    Call<GeneralResponse> TaskDelete  (@Query("token") String token,
                                       @Query("action") String action,
                                       @Query("deleted") String deleted,
                                       @Query("id_task") String id_task);

    @Multipart
    @POST(uploadCv)
    Call<GeneralResponse> upload  (@Body RequestApply requestApply);


}
