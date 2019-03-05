package com.imooc.utils;

import com.imooc.VO.ResultVO;

/**
 * @program: springboot_wx_sell
 * @description: ResultVO工具对象
 * @author: Gu
 * @create: 2019-03-06 00:04
 **/

public class ResultVOUtil {

    public static ResultVO success(Object object){
        ResultVO resultVO=new ResultVO();
        resultVO.setData(object);
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        return resultVO;
    }

    public static ResultVO success(){
        return success(null);
    }

    public static ResultVO error(Integer code,String msg){
        ResultVO resultVO=new ResultVO();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        return resultVO;
    }
}
