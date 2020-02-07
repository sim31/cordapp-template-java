package com.template.webserver;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.corda.client.jackson.JacksonSupport;
import net.corda.core.messaging.CordaRPCOps;
import net.corda.core.node.NodeInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Define your API endpoints here.
 */
@RestController
@RequestMapping("/") // The paths for HTTP requests are relative to this base path.
public class Controller {
    private final CordaRPCOps proxy;
    private final static Logger logger = LoggerFactory.getLogger(Controller.class);

    public Controller(NodeRPCConnection rpc) {
        this.proxy = rpc.proxy;
    }

    @GetMapping(value = "/templateendpoint", produces = "text/plain")
    private String templateendpoint() {
        return "Define an endpoint here.";
    }

    @GetMapping(value = "/networkmap", produces = "text/json")
    private String networkMap() throws JsonProcessingException {
        ObjectMapper mapper = JacksonSupport.createNonRpcMapper();
        //ObjectMapper mapper = JacksonSupport.createDefaultMapper(proxy);
        List<NodeInfo> nodeList = proxy.networkMapSnapshot();
        return mapper.writeValueAsString(nodeList);
    }
}