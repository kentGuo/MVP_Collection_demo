package com.fg.mvp_collection_demo.module.login.module;

import com.fg.mvp_collection_demo.base.BaseResponse;
import com.fg.mvp_collection_demo.base.mvp.BaseModel;
import com.fg.mvp_collection_demo.bean.UserInfo;
import com.fg.mvp_collection_demo.constant.APIConstant;
import com.fg.mvp_collection_demo.module.login.contract.LoginContract;
import com.fg.mvp_collection_demo.network.APIService;

import java.util.Map;

import io.reactivex.Observable;

public class LoginModel extends BaseModel implements LoginContract.IModelContract {
    private APIService service;
      public  LoginModel(){
          service=createService(APIService.class);
      }
    @Override
    public Observable<BaseResponse> login(String code, String psd) {
          String url= APIConstant.LOGIN;
        Map<String,String> map=createBaseParamMap();
        map.put("Mobile","18856422586");
        map.put("Password","123456");

        return service.login(url,map);
    }

    @Override
    public Observable<BaseResponse<UserInfo>> getUserInfo() {
          String url=APIConstant.GET_USER_INFO;
          Map<String,String> map=createBaseParamMap();
          map.put("Mobile","18856422586");
        return service.getUserInfo(url,map);
    }
}
