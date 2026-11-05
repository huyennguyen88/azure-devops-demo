package com.example.azuredevopsdemo.diagnostics;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
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

        // Attempt to resolve the hostname to an IP address
        try {
            InetAddress[] addresses = InetAddress.getAllByName(hostname);
            log.info("PG_DNS_DIAGNOSTIC host={} addresses={}", hostname, Arrays.toString(addresses));
        } catch (UnknownHostException ex) {
            log.error("PG_DNS_DIAGNOSTIC failed host={}", hostname, ex);
        }

        // Attempt to establish a TCP connection to the specified hostname on port 5432
        try (Socket socket = new Socket()) {
            socket.connect(
                    new InetSocketAddress(hostname, 5432),
                    3000);

            log.info(
                    "PG_TCP_DIAGNOSTIC host={} port=5432 result=CONNECTED",
                    hostname);

        } catch (Exception ex) {
            log.error(
                    "PG_TCP_DIAGNOSTIC host={} port=5432 result=FAILED reason={}",
                    hostname,
                    ex.toString());
        }

    }
}