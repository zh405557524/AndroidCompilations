package com.soul.androidcompilptions.rxandretrofi.ui.gan;

import com.soul.androidcompilptions.rxandretrofi.entity.GanK;
import com.soul.androidcompilptions.rxandretrofi.ui.meizi.MeiZhiContract;
import com.soul.library.base.BasePresenter;
import com.soul.library.mvp.IView;

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

public interface GanKContract {

    interface GanKView extends IView {
        void loadDataSucceed(List<GanK> ganKList);

    }


    abstract class GanKPresenter extends BasePresenter<GanKView, MeiZhiContract.Model> {
        /**
         * 获取干货的数据
         *
         * @param year
         * @param month
         * @param day
         */
        abstract void loadGanKData(int year, int month, int day);

    }
}
