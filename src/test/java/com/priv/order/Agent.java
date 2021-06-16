package com.priv.order;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Json
 * @date 2021/6/15 14:34
 */
public class Agent {

    private static final Logger logger = LoggerFactory.getLogger(Agent.class);

    public static void main(String[] args) {
        logger.info("1.0.用户伽汇公司下单购买迪士尼成人票");

        // 前置判断:商品限制策略 代理商商品权限 商品上下架
        logger.info("2.1.伽汇公司:保存订单基本信息");
        // 目前公式:购买数量 * 销售金额 * 优惠金额 / 订单总金额
        logger.info("2.2.伽汇公司:计算优惠金额平摊到产品");  // 除不尽如何处理？
        logger.info("2.3.伽汇公司:请求供应商占用库存");
        logger.info("2.4.伽汇公司:保存各项订单详细信息");
        logger.info("2.5.伽汇公司:非代理商订单插入订单支付提醒轮询"); // 代理商订单为何不用?

        logger.info("3.1.伽汇公司:订单发起支付操作");
        logger.info("3.2.伽汇公司:判断订单是否是代理商支付"); // 通过第三方订单编号判断
        logger.info("3.3.伽汇公司:订单支付成功");
        logger.info("3.4.伽汇公司:插入备货轮询记录");
        logger.info("3.5.伽汇公司:备货完成,插入发货轮询记录");
        logger.info("3.6.伽汇公司:供货商发货,得到发货凭证(entityUID)"); // entityUID是自造? 如果是代理订单则自助发货
        logger.info("3.7.伽汇公司:通过发货凭证进行仓库发货");
        logger.info("3.8.伽汇公司:有第三方订单编号,则给代理商发通知轮询"); // 伽汇公司第三方订单编号哪来的?
    }
}
