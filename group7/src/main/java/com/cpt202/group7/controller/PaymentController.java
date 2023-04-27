//package com.cpt202.group7.controller;
//
//import com.alibaba.fastjson.JSONObject;
//import com.alipay.api.AlipayApiException;
//import com.alipay.api.AlipayClient;
//import com.alipay.api.DefaultAlipayClient;
//import com.alipay.api.internal.util.AlipaySignature;
//import com.alipay.api.request.AlipayTradePagePayRequest;
//import com.alipay.api.response.AlipayTradePagePayResponse;
//import com.cpt202.group7.entity.Order;
//import com.cpt202.group7.mapper.OrderMapper;
//import com.cpt202.group7.utils.PaymentConstants;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import java.awt.print.Printable;
//import java.io.PrintWriter;
//import java.util.HashMap;
//import java.util.Map;
//
//
//@Controller
//@RequestMapping("/customer")
//public class PaymentController {
//    @Autowired
//    private OrderMapper orderMapper;
//    @RequestMapping("/payment")
//    public String payment(@RequestParam("orderId") String orderId, HttpServletResponse servletResponse){
//        Order order = orderMapper.selectById(orderId);
//            AlipayClient alipayClient = new DefaultAlipayClient(PaymentConstants.URL,
//                    PaymentConstants.APP_ID,
//                    PaymentConstants.APP_PRIVATE_KEY,
//                    PaymentConstants.FORMAT,
//                    PaymentConstants.CHARSET,
//                    PaymentConstants.ALIPAY_PUBLIC_KEY,
//                    PaymentConstants.SIGN_TYPE);
//            AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
//            //异步接收地址
//            request.setNotifyUrl("");
//            //同步跳转地址，仅支持http/https
//            request.setReturnUrl("");
///******必传参数******/
//            JSONObject bizContent = new JSONObject();
////商户订单号，商家自定义，保持唯一性
//            bizContent.put("out_trade_no", "OrderId");
////支付金额，最小值0.01元
//            bizContent.put("total_amount", order.getTotalPrice());
////订单标题，不可使用特殊符号
//            bizContent.put("subject", "groomerService");
////电脑网站支付场景固定传值FAST_INSTANT_TRADE_PAY
//            bizContent.put("product_code", "FAST_INSTANT_TRADE_PAY");
//
//
//
//            request.setBizContent(bizContent.toString());
//            AlipayTradePagePayResponse response = alipayClient.pageExecute(request);
//            if(response.isSuccess()){
//                System.out.println(response.getBody());
//                servletResponse.setContentType("text/html;charset=utf-8");
//                PrintWriter
//            } else {
//                System.out.println("调用失败");
//            }
//
//
//    }
//
//    @RequestMapping("/payment/sync_callback")
//    public String callbackPage(HttpServletRequest servletRequest, HttpServletResponse servletResponse){
//
//
//
//
//        return "paymentSuccess";
//    }
//
//    @RequestMapping("/payment/assync_callback")
//    public String callback(HttpServletRequest servletRequest, HttpServletResponse servletResponse){
//        //TODO: update order status 验签
//        try{
//            Map<String,String[]> params=servletRequest.getParameterMap();
//            Map<String,String> map=new HashMap<>();
//            for(String s:params.keySet()){
//                map.put(s,params.get(s)[0]);
//            }
//            boolean signVerified = AlipaySignature.rsaCheckV1(map, PaymentConstants.ALIPAY_PUBLIC_KEY,
//                    PaymentConstants.CHARSET,
//                    PaymentConstants.SIGN_TYPE);
//            if(signVerified){
//                System.out.println("验签失败");
//                System.out.println(map.get("out_trade_no"));
//                //订单号查订单
//                System.out.println(map.get("trade_no"));
//                System.out.println(map.get("total_amount"));
//                //判断是否等于订单金额
//                System.out.println(map.get("trade_status"));
//
//
//
//                return "paymentSuccess";
//            }else{
//                System.out.println("验签失败");
//                return "paymentFail";
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return "paymentFail";
//    }
//
//    public String query(String outTradeNo){
//
//    }
//
//
//
//
//}
