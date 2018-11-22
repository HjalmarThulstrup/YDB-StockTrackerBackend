/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package endpointtest;
//
//import javax.json.Json;
//import javax.ws.rs.core.Application;
//import org.glassfish.jersey.server.ResourceConfig;
//import org.glassfish.jersey.test.JerseyTest;
//import org.junit.Before;
//import org.junit.Test;
//import static org.junit.Assert.*;
//import security.DummyLogin;
//
//
///**
// *
// * @author rlumh
// */
//public class LoginTest extends JerseyTest{
//    
//    @Override
//    protected Application configure() {
//        return new ResourceConfig(DummyLogin.class);
//    }
//    
//    @Test
//    public void getDummyLoginTest() {
//        String response = target("dummyLogin").request().get(String.class);
//        System.out.println(response);
//        assertNotNull(response);
//    }
//    
//    
//}
