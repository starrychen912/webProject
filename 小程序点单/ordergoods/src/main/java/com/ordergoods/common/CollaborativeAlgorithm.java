package com.ordergoods.common;


import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by jianggc
 * 协同过滤推荐算法
 */
public class CollaborativeAlgorithm {

    /**
     * 根据所有用户订购列表获取用户推荐列表
     * @param baseUserId
     * @param allList
     */
    public static  List<String> calculate(Long baseUserId, List<HashMap> allList){
        //所有被用户订购过的餐品ID列表
        Set<Long> goodsIdSet = allList.stream().map(e -> Long.parseLong(String.valueOf(e.get("goods_id")))).collect(Collectors.toSet());
        
        List<Long> goodsIdList=new ArrayList<>();
        goodsIdList.addAll(goodsIdSet);
        //保存用户订购的餐品ID：userid-goodsId+','+goodsId
        HashMap<Long,String> userIdMap=new HashMap<>();
        //保存每个餐品订购用户数
        HashMap<Long,Long> IdUserNumMap=new HashMap<>();
        //保存baseUserId用户的餐品订购
        HashMap<Long,Integer> IdScoreMap=new HashMap<>();
        allList.forEach(e->{
            Long userId = Long.parseLong(String.valueOf(e.get("user_id")));
            Long goodsId = Long.parseLong(String.valueOf(e.get("goods_id")));;
            Integer number = Integer.parseInt(String.valueOf(e.get("number")));
            if(userId==baseUserId.intValue()){
                IdScoreMap.put(goodsId,number);
            }
            if(userIdMap.containsKey(userId)){
                String goodsIds= userIdMap.get(userId);
                goodsIds=goodsIds+","+goodsId;
                userIdMap.put(userId,goodsIds);
            }else{
                String goodsIds=new String();
                goodsIds=goodsIds+goodsId;
                userIdMap.put(userId,goodsIds);
            }
            if(IdUserNumMap.containsKey(goodsId)){
                Long userNum=IdUserNumMap.get(goodsId);
                userNum++;
                IdUserNumMap.put(goodsId,userNum);
            }else{
                IdUserNumMap.put(goodsId,1L);
            }
        });
        if(IdUserNumMap==null||IdUserNumMap.isEmpty()){
            return Collections.emptyList();
        }

        //计算i j相似度
        HashMap<String,Double> idijMap=new HashMap<>();
        for(int i=0;i<goodsIdList.size();i++){
            Long goodsIdI = goodsIdList.get(i);
            for(int j=i+1;j<goodsIdList.size();j++){
                Long goodsIdJ = goodsIdList.get(j);
                //获取i j同时被喜欢的用户数
                Integer userIdIJNumber = getUserIdIJNumber(goodsIdI, goodsIdJ, userIdMap);
                //喜欢i的用户数
                Long goodsIdINum = IdUserNumMap.get(goodsIdI);
                //喜欢j的用户数
                Long goodsIdJNum = IdUserNumMap.get(goodsIdJ);
                double sqrt = Math.sqrt(goodsIdINum * goodsIdJNum);
                //ij相似度
                BigDecimal similarityDegree = new BigDecimal(userIdIJNumber).divide(new BigDecimal(sqrt), 3, BigDecimal.ROUND_HALF_UP);
                idijMap.put(goodsIdI+","+goodsIdJ,similarityDegree.doubleValue());
            }
        }
        if(idijMap.isEmpty()){
            return Collections.emptyList();
        }
        //根据用户看到的每个餐品进行相似度推荐
        Set<Long> keySet = IdScoreMap.keySet();
        //保存每个推荐餐品goodsId与推荐值
        Map<String,Object> recommendScore=new HashMap<>();
        for(Long key:keySet){
            //用户对key的订购
            Integer keyScore = IdScoreMap.get(key);
            //recommendMap里面保存的是用户没有看过的goodsId与相似度
            Map<String, Object> recommendMap = getRecommendByFilmId(key, idijMap, IdScoreMap);
            recommendMap.keySet().forEach(e->{
                double degree = (double) recommendMap.get(e);
                if(recommendScore.containsKey(e)){
                    double oldVal = (double) recommendScore.get(e);
                    double newVal=oldVal+degree*keyScore;
                    recommendScore.put(e,newVal);
                }else{
                    double newVal=degree*keyScore;
                    recommendScore.put(e,newVal);
                }
            });
        }
        //取排名前6返回ID列表
        if(recommendScore.size()<=6){
            List<String> goodsIdListRecommend = recommendScore.keySet().parallelStream().collect(Collectors.toList());
            return goodsIdListRecommend;
        }else{
            List<String> goodsIdListRecommend=new ArrayList<>();
            double sexthDegree = getSexthDegree(recommendScore);
            for(String e :recommendScore.keySet()){
                double degree = (double) recommendScore.get(e);
                if(degree>=sexthDegree){
                    goodsIdListRecommend.add(e);
                }
                if(goodsIdListRecommend.size()==6){
                    break;
                }
            }
            return goodsIdListRecommend;
        }
    }

