package com.dchllc;

import static spark.Spark.get;

public class PortfolioServer {
    public static void main(String[] args) {
        get("/version", "application/json", (request, response) -> "{\"version\": \"1\"}");
    }
}
