package com.soul.androidcompilptions.rxandretrofi.contract;

import com.soul.androidcompilptions.rxandretrofi.entity.GanK;

import java.util.List;

/**
 * * @author soul
 *
 * @项目名:Compilations
 * @包名: com.soul.androidcompilptions.rxandretrofi.contract
 * @作者：祝明
 * @描述：干货的契约类
 * @创建时间：2017/1/19 16:17
 */

public class GanKContract {

    public interface GanKView {
        void loadDataSucceed(List<GanK> ganKList);

    }

    public interface GanKPresenter {
        /**
         * 获取干货的数据
         * @param year
         * @param month
         * @param day
         */
        void loadGanKData(int year,int month,int day);

    }
}