    //获取最后推荐列表中第六名的推荐值
    private static double getSexthDegree( Map<String,Object> recommendScore){
        ArrayList<Map.Entry<String, Object>> arrayList = new ArrayList<>(recommendScore.entrySet());
        Collections.sort(arrayList, new Comparator<Map.Entry<String, Object>>() {
            @Override
            public int compare(Map.Entry<String, Object> o1, Map.Entry<String, Object> o2) {
                return o2.getValue().toString().compareTo(o1.getValue().toString());
            }
        });
        if(arrayList.size()<=6){
            Map.Entry<String, Object> objectEntry = arrayList.get(arrayList.size()-1);
            return Double.parseDouble(objectEntry.getValue().toString());
        }
        Map.Entry<String, Object> objectEntry = arrayList.get(5);
        return Double.parseDouble(objectEntry.getValue().toString());
    }

    /**
     * 返回goodsId的推荐列表
     * @param goodsId 用户看过的餐品ID
     * @param idijMap 相似度
     * @param IdScoreMap 用户看过的餐品与订购
     * @return
     */
    private static Map<String,Object> getRecommendByFilmId(Long goodsId,HashMap<String,Double> idijMap,HashMap<Long,Integer> IdScoreMap){
        //从idijMap中获取只和goodsId有相似度的集合
        HashMap<String,Object> onlyMap=new HashMap<>();
        idijMap.keySet().forEach(e->{
            if(e.contains(goodsId.toString())){
                onlyMap.put(e,idijMap.get(e));
            }
        });
        //排序取第十名相似度(如果不够十个返回最后一名相似度)
        double tenthScore = getTenthScore(onlyMap);

        //取10个最高相似度的餐品
        Map<String,Object> tenMap=new HashMap<>();
        for(String e :onlyMap.keySet()){
            double degree= (double) onlyMap.get(e);
            if(degree>=tenthScore){
                tenMap.put(e,degree);
                if(tenMap.size()==10){
                    break;
                }
            }
        }

        //从10个里面获取用户没有订购过的
        Map<String,Object> recommendMap=new HashMap<>();
        tenMap.keySet().forEach(e->{
            String[] split = e.split(",");
            String currFilmId=null;
            if(split[0].equals(goodsId.toString())){
                currFilmId=split[1];
            }else{
                currFilmId=split[0];
            }
            if(!IdScoreMap.containsKey(Long.parseLong(currFilmId))){
                recommendMap.put(currFilmId,tenMap.get(e));
            }
        });

        return recommendMap;
    }
    //获取相似度中第十名的分值
    private static double getTenthScore(HashMap<String,Object> onlyMap){
        ArrayList<Map.Entry<String, Object>> arrayList = new ArrayList<>(onlyMap.entrySet());
        Collections.sort(arrayList, new Comparator<Map.Entry<String, Object>>() {
            @Override
            public int compare(Map.Entry<String, Object> o1, Map.Entry<String, Object> o2) {
                return o2.getValue().toString().compareTo(o1.getValue().toString());
            }
        });
        if(arrayList.size()<=10){
            Map.Entry<String, Object> objectEntry = arrayList.get(arrayList.size()-1);
            return Double.parseDouble(objectEntry.getValue().toString());
        }
        Map.Entry<String, Object> objectEntry = arrayList.get(9);
        return Double.parseDouble(objectEntry.getValue().toString());
    }

    //获取同时喜欢I J餐品的用户数
    private static Integer getUserIdIJNumber(Long goodsIdI,Long goodsIdJ,HashMap<Long,String> userIdMap){
        int count=0;
        Set<Long> keySet = userIdMap.keySet();
        for(Long key:keySet){
            String goodsIds = userIdMap.get(key);
            if(goodsIds.contains(goodsIdI+"")&&goodsIds.contains(goodsIdJ+"")){
                count++;
            }
        }
        return count;

    }
}
