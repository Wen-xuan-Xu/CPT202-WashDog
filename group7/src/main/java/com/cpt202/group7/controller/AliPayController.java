package com.cpt202.group7.controller;

import cn.hutool.json.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cpt202.group7.entity.Order;
import com.cpt202.group7.mapper.OrderMapper;
import com.cpt202.group7.utils.PaymentConstants;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.bouncycastle.asn1.x500.style.RFC4519Style.cn;

@RestController
@RequestMapping({"/customer/alipay"})
public class AliPayController {
    @Autowired
    private OrderMapper orderMapper;

    public AliPayController() {
    }

    @GetMapping({"/pay"})
    public void pay(@RequestParam String OrderId, HttpServletResponse httpResponse) throws Exception {
        AlipayClient alipayClient = new DefaultAlipayClient(
                PaymentConstants.URL,
                PaymentConstants.APP_ID,
                PaymentConstants.APP_PRIVATE_KEY,
                PaymentConstants.FORMAT,
                PaymentConstants.CHARSET,
                PaymentConstants.ALIPAY_PUBLIC_KEY,
                PaymentConstants.SIGN_TYPE);

        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("orderId", OrderId);
        Order order = orderMapper.selectOne(queryWrapper);
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();



//服务器用的返回地址
        String returnUrl = "http://121.41.226.104:8080/" + order.getUserId() + "/orderHistory";
        //本地用的返回地址
//        String returnUrl = "http://4hy6tn.natappfree.cc/customer/" + order.getUserId() + "/orderHistory";

        request.setNotifyUrl(PaymentConstants.NOTIFY_URL);
        request.setReturnUrl(returnUrl);
        JSONObject bizContent = new JSONObject();

        bizContent.set("out_trade_no", order.getOrderId());
        bizContent.set("total_amount", order.getTotalPrice());
        bizContent.set("product_code", "FAST_INSTANT_TRADE_PAY");
        bizContent.set("subject", "CPT202Group7");


        request.setBizContent(bizContent.toString());
        String form = "";
        try {
            form = alipayClient.pageExecute(request).getBody(); // 调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        httpResponse.setContentType("text/html;charset=" + PaymentConstants.CHARSET);
        httpResponse.getWriter().write(form);// 直接将完整的表单html输出到页面
        httpResponse.getWriter().flush();
        httpResponse.getWriter().close();
    }

    @PostMapping("/notify_u")  // 注意这里必须是POST接口
    public String payNotify(HttpServletRequest request) throws Exception {
        System.out.println("执行？？？？？");

        if (request.getParameter("trade_status").equals("TRADE_SUCCESS")) {
            System.out.println("=========支付宝异步回调========");

            Map<String, String> params = new HashMap<>();
            Map<String, String[]> requestParams = request.getParameterMap();
            for (String name : requestParams.keySet()) {
                params.put(name, request.getParameter(name));
                // System.out.println(name + " = " + request.getParameter(name));
            }

            String outTradeNo = params.get("out_trade_no");
            String gmtPayment = params.get("gmt_payment");
            String alipayTradeNo = params.get("trade_no");

            String sign = params.get("sign");
            String content = AlipaySignature.getSignCheckContentV1(params);
            boolean checkSignature = AlipaySignature.rsa256CheckContent(content, sign, PaymentConstants.ALIPAY_PUBLIC_KEY, "UTF-8"); // 验证签名
            // 支付宝验签
            if (checkSignature) {
                // 验签通过
                System.out.println("交易名称: " + params.get("subject"));
                System.out.println("交易状态: " + params.get("trade_status"));
                System.out.println("支付宝交易凭证号: " + params.get("trade_no"));
                System.out.println("商户订单号: " + params.get("out_trade_no"));
                System.out.println("交易金额: " + params.get("total_amount"));
                System.out.println("买家在支付宝唯一id: " + params.get("buyer_id"));
                System.out.println("买家付款时间: " + params.get("gmt_payment"));
                System.out.println("买家付款金额: " + params.get("buyer_pay_amount"));

                // 查询订单
                QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("orderId", outTradeNo);
                Order orders = orderMapper.selectOne(queryWrapper);

                if (orders != null) {
                    orders.setState("PAID");
                    orderMapper.updateById(orders);
                }
            }
        }
        return "success";
    }
}