package com.example.demo.job;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.domain.Data;
import com.example.demo.domain.General;
import com.example.demo.utils.OkHttpClientUtil;
import okhttp3.Response;

public class R6Server {

    private  static String id_url = "https://r6stats.com/api/player-search/";

    private  static String data_url = "https://r6stats.com/api/stats/";

    public static String getUserId(String gameName) throws Exception {

        Response response = OkHttpClientUtil.getInstance().getData(id_url+gameName+"/pc");

        String jsonString = response.body().string();
        JSONObject jsonMap;
        try{
            jsonMap = JSON.parseObject(jsonString.substring(1,jsonString.length()-1));
        }catch (JSONException e){
            return null;
        }

        System.out.println(jsonMap);

        return jsonMap.getString("uplay_id");
    }

    public static String getData(String uplayId)throws Exception {
        System.out.println("!---------------------------------");
        Response response = OkHttpClientUtil.getInstance().getData(data_url+uplayId);
        String jsonString = response.body().string();
        System.out.println(jsonString);
        Data data = JSONObject.parseObject(jsonString,Data.class);
        System.out.println(data);
        String fs = "["+ data.getProgression().getLevel()+"]"+data.getUsername()+"-刷包概率："+data.getProgression().getLootbox_probability()/100.0+"% 的战绩如下：" +
                "\n总计：" +
                "\nKD(击杀/死亡)\n"+data.getStats().get(0).getGeneral().getKd()+
                "("+data.getStats().get(0).getGeneral().getKills()+
                "/"+data.getStats().get(0).getGeneral().getDeaths()+")" +
                "\n近战/穿透/致盲："+data.getStats().get(0).getGeneral().getMelee_kills()+"/"+data.getStats().get(0).getGeneral().getPenetration_kills()+"/"+data.getStats().get(0).getGeneral().getBlind_kills()+
                "\n胜负比(胜/负)\n"+data.getStats().get(0).getGeneral().getWl()+
                "("+data.getStats().get(0).getGeneral().getWins()+
                "/"+data.getStats().get(0).getGeneral().getLosses()+")" +
                "\n休闲："+
                "\nKD(击杀/死亡)\n"+data.getStats().get(0).getQueue().getCasual().getKd()+
                "("+data.getStats().get(0).getQueue().getCasual().getKills()+
                "/"+data.getStats().get(0).getQueue().getCasual().getDeaths()+")"+
                "\n胜负比(胜/负)\n"+data.getStats().get(0).getQueue().getCasual().getWl()+
                "("+data.getStats().get(0).getQueue().getCasual().getWins()+
                "/"+data.getStats().get(0).getQueue().getCasual().getLosses()+")" +
                "\n排位："+
                "\nKD(击杀/死亡)\n"+data.getStats().get(0).getQueue().getRanked().getKd()+
                "("+data.getStats().get(0).getQueue().getRanked().getKills()+
                "/"+data.getStats().get(0).getQueue().getRanked().getDeaths()+")"+
                "\n胜负比(胜/负)\n"+data.getStats().get(0).getQueue().getRanked().getWl()+
                "("+data.getStats().get(0).getQueue().getRanked().getWins()+
                "/"+data.getStats().get(0).getQueue().getRanked().getLosses()+")";

        return fs;
    }
}
