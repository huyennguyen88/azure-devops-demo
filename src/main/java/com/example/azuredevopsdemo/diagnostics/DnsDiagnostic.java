package com.example.azuredevopsdemo.diagnostics;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DnsDiagnostic implements ApplicationRunner {

    private static final Logger log = LoggerFactory.getLogger(DnsDiagnostic.class);

    @Override
    public void run(ApplicationArguments args) {
        String hostname = System.getenv("PG_DIAGNOSTIC_HOST");
        if (hostname == null || hostname.isBlank()) {
            return;
        }
        try {
            // Perform DNS lookup for the specified hostname
            InetAddress[] addresses = InetAddress.getAllByName(hostname);
            log.info("PG_DNS_DIAGNOSTIC host={} addresses={}", hostname, Arrays.toString(addresses));
        } catch (UnknownHostException ex) {
            log.error("PG_DNS_DIAGNOSTIC failed host={}", hostname, ex);
        }
    }
}