///**
// * 
// */
//package com.zek.doorstep1.camel;
//
//import org.apache.camel.builder.RouteBuilder;
//import org.springframework.stereotype.Component;
//
///**
// * @author EK
// *
// */
//@Component
//public class CamelRouter extends RouteBuilder {
//
//    @Override
//    public void configure() {
//        from("direct:sayHello").routeId("hello")
//            .transform().method("myBean", "saySomething")
//            .filter(simple("${body} contains 'foo'"))
//                .to("log:foo")
//            .end()
//            .to("stream:out");
//    }
///* This was the default Route configured
//    @Override
//    public void configure() {
//        from("timer:hello?period={{timer.period}}").routeId("hello")
//                .transform().method("myBean", "saySomething")
//                .filter(simple("${body} contains 'foo'"))
//                .to("log:foo")
//                .end()
//                .to("stream:out");
//    }*/
//
//}